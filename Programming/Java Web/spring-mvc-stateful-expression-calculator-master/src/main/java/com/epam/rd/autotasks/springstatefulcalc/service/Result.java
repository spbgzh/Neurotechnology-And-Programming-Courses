package com.epam.rd.autotasks.springstatefulcalc.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public final class Result {

    private Result() {
    }

    public static String processingRequest(HttpSession session) {
        String expression = (String) session.getAttribute("expression");
        String result;
        expression = expression.replaceAll(" ", "");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            String ch = String.valueOf(expression.charAt(i));
            if (Character.isLetter(expression.charAt(i))) {
                String value = (String) session.getAttribute(ch);
                if (value != null) {
                    if (Character.isLetter(value.charAt(0))) {
                        value = (String) session.getAttribute(String.valueOf(value.charAt(0)));
                    }
                }
                output.append(value);
            } else {
                output.append(ch);
            }
        }

        List<String> list = Calc.processingExpression(output.toString());
        result = list.get(0);

        for (int i = list.size() - 1; i > 0; i--) {
            String s1 = list.get(i);
            String s2 = s1;
            s1 = s1.replaceAll("[()]", "");
            try {
                result = result.replace(s2, Calc.calculateExpression(s1));
            } catch (Exception e) {
                break;
            }
        }
        result = result.replaceAll("[()<>]", "");

        return result;
    }
}
