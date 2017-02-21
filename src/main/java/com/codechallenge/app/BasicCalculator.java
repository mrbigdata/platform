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
    }

    private final Stack<Operand> operandStack;
    private final Stack<Integer> operatorStack;

    public BasicCalculator(){
        this.operandStack = new Stack<Operand>();
        this.operatorStack = new Stack<Integer>();
    }

    public double calculate(final String expression) throws Exception{
        double result = 0;
        Operand operand;
        parse(expression);
        while( !operandStack.isEmpty()){
            //TODO order stacks based on priority
            //TODO pop 2 operators from operators stack,
            //if MULTIPLY or divide, push result back on stack
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
