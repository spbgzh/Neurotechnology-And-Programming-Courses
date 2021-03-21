# Test Factorial

This exercise is about getting familiar with unit testing and JUnit 5 approach in particular.

First, you need to design and code a `factorial` method in
the [Factorial](src/main/java/com/epam/rd/autotasks/Factorial.java) class. Here are some details:

- the method takes a String as a parameter, transforms it to an integer value and counts its factorial.
- The method returns a result as a String.
- String parameter `n` must represent a non-negative integer number. If it does not, throw an IllegalArgumentException.

Next, you need to complete the test classes:

- [FactorialBadInputTesting](src/main/java/com/epam/rd/autotasks/FactorialBadInputTesting.java)\
  There are four empty methods that you must complete:
    - `testNullInput` - test a null input cases
    - `testNegativeInput` - test a negative number input cases
    - `testFractionalInput` - test a fractional cases
    - `testNonDigitalInput` - test a non-digit cases
- [FactorialCsvParametrizedTesting](src/main/java/com/epam/rd/autotasks/FactorialCsvParametrizedTesting.java)\
  This is a parameterized test, that takes arguments from the [csvCases.csv](src/main/resources/csvCases.csv) file. Do
  not change the csv file, only implement the method.
- [FactorialMethodSourceParametrizedTesting](src/main/java/com/epam/rd/autotasks/FactorialMethodSourceParametrizedTesting.java)\
  This is a parameterized test, that takes arguments from the `testCases` method. You must complete the test method and
  introduce the `testCases` method, that provides following cases:
    - "1", "1"
    - "2", "2"
    - "5", "120"
- [FactorialRegularInputTesting](src/main/java/com/epam/rd/autotasks/FactorialRegularInputTesting.java)\
  This is a test class where you can add regular test cases. Consider covering cases that are not present in other test
  classes.

In order to pass the exercise, your tests must correctly detect flaws of some other implementations. There are special
tests in several classes that applies your tests to your and some of such bad implementations:

- [FactorialTestingsTest](src/test/java/com/epam/rd/autotasks/FactorialTestingsTest.java)
- [LazyFactorialTestingsTest](src/test/java/com/epam/rd/autotasks/LazyFactorialTestingsTest.java)
- [WrongOperationConcatIntFactorialTestingsTest](src/test/java/com/epam/rd/autotasks/WrongOperationConcatIntFactorialTestingsTest.java)
- [WrongOperationSumIntFactorialTestingsTest](src/test/java/com/epam/rd/autotasks/WrongOperationSumIntFactorialTestingsTest.java)

Your solution method must pass your tests, and others must fail in some cases.

Also, there is one more secret test class that you do not have access to. It will be applied to your solution once you
submit it to Autocode. So, mind variety of possible cases.

Hint: [Factorial reference](https://en.wikipedia.org/wiki/Factorial)