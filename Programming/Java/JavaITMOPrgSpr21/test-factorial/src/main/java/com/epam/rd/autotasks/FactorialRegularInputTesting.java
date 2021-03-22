package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();
    @Test
    void testRegularInputCase1() {
        assertEquals("24", factorial.factorial("4"));
        assertEquals("2432902008176640000", factorial.factorial("20"));
        assertEquals("39916800", factorial.factorial("11"));
        assertEquals("6402373705728000", factorial.factorial("18"));
    }
}
