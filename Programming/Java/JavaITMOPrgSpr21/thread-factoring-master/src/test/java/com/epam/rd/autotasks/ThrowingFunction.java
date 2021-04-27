package com.epam.rd.autotasks;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {
    R apply(T o) throws E;

    static <T, R, E extends Throwable> Function<T, R> silent(ThrowingFunction<T, R, E> throwingFunction) {
        return (param) -> {
            try {
                return throwingFunction.apply(param);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
