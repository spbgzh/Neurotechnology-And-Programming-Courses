package com.epam.rd.autotasks.springemployeecatalog.exeption;

public class SQLOperationException extends RuntimeException {

    public SQLOperationException() {
    }

    public SQLOperationException(String message) {
        super(message);
    }

    public SQLOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLOperationException(Throwable cause) {
        super(cause);
    }

    public SQLOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
