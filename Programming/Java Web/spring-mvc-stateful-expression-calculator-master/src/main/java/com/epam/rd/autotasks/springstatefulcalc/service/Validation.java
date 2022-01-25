package com.epam.rd.autotasks.springstatefulcalc.service;

import org.springframework.stereotype.Service;

@Service
public final class Validation {

    private static final int MIN_RANGE = -10000;
    private static final int MAX_RANGE = 10000;

    private Validation() {
    }

    public static boolean validationExpression(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            char s1 = s.charAt(i);
            char s2 = s.charAt(i + 1);
            if (Character.isLetter(s1) && Character.isLetter(s2))
                return false;
        }
        return true;
    }

    public static boolean validationVariable(String s) {
        if (s.charAt(0) >= 'a' && s.length() == 1 && s.charAt(0) <= 'z') {
            return true;
        }
        int value = Integer.parseInt(s);
        return value >= MIN_RANGE && value <= MAX_RANGE;
    }


}