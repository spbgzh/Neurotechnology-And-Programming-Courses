package com.efimchick.tasks.risky.util;

import java.io.FileNotFoundException;
import java.io.IOException;

//do not edit
public class RussianRoulette {

    final FileNotFoundException fileNotFoundException = new FileNotFoundException("1");
    final IOException ioException = new IOException("2");
    final ArithmeticException arithmeticException = new ArithmeticException("3");
    final NumberFormatException numberFormatException = new NumberFormatException("4");
    final UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("5");

    final Exception[] cylinder = new Exception[]{
            fileNotFoundException,
            ioException,
            arithmeticException,
            numberFormatException,
            unsupportedOperationException,
            null
    };

    private final int shift;

    public RussianRoulette() {
        this(0);
    }

    public RussianRoulette(final int shift) {
        this.shift = shift;
    }

    public int shot(int i) throws Exception {
        Exception e = cylinder[Math.abs(i + shift) % 6];
        if (e == null) {
            return i;
        }
        throw e;
    }

}
