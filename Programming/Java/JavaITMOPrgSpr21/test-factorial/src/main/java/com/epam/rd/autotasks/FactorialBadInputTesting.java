package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput()throws IllegalArgumentException{
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial(null);} );
    }


    @Test
    void testNegativeInput()throws IllegalArgumentException{
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("-1");} );
    }

    @Test
    void testFractionalInput()throws IllegalArgumentException{
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("1.1");} );
    }

    @Test
    void testNonDigitalInput()throws IllegalArgumentException{
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("null");} );
    }


}
