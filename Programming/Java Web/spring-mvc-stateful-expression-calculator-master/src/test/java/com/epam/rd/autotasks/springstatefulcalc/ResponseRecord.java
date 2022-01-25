package com.epam.rd.autotasks.springstatefulcalc;

public class ResponseRecord {
    public final int code;
    public final String reason;
    public final String body;

    public ResponseRecord(final int code, final String reason, final String body) {
        this.code = code;
        this.reason = reason;
        this.body = body;
    }
}
