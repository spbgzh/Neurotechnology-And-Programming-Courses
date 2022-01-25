package com.epam.rd.jsp.currencies;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CurrenciesBeanTests {

    private Currencies currencies;

    @Test
    void testSmall() throws IOException, URISyntaxException {

        initTestCase("small");
        currencies = new CurrenciesOfCurrentTestCase();

        testCaseGetCurrencies("small-crs.png");

        testCaseExRate("GBP", "small-er-gbp.txt");
        testCaseExRate("USD", "small-er-usd.txt");
        testCaseExRate("EUR", "small-er-eur.txt");

        testCaseConvert("GBP", "USD", BigDecimal.valueOf(36.6), "small-cvt-gbp-usd-366.txt");
        testCaseConvert("GBP", "USD", BigDecimal.valueOf(123.123), "small-cvt-gbp-usd-123132.txt");
        testCaseConvert("GBP", "EUR", BigDecimal.valueOf(78.88), "small-cvt-gbp-usd-7888.txt");
        testCaseConvert("EUR", "USD", BigDecimal.valueOf(55.5), "small-cvt-eur-usd-555.txt");
    }

    @Test
    void testMedium() throws IOException, URISyntaxException {

        initTestCase("medium");
        currencies = new CurrenciesOfCurrentTestCase();

        testCaseGetCurrencies("medium-crs.txt");

        testCaseExRate("GBP", "medium-er-gbp.txt");
        testCaseExRate("USD", "medium-er-usd.txt");
        testCaseExRate("EUR", "medium-er-eur.txt");
        testCaseExRate("NOK", "medium-er-nok.txt");
        testCaseExRate("CNY", "medium-er-cny.txt");
        testCaseExRate("BRL", "medium-er-brl.txt");

        testCaseConvert("GBP", "USD", BigDecimal.valueOf(36.6), "medium-cvt-gbp-usd-366.txt");
        testCaseConvert("GBP", "USD", BigDecimal.valueOf(123.123), "medium-cvt-gbp-usd-123132.txt");
        testCaseConvert("GBP", "EUR", BigDecimal.valueOf(78.88), "medium-cvt-gbp-usd-7888.txt");
        testCaseConvert("EUR", "USD", BigDecimal.valueOf(55.5), "medium-cvt-eur-usd-555.txt");
        testCaseConvert("NOK", "CNY", BigDecimal.valueOf(10.1), "medium-cvt-nok-cny-101.txt");
        testCaseConvert("BRL", "GBP", BigDecimal.valueOf(1133), "medium-cvt-brl-gbp-1133.txt");
        testCaseConvert("EUR", "CNY", BigDecimal.valueOf(7.7), "medium-cvt-eur-cny-77.txt");
    }

    @Test
    void testLarge() throws IOException, URISyntaxException {

        initTestCase("large");
        currencies = new CurrenciesOfCurrentTestCase();

        testCaseGetCurrencies("large-crs.txt");

        testCaseExRate("GBP", "large-er-gbp.txt");
        testCaseExRate("USD", "large-er-usd.txt");
        testCaseExRate("EUR", "large-er-eur.txt");
        testCaseExRate("NOK", "large-er-nok.txt");
        testCaseExRate("CNY", "large-er-cny.txt");
        testCaseExRate("BRL", "large-er-brl.txt");
        testCaseExRate("JPY", "large-er-jpy.txt");
        testCaseExRate("SEK", "large-er-sek.txt");
        testCaseExRate("RUB", "large-er-rub.txt");
        testCaseExRate("PLN", "large-er-pln.txt");

        testCaseConvert("GBP", "USD", BigDecimal.valueOf(36.6), "large-cvt-gbp-usd-366.txt");
        testCaseConvert("GBP", "USD", BigDecimal.valueOf(123.123), "large-cvt-gbp-usd-123132.txt");
        testCaseConvert("GBP", "EUR", BigDecimal.valueOf(78.88), "large-cvt-gbp-usd-7888.txt");
        testCaseConvert("EUR", "USD", BigDecimal.valueOf(55.5), "large-cvt-eur-usd-555.txt");
        testCaseConvert("NOK", "CNY", BigDecimal.valueOf(10.1), "large-cvt-nok-cny-101.txt");
        testCaseConvert("BRL", "GBP", BigDecimal.valueOf(1133), "large-cvt-brl-gbp-1133.txt");
        testCaseConvert("EUR", "CNY", BigDecimal.valueOf(7.7), "large-cvt-eur-cny-77.txt");
        testCaseConvert("RUB", "USD", BigDecimal.valueOf(5560), "large-cvt-rub-usd-5560.txt");
        testCaseConvert("PLN", "SEK", BigDecimal.valueOf(2323), "large-cvt-pln-sek-2323.txt");
        testCaseConvert("JPY", "CNY", BigDecimal.valueOf(7825), "large-cvt-jpy-cny-7825.txt");
    }

    @AfterAll
    public static void stopServer() throws IOException {
        initTestCase("small");
    }

    private void testCaseGetCurrencies(final String refTxtName) throws IOException {
        final String result = String.join("\n", currencies.getCurrencies());
        final String expected = Files.lines(Paths.get("src/test/resources/ref-txt/" + refTxtName)).collect(joining("\n"));
        assertEquals(expected, result);
    }

    private void testCaseExRate(final String from, final String refTxtName) throws IOException {
        final String result = currencies.getExchangeRates(from).entrySet().stream()
                .map(e -> e.getKey() + " - " + e.getValue())
                .collect(joining("\n"));
        final String expected = Files.lines(Paths.get("src/test/resources/ref-txt/" + refTxtName)).collect(joining("\n"));
        assertEquals(expected, result);
    }

    private void testCaseConvert(final String from,
                                 final String to,
                                 final BigDecimal amount,
                                 final String refTxtName) throws IOException {
        final String result = currencies.convert(amount, from, to).toPlainString();
        final String expected = Files.lines(Paths.get("src/test/resources/ref-txt/" + refTxtName)).collect(joining("\n"));
        assertEquals(expected, result);
    }

    private static void initTestCase(final String testCase) throws IOException {
        Files.copy(
                Paths.get("src/test/resources/test-cases/" + testCase + ".txt"),
                Paths.get("src/test/resources/test-cases/current.txt"),
                StandardCopyOption.REPLACE_EXISTING);
    }

}