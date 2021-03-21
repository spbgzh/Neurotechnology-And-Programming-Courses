package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput()throws UnsupportedOperationException{
        Throwable exception = assertThrows(UnsupportedOperationException.class,
                ()->{factorial.factorial(null);} );
    }


    @Test
    void testNegativeInput(){
        Throwable exception = assertThrows(UnsupportedOperationException.class,
                ()->{factorial.factorial("-1");} );
    }

    @Test
    void testFractionalInput(){
        Throwable exception = assertThrows(UnsupportedOperationException.class,
                ()->{factorial.factorial("1.1");} );
    }

    @Test
    void testNonDigitalInput(){
        Throwable exception = assertThrows(UnsupportedOperationException.class,
                ()->{factorial.factorial("null");} );
    }


}
