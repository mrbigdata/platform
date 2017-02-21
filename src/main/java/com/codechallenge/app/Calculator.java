package com.codechallenge.app;

/**
 * Calculator, evaluates and returns result for binary arithmetic expressions.
 * E.g. 1 + 1 - 4 * 4.
 */
public interface Calculator {
    /**
     *
     * @param expression arithmetic expression, each operator and operand separated by space.
     * @return result given expression evaluates to.
     * @throws Exception if expression is invalid.
     */
    double calculate(String expression) throws Exception;
}
