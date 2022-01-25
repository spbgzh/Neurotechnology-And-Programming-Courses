package com.efimchick.ifmo.web.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.http.Consts;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatefulCalcServletTest {

    private static Tomcat tomcat;

    @BeforeAll
    public static void startServer() throws Exception {

        int port = 8080;

        tomcat = new Tomcat();
        tomcat.setPort(port);

        String webappDirLocation = "src/main/webapp/";
        StandardContext ctx = (StandardContext) tomcat.addWebapp(
                "", new File(webappDirLocation).getAbsolutePath()
        );
        System.out.println(
                "configuring app with basedir: "
                        + new File("./" + webappDirLocation).getAbsolutePath()
        );

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        "/WEB-INF/classes",
                        additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();

    }

    @AfterAll
    public static void stopServer() throws Exception {
        tomcat.stop();
    }

    private void testExpression(final String expression, final ImmutableMap<String, Object> params, final int result) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom().build())
                .build()) {

            HttpContext httpContext = createCookieAwareHttpContext();

            putArgument("expression", expression, httpClient, httpContext);

            for (Map.Entry<String, Object> param : params.entrySet()) {
                putArgument(param.getKey(), String.valueOf(param.getValue()), httpClient, httpContext);
            }

            ResponseRecord response = getResult(httpClient, httpContext);

            assertEquals(String.valueOf(result), response.body);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseRecord getResult(final CloseableHttpClient httpClient, final HttpContext httpContext) {
        try {
            HttpGet httpGet = new HttpGet(calcUriBuilder("result").build());
            try (final CloseableHttpResponse response = httpClient.execute(httpGet, httpContext)) {
                return toRecord(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseRecord putArgument(final String name, final String value, final CloseableHttpClient httpClient, final HttpContext httpContext) {
        try {
            HttpPut httpPut = new HttpPut(calcUriBuilder(name).build());
            httpPut.setEntity(makePlainEntity(value));
            try (final CloseableHttpResponse response = httpClient.execute(httpPut, httpContext)) {
                return toRecord(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseRecord deleteArgument(final String name, final CloseableHttpClient httpClient, final HttpContext httpContext) throws URISyntaxException, IOException {
        HttpDelete req = new HttpDelete(calcUriBuilder(name).build());
        try (final CloseableHttpResponse response = httpClient.execute(req, httpContext)) {
            return toRecord(response);
        }
    }

    private ResponseRecord toRecord(final CloseableHttpResponse response) throws IOException {
        final String body = response.getEntity() == null ? null : EntityUtils.toString(response.getEntity());
        return new ResponseRecord(
                response.getStatusLine().getStatusCode(),
                response.getStatusLine().getReasonPhrase(),
                body
        );
    }

    private static StringEntity makePlainEntity(final String expression) {
        return new StringEntity(expression, ContentType.create("text/plain", Consts.UTF_8));
    }

    private static URIBuilder calcUriBuilder(final String entity) {
        return new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPathSegments("calc", entity);
    }

    private static HttpContext createCookieAwareHttpContext() {
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext httpContext = new BasicHttpContext();
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return httpContext;
    }

    @Test
    public void test001() throws Exception {
        testExpression("a+b/c",
                ImmutableMap.of("a", 1, "b", 2, "c", 3),
                1);
    }

    @Test
    public void test002() throws Exception {
        testExpression("x+y/z",
                ImmutableMap.of("x", 1, "y", 2, "z", 3),
                1);
    }

    @Test
    public void test003() throws Exception {
        testExpression("x+y/z",
                ImmutableMap.of("z", 1, "y", 2, "x", 3),
                5);
    }

    @Test
    public void test004() throws Exception {
        testExpression("x+y/z",
                ImmutableMap.of("x", 1, "y", 2, "z", "x"),
                3);
    }

    @Test
    public void test005() throws Exception {
        testExpression("(f + k)*(h - g)/f",
                ImmutableMap.of("f", 61, "k", 32, "h", 354, "g", 19),
                510);
    }

    @Test
    public void test006() throws Exception {
        testExpression("a/b/c/d",
                ImmutableMap.of("a", 8941, "b", 13, "c", 7, "d", 5),
                19);
    }

    @Test
    public void test007() throws Exception {
        testExpression("a/b/c/d",
                ImmutableMap.of("a", -8941, "b", 13, "c", 7, "d", 5),
                -19);
    }

    @Test
    public void test008() throws Exception {
        final int a = randomInt();
        final int b = randomInt();
        final int c = randomInt();
        testExpression("a+b/c",
                ImmutableMap.of("a", a, "b", b, "c", c),
                a + b / c);
    }

    @Test
    public void test009() throws Exception {
        final int a = randomInt();
        final int b = randomInt();
        final int c = randomInt();
        testExpression("(c*(a-b)/b)*a",
                ImmutableMap.of("a", a, "b", b, "c", c),
                (c * (a - b) / b) * a);
    }

    @Test
    public void test010() throws Exception {
        final int a = randomInt();
        final int b = randomInt();
        final Arg<Integer> c = randomChoose(arg("a", a), arg("b", b));
        testExpression("(c*(a-b)/b)*a",
                ImmutableMap.of("a", a, "b", b, "c", c.name),
                (c.val * (a - b) / b) * a);
    }

    @Test
    public void test011() throws Exception {
        CompletableFuture.allOf(
                IntStream.range(0, 10).mapToObj((i) -> (Runnable) () -> {
                            final int a = randomInt();
                            final int b = randomInt();
                            final Arg<Integer> c = randomChoose(arg("a", a), arg("b", b));
                            testExpression("(c*(a-b)/b)*a",
                                    ImmutableMap.of("a", a, "b", b, "c", c.name),
                                    (c.val * (a - b) / b) * a);
                        }).map(CompletableFuture::runAsync)
                        .toArray(CompletableFuture[]::new)
        ).join();
    }

    @Test
    public void test012() throws Exception {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom().build())
                .build()) {

            HttpContext httpContext = createCookieAwareHttpContext();

            {
                final ResponseRecord resp = putArgument("expression", "a+b", httpClient, httpContext);
                assertEquals(201, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("expression", "a+2*b", httpClient, httpContext);
                assertEquals(200, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("expression", "2*a+2*b", httpClient, httpContext);
                assertEquals(200, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("expression", "bad format", httpClient, httpContext);
                assertEquals(400, resp.code);
                assertNotNull(resp.reason);
            }
            {
                final ResponseRecord resp = putArgument("a", "3", httpClient, httpContext);
                assertEquals(201, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("a", "7", httpClient, httpContext);
                assertEquals(200, resp.code);
            }
            {
                final ResponseRecord resp = deleteArgument("a", httpClient, httpContext);
                assertEquals(204, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("a", "3", httpClient, httpContext);
                assertEquals(201, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("b", "4", httpClient, httpContext);
                assertEquals(201, resp.code);
            }
            {
                final ResponseRecord resp = getResult(httpClient, httpContext);
                assertEquals(200, resp.code);
                assertEquals("14", resp.body);
            }
            {
                final ResponseRecord resp = deleteArgument("a", httpClient, httpContext);
                assertEquals(204, resp.code);
            }
            {
                final ResponseRecord resp = getResult(httpClient, httpContext);
                System.out.println(resp.body);
                assertEquals(409, resp.code);
                assertNotNull(resp.reason);
            }
            {
                final ResponseRecord resp = putArgument("a", "5", httpClient, httpContext);
                assertEquals(201, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("a", "10005", httpClient, httpContext);
                assertEquals(403, resp.code);
            }
            {
                final ResponseRecord resp = putArgument("a", "-10005", httpClient, httpContext);
                assertEquals(403, resp.code);
            }
            {
                final ResponseRecord resp = getResult(httpClient, httpContext);
                assertEquals(200, resp.code);
                assertEquals("18", resp.body);
            }
        }
    }

    @Test
    @DisplayName("Implementation must use Servlets and Filters")
    public void testSources() throws IOException {
        assertTrue(Utils.findInSource("javax.servlet.Filter"));
        assertTrue(Utils.findInSource("void doFilter"));
        assertTrue(Utils.findInSource("javax.servlet.http.HttpServlet"));
        assertTrue(Utils.findInSource("void doPut"));
        assertTrue(Utils.findInSource("void doDelete"));
        assertTrue(Utils.findInSource("void doGet"));
    }

    private Random random = new Random();

    private int randomInt() {
        return random.nextInt(100) + 1;
    }

    @SafeVarargs
    private final <T> T randomChoose(T... values) {
        return values[random.nextInt(values.length)];
    }

    private static Arg<Integer> arg(final String name, final Integer val) {
        return new Arg<Integer>(name, val);
    }

    private static class Arg<T> {
        private final String name;
        private final T val;

        private Arg(final String name, final T val) {
            this.name = name;
            this.val = val;
        }
    }

}