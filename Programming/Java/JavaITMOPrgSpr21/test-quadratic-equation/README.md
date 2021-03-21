# Test Quadratic Equation

This exercise is about getting familiar with parametrized unit testing and JUnit 4.12 approach in particular.

First, you need to design and code a `solve` method in the [QuadraticEquation](src/main/java/com/epam/rd/autotasks/QuadraticEquation.java) class.
Here are some details:
- the method must solve a quadratic equation specified by three coefficients given as parameters: a,b,c.
- first parameter `a` might not be zero. If it is, throw an IllegalArgumentException.
- the `solve` method return a string in of three formats:
    - `x1 x2` (two roots in any order separated by space) if there are two roots,
    - `x1` (just the value of the root) if there is the only root,
    - `no roots` (just a string value "no roots") if there is no root.

Next, you need to complete the test classes:
- [QuadraticEquationNoRootsCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationNoRootsCasesTesting.java)
- [QuadraticEquationSingleRootCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationSingleRootCasesTesting.java)
- [QuadraticEquationTwoRootsCasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationTwoRootsCasesTesting.java)
- [QuadraticEquationZeroACasesTesting](src/main/java/com/epam/rd/autotasks/QuadraticEquationZeroACasesTesting.java)

Those are parameterized test classes, so take that into account. You have to provide at least 4 test cases for each one of the classes.
Also, you may not change signature of their constructors.

In order to pass the exercise, your tests must correctly detect flaws of some other sorting method implementations.
There are special tests in several classes that applies your tests to your and some of such bad implementations:
- [DefaultQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/DefaultQuadraticEquationTestingTest.java)
- [ParamCarefulIncapableQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarefulIncapableQuadraticEquationTestingTest.java)
- [ParamCarefulTwoRootsReversedOrderQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarefulTwoRootsReversedOrderQuadraticEquationTestingTest.java)
- [ParamCarelessSingleRootOnlyQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarelessSingleRootOnlyQuadraticEquationTestingTest.java)
- [ParamCarelessTwoRootsOnlyQuadraticEquationTestingTest](src/test/java/com/epam/rd/autotasks/ParamCarelessTwoRootsOnlyQuadraticEquationTestingTest.java)

Your solution method must pass your tests, and others must fail in some cases.

Hint: [Quadratic formula reference](https://en.wikipedia.org/wiki/Quadratic_formula)