package com.efimchick.tasks.risky.util;

import java.io.Closeable;

//do not edit
public class CarelessResourceConsumer {

    private final int shift;

    public CarelessResourceConsumer(final int shift) {
        this.shift = shift;
    }

    public void consume(int input, Closeable resource) throws Exception {

        switch (input + shift) {
            case 0: {
                resource.close();
                break;
            }
            case 1: {
                resource.close();
                throw new DontCareException();
            }
            case 2: {
                throw new DontCareException();
            }
        }
    }

    public static class DontCareException extends Exception {

    }
}
