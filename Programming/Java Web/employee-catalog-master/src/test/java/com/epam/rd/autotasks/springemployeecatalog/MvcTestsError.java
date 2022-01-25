package com.epam.rd.autotasks.springemployeecatalog;

public class MvcTestsError extends Error {
    public MvcTestsError() {
    }

    public MvcTestsError(final String message) {
        super(message);
    }

    public MvcTestsError(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MvcTestsError(final Throwable cause) {
        super(cause);
    }

    public MvcTestsError(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
