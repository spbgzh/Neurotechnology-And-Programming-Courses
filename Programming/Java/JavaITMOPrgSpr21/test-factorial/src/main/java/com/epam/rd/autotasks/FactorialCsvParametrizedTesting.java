package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialCsvParametrizedTesting {

    Factorial factorial = new Factorial();

    @ParameterizedTest
    @CsvFileSource(resources = "/csvCases.csv")
    void testFactorialCase(String input, String output){
        String delimiter = ",";
        String[] temp;
        temp = input.split(delimiter);
        assertEquals(factorial.factorial(temp[0]), temp[1]);
    }
    String testCases(){

    }
}
