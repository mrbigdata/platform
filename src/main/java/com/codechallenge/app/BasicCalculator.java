package com.codechallenge.app;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Basic implementation for Calculator
 * Using 2 stacks, one for operands, one for operators to maintain correct order of operations.
 */
public class BasicCalculator implements Calculator{

    private enum Operand {
        MINUS(1),
        PLUS(1),
        MULTIPLY(0),
        DIVIDE(0);

        private final int priority;
        Operand(int priority){
            this.priority = priority;
        }

        int getPriority(){
            return this.priority;
        }

        double calculate(Double first, Double second){
            switch(this) {
                case MINUS:
                    return first - second;
                case PLUS:
                    return first + second;
                case MULTIPLY:
                    return first*second;
                case DIVIDE:
                    return first/second;
                default:
                    return 0;
            }
        }
    }

    private final Stack<Operand> operandStack;
    private final Stack<Double> operatorStack;

    public BasicCalculator(){
        this.operandStack = new Stack<Operand>();
        this.operatorStack = new Stack<Double>();
    }

    public double calculate(final String expression) throws Exception{
        if( expression == null ){
            throw new IllegalArgumentException();
        }
        double result = 0;
        Operand operand;
        parseExpression(expression);
        while( !operandStack.isEmpty()){
            operand = operandStack.pop();
            if ( operand.getPriority() == 0 ){
                Double first = operatorStack.pop();
                Double second = operatorStack.pop();
                operatorStack.push(operand.calculate(first, second));
            }else{
                //check if should postpone 1st priority calculation until zero priority calculation is done
                if( operandStack.isEmpty() || operandStack.peek().getPriority() == 1){
                    Double first = operatorStack.pop();
                    Double second = operatorStack.pop();
                    operatorStack.push(operand.calculate(first, second));
                }
            }
        }
        result = operatorStack.pop();
        return result;
    }

    private void parseExpression(final String expression){
        final StringTokenizer st = new StringTokenizer(expression, " ");
        boolean operatorNext = true;
        while(st.hasMoreTokens()){
            try {
                if (operatorNext) {
                    Double operator = Double.parseDouble(st.nextToken());
                    operatorStack.add(operator);
                }
                else{
                    Operand operand = Operand.valueOf(st.nextToken());
                    operandStack.add(operand);
                }
            }
            catch(Exception e){
                throw new IllegalArgumentException(e);
            }
        }

    }

}
