package com.epam.rd.autotasks;

import java.time.LocalDateTime;

public class FinishedThreadResult {
    private final String threadName;
    private final LocalDateTime finished;
    private final Throwable throwable;

    public FinishedThreadResult(final String threadName) {
        this(threadName, null);
    }

    public FinishedThreadResult(final String threadName, final Throwable throwable) {
        this.threadName = threadName;
        this.throwable = throwable;
        this.finished = LocalDateTime.now().minusNanos(2000000L);
    }

    public String getThreadName() {
        return threadName;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
