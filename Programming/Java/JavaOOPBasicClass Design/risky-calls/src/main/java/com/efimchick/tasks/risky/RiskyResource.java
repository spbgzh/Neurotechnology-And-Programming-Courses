package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.CarelessResourceConsumer;

import java.io.Closeable;
import java.io.IOException;

public class RiskyResource {

    final int input;
    final CarelessResourceConsumer careless;
    final Closeable resource;

    public RiskyResource(final int input,
                         final CarelessResourceConsumer careless,
                         final Closeable resource) {
        this.input = input;
        this.careless = careless;
        this.resource = resource;
    }

    public void handleCarelessConsuming()  /*You may not add "throws" here*/  {
        // handle method call
        //careless.consume(input, resource);
        try {

            careless.consume(input, resource);

        } catch (Exception test) {


                test.getMessage();


        } finally {
            try {
                resource.close();
            } catch (IOException test) {
                test.printStackTrace();
            }
        }

    }
}
