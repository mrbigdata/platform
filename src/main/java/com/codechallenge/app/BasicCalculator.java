package com.codechallenge.app;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Stack;

/**
 * Basic implementation for Calculator
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
        double result = 0;
        Operand operand;
        parse(expression);
        while( !operandStack.isEmpty()){
            operand = operandStack.pop();
            if ( operand.getPriority() == 0 ){
                Double first = operatorStack.pop();
                Double second = operatorStack.pop();
                operatorStack.push(operand.calculate(first, second));
            }
        }
        return result;
    }

    private void parseExpression(final String expression) throws Exception{
        final StringTokenizer st = new StringTokenizer(expression, ' ');
        boolean operatorNext = true;
        while(st.hasNext()){
            try {
                if (operatorNext) {
                    Integer operator = Integer.parseInt(st.next());
                    operatorStack.add(operator);
                }
                else{
                    Operand operand = Operand.valueOf(st.next());
                    operandStack.add(operand);
                }
            }
            catch(Exception e){
                throw new Exception(e);
            }
        }

    }

}
