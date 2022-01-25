package com.epam.rd.autotasks.springstatefulcalc.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class Calc {

    private Calc() {
    }

    public static List<String> processingExpression(String mathExpression) {
        List<String> stringParts = new ArrayList<>();
        char firstBracket = '(';
        char lastBracket = ')';
        stringParts.add(mathExpression);
        while (mathExpression.contains(Character.toString(firstBracket))) {
            int num = mathExpression.indexOf(firstBracket);
            int ordinal = 1;
            for (int i = num + 1; i < mathExpression.length(); ++i) {
                if (mathExpression.charAt(i) == firstBracket) {
                    ++ordinal;
                } else if (mathExpression.charAt(i) == lastBracket) {
                    --ordinal;
                }
                if (mathExpression.charAt(i) == lastBracket
                        && ordinal == 0) {
                    stringParts.add(mathExpression.substring(num, ++i));
                    break;
                }
            }
            mathExpression = mathExpression.substring(++num);
        }
        return stringParts;
    }

    public static String calculateExpression(String expression) {
        expression = addSpace(expression);
        String[] arrExpr = expression.split(" ");
        int length = arrExpr.length;
        int i = 1;
        while (i < length - 1) {
            if (arrExpr[i].equals("*") || arrExpr[i].equals("/")) {
                arrExpr[i - 1] = calculate(Integer.parseInt(arrExpr[i - 1]),
                        Integer.parseInt(arrExpr[i + 1]),
                        arrExpr[i]);
                overwriting(arrExpr, i);
                i = 1;
                length -= 2;
            } else
                i += 2;
        }
        i = 1;
        while (i < length - 1) {
            if (arrExpr[i].equals("-") || arrExpr[i].equals("+")) {
                arrExpr[i - 1] = calculate(Integer.parseInt(arrExpr[i - 1]),
                        Integer.parseInt(arrExpr[i + 1]),
                        arrExpr[i]);
                overwriting(arrExpr, i);
                i = 1;
                length -= 2;
            } else
                i += 2;
        }
        return arrExpr[0];
    }

    private static String addSpace(String s) {
        StringBuilder output = new StringBuilder();
        String space = " ";
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '*' || c == '/' || c == '+') {
                output.append(space).append(c).append(space);
            } else if (c == '-') {
                if (i != 0 && Character.isDigit(s.charAt(i - 1))) {
                    output.append(space).append(c).append(space);
                } else {
                    output.append(c);
                }
            } else {
                output.append(c);
            }
        }
        return output.toString();
    }

    private static String calculate(int valueX, int valueY, String operator) {
        int result = 0;
        switch (operator) {
            case "*": {
                result = valueX * valueY;
                break;
            }
            case "/": {
                result = valueX / valueY;
                break;
            }
            case "+": {
                result = valueX + valueY;
                break;
            }
            case "-": {
                result = valueX - valueY;
                break;
            }
        }
        return String.valueOf(result);
    }

    private static void overwriting(String[] arrStr, int position) {
        if (arrStr.length - (position + 2) >= 0) {
            System.arraycopy(arrStr, position + 2,
                    arrStr, position, arrStr.length - (position + 2));
        }
    }
}
