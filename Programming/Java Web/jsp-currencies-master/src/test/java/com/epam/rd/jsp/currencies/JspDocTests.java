package com.epam.rd.jsp.currencies;

import com.google.common.collect.ImmutableMap;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;


class JspDocTests {
    static {
        System.setProperty("java.awt.headless", "true");
        System.out.println("isHeadless:" + GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadlessInstance());
    }

    @Test
    void testSmall() throws IOException, URISyntaxException {

        initTestCase("small");

        testCurrencies("small-crs.txt");

        testExchangeRates(ImmutableMap.of("from", "GBP"), "Exchange Rates for GBP", "small-er-gbp.txt");
        testExchangeRates(ImmutableMap.of("from", "USD"), "Exchange Rates for USD", "small-er-usd.txt");
        testExchangeRates(ImmutableMap.of("from", "EUR"), "Exchange Rates for EUR", "small-er-eur.txt");

        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6), "Converting GBP to USD", "small-cvt-gbp-usd-366.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123), "Converting GBP to USD", "small-cvt-gbp-usd-123132.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88), "Converting GBP to EUR", "small-cvt-gbp-usd-7888.txt");
        testConvert(ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5), "Converting EUR to USD", "small-cvt-eur-usd-555.txt");
    }

    @Test
    void testMedium() throws IOException, URISyntaxException {

        initTestCase("medium");

        testCurrencies("medium-crs.txt");

        testExchangeRates(ImmutableMap.of("from", "GBP"), "Exchange Rates for GBP", "medium-er-gbp.txt");
        testExchangeRates(ImmutableMap.of("from", "USD"), "Exchange Rates for USD", "medium-er-usd.txt");
        testExchangeRates(ImmutableMap.of("from", "EUR"), "Exchange Rates for EUR", "medium-er-eur.txt");
        testExchangeRates(ImmutableMap.of("from", "NOK"), "Exchange Rates for NOK", "medium-er-nok.txt");
        testExchangeRates(ImmutableMap.of("from", "CNY"), "Exchange Rates for CNY", "medium-er-cny.txt");
        testExchangeRates(ImmutableMap.of("from", "BRL"), "Exchange Rates for BRL", "medium-er-brl.txt");

        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6), "Converting GBP to USD", "medium-cvt-gbp-usd-366.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123), "Converting GBP to USD", "medium-cvt-gbp-usd-123132.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88), "Converting GBP to EUR", "medium-cvt-gbp-usd-7888.txt");
        testConvert(ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5), "Converting EUR to USD", "medium-cvt-eur-usd-555.txt");
        testConvert(ImmutableMap.of("from", "NOK", "to", "CNY", "amount", 10.1), "Converting NOK to CNY", "medium-cvt-nok-cny-101.txt");
        testConvert(ImmutableMap.of("from", "BRL", "to", "GBP", "amount", 1133), "Converting BRL to GBP", "medium-cvt-brl-gbp-1133.txt");
        testConvert(ImmutableMap.of("from", "EUR", "to", "CNY", "amount", 7.7), "Converting EUR to CNY", "medium-cvt-eur-cny-77.txt");
    }

    @Test
    void testLarge() throws IOException, URISyntaxException {

        initTestCase("large");

        testCurrencies("large-crs.txt");

        testExchangeRates(ImmutableMap.of("from", "GBP"), "Exchange Rates for GBP", "large-er-gbp.txt");
        testExchangeRates(ImmutableMap.of("from", "USD"), "Exchange Rates for USD", "large-er-usd.txt");
        testExchangeRates(ImmutableMap.of("from", "EUR"), "Exchange Rates for EUR", "large-er-eur.txt");
        testExchangeRates(ImmutableMap.of("from", "NOK"), "Exchange Rates for NOK", "large-er-nok.txt");
        testExchangeRates(ImmutableMap.of("from", "CNY"), "Exchange Rates for CNY", "large-er-cny.txt");
        testExchangeRates(ImmutableMap.of("from", "BRL"), "Exchange Rates for BRL", "large-er-brl.txt");
        testExchangeRates(ImmutableMap.of("from", "JPY"), "Exchange Rates for JPY", "large-er-jpy.txt");
        testExchangeRates(ImmutableMap.of("from", "SEK"), "Exchange Rates for SEK", "large-er-sek.txt");
        testExchangeRates(ImmutableMap.of("from", "RUB"), "Exchange Rates for RUB", "large-er-rub.txt");
        testExchangeRates(ImmutableMap.of("from", "PLN"), "Exchange Rates for PLN", "large-er-pln.txt");

        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6), "Converting GBP to USD", "large-cvt-gbp-usd-366.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123), "Converting GBP to USD", "large-cvt-gbp-usd-123132.txt");
        testConvert(ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88), "Converting GBP to EUR", "large-cvt-gbp-usd-7888.txt");
        testConvert(ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5), "Converting EUR to USD", "large-cvt-eur-usd-555.txt");
        testConvert(ImmutableMap.of("from", "NOK", "to", "CNY", "amount", 10.1), "Converting NOK to CNY", "large-cvt-nok-cny-101.txt");
        testConvert(ImmutableMap.of("from", "BRL", "to", "GBP", "amount", 1133), "Converting BRL to GBP", "large-cvt-brl-gbp-1133.txt");
        testConvert(ImmutableMap.of("from", "EUR", "to", "CNY", "amount", 7.7), "Converting EUR to CNY", "large-cvt-eur-cny-77.txt");
        testConvert(ImmutableMap.of("from", "RUB", "to", "USD", "amount", 5560), "Converting RUB to USD", "large-cvt-rub-usd-5560.txt");
        testConvert(ImmutableMap.of("from", "PLN", "to", "SEK", "amount", 2323), "Converting PLN to SEK", "large-cvt-pln-sek-2323.txt");
        testConvert(ImmutableMap.of("from", "JPY", "to", "CNY", "amount", 7825), "Converting JPY to CNY", "large-cvt-jpy-cny-7825.txt");
    }

    private static Tomcat tomcat;

    @BeforeAll
    public static void startServer() throws ServletException, LifecycleException {

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

        File additionWebInfClasses = new File("target/classes");
        File jstlLib = new File("src/test/resources/taglib");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        "/WEB-INF/classes",
                        additionWebInfClasses.getAbsolutePath(), "/"));
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        "/WEB-INF/lib",
                        jstlLib.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();

    }

    @AfterAll
    public static void stopServer() throws LifecycleException, IOException {
        tomcat.stop();
        initTestCase("small");
    }

    private static void testCurrencies(final String refFileName) throws URISyntaxException, IOException {
        testCase("currencies.jsp", ImmutableMap.of(), "Currencies", "li", refFileName);
    }

    private static void testExchangeRates(final ImmutableMap<String, Object> params,
                                          final String expectedHeader,
                                          final String refFileName) throws URISyntaxException, IOException {
        testCase("exchangeRates.jsp", params, expectedHeader, "li", refFileName);
    }

    private static void testConvert(final ImmutableMap<String, Object> params,
                                    final String expectedHeader,
                                    final String refFileName) throws URISyntaxException, IOException {
        testCase("convert.jsp", params, expectedHeader, "p", refFileName);
    }

    private static void testCase(final String page,
                                 final ImmutableMap<String, Object> params,
                                 final String expectedHeader,
                                 final String selector,
                                 final String refFileName) throws URISyntaxException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final URI uri = buildRequestUri(page, params);
            final Document responseDoc = executeRequest(httpClient, uri);

            final String header = extractHeader(responseDoc);
            assertEquals(expectedHeader, header);

            final String actual = extractData(responseDoc, selector);
            Files.write(Paths.get("src/test/resources/ref-html/" + refFileName), actual.getBytes(StandardCharsets.UTF_8));
            final String expected = readExpected(refFileName);
            assertEquals(expected, actual);
        }
    }

    private static String extractData(final Document responseDoc, final String selector) {
        return responseDoc.select(selector).stream()
                .map(Element::text)
                .map(String::strip)
                .collect(joining("\n"));
    }

    private static String extractHeader(final Document responseDoc) {
        return extractData(responseDoc, "h1");
    }

    private static String readExpected(final String expectedFileName) throws IOException {
        try (final Stream<String> lines = Files.lines(Paths.get("src/test/resources/ref-html/" + expectedFileName))) {
            return lines.map(String::strip).collect(Collectors.joining("\n"));
        }
    }
    private static Document executeRequest(final CloseableHttpClient httpClient, final URI uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        final String responseText = EntityUtils.toString(response.getEntity()).trim();
        return Jsoup.parse(responseText);
    }

    private static URI buildRequestUri(final String page, final ImmutableMap<String, Object> params) throws URISyntaxException {
        final URIBuilder uriBuilder = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/" + page);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            uriBuilder.setParameter(param.getKey(), String.valueOf(param.getValue()));
        }

        return uriBuilder.build();
    }

    private static void initTestCase(final String testCase) throws IOException {
        Files.copy(
                Paths.get("src/test/resources/test-cases/" + testCase + ".txt"),
                Paths.get("src/test/resources/test-cases/current.txt"),
                StandardCopyOption.REPLACE_EXISTING);
    }

}