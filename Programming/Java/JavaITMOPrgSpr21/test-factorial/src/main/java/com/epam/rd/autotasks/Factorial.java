package com.epam.rd.autotasks;

import java.util.regex.Pattern;

public class Factorial {
  public String factorial(String n) {
    if (!(n == null ? false : Pattern.compile("^\\d+$").matcher(n).matches()))
      throw new UnsupportedOperationException();
    int num = Integer.valueOf(n);
    int temp = 1;
    long factorial = 1;
    while (num >= temp) {
      factorial = factorial * temp;
      temp++;
    }
    return String.valueOf(factorial);
  }
}
