package com.epam.rd.autocode.concurrenttictactoe;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {
    void apply(T o) throws E;

    static <T, E extends Throwable> Consumer<T> silentConsumer(ThrowingConsumer<T, E> throwingConsumer) {
        return (param) -> {
            try {
                throwingConsumer.apply(param);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
