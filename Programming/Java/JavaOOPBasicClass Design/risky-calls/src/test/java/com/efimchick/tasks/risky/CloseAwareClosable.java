package com.efimchick.tasks.risky;

import java.io.Closeable;
import java.io.IOException;

public class CloseAwareClosable implements Closeable {
    boolean closed = false;

    @Override
    public void close() throws IOException {
        this.closed = true;
    }
}
