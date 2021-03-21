package com.epam.rd.autotasks.meetautocode;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloAutocodeTest {

    @Test
    public void test() {

        final ByteArrayOutputStream sink = new ByteArrayOutputStream();
        PrintStream controlledOut = new PrintStream(sink);
        PrintStream defaultOut = System.out;

        System.setOut(controlledOut);

        try {
            HelloAutocode.main(new String[]{});
            controlledOut.flush();
            final String actual = sink.toString().trim();
            assertEquals("Hello, Autocode!", actual, "Your program must print \"Hello, Autocode!\" but printed \"" + actual + "\" instead.");
        } finally {
            System.setOut(defaultOut);
        }
    }
}