package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {
    public static String tableString(char[][] table){
        return Arrays.stream(table)
                .map(String::new)
                .collect(Collectors.joining("\n"));
    }
}
