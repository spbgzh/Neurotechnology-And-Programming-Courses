package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageTest {

    private ByteArrayOutputStream sink;
    private PrintStream controlledOut;
    private ByteArrayInputStream controlledIn;
    private PrintStream defaultOut;
    private InputStream defaultIn;

    @ParameterizedTest
    @MethodSource("testCases")
    void test(final String input, final String expected) {
        setControlledStreamsWithInput(input);
        try {
            Average.main(new String[]{});
            controlledOut.flush();
            assertEquals(expected, sink.toString().trim(), "Error on input " + input);
        } finally {
            setStandardStreams();
        }
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("1 2 3 4 5 0", "3"),
                Arguments.of("1 2 3 4 6 0 ", "3"),
                Arguments.of("1 2 3 4 0 ", "2"),
                Arguments.of("1 1 9 0 ", "3"),
                Arguments.of("2 3 6 8 5 6 8 7 4 1 2 0", "4"),
                Arguments.of("1 1 10 0 ", "4"),
                Arguments.of("787 5 32 8 5 39 0 ", "146"),
                Arguments.of("-1 -1 12 3 5 -100 0 ", "-13"),
                Arguments.of("1111 111 11 1 0 ", "308"),
                Arguments.of("1 1 -1 -1 0 ", "0"),
                Arguments.of("1 1 -2 0 ", "0")
        );
    }

    private void setControlledStreamsWithInput(final String input) {

        sink = new ByteArrayOutputStream();
        controlledOut = new PrintStream(sink);
        controlledIn = new ByteArrayInputStream(input.getBytes());

        defaultOut = System.out;
        defaultIn = System.in;

        System.setOut(controlledOut);
        System.setIn(controlledIn);
    }

    private void setStandardStreams() {
        System.setOut(defaultOut);
        System.setIn(defaultIn);
    }

}