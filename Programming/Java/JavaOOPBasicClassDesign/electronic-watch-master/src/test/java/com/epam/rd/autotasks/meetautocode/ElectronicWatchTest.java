package com.epam.rd.autotasks.meetautocode;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ElectronicWatchTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int input, String expected) {

        String inputText = Integer.toString(input);

        final ByteArrayInputStream controlledIn = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        InputStream defaultIn = System.in;

        final ByteArrayOutputStream sink = new ByteArrayOutputStream();
        PrintStream controlledOut = new PrintStream(sink);
        PrintStream defaultOut = System.out;

        try {
            System.setIn(controlledIn);
            System.setOut(controlledOut);

            ElectronicWatch.main(new String[]{});

            controlledOut.flush();
            assertEquals(expected, sink.toString().trim(), "Error on input value: " + input);
        } finally {
            System.setIn(defaultIn);
            System.setOut(defaultOut);
        }
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(60, "0:01:00"),
                Arguments.of(3599, "0:59:59"),
                Arguments.of(86229, "23:57:09"),
                Arguments.of(86400, "0:00:00"),
                Arguments.of(89999, "0:59:59"),
                Arguments.of(86460, "0:01:00"),
                Arguments.of(1, "0:00:01"),
                Arguments.of(10, "0:00:10"),
                Arguments.of(11, "0:00:11"),
                Arguments.of(70, "0:01:10"),
                Arguments.of(71, "0:01:11"),
                Arguments.of(3601, "1:00:01")
        );
    }
}