package com.codechallenge.app;

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

        static final Operand valueOf(char operandAsString){
            switch(operandAsString) {
                case '+':
                    return Operand.PLUS;
                case '-':
                    return Operand.MINUS;
                case '*':
                    return Operand.MULTIPLY;
                case '/':
                    return Operand.DIVIDE;
                default:
                    throw new UnsupportedOperationException(operandAsString + "not supported.");
            }
        }
    }

    private final Stack<Operand> operandStack;
    private final Stack<Double> operatorStack;
    //temporary stack to build operator stack in the right order
    private final Stack<Double> tempOperatorStack;
    private final Stack<Operand> tempOperandStack;

    public BasicCalculator(){
        this.operandStack = new Stack<Operand>();
        this.operatorStack = new Stack<Double>();
        this.tempOperandStack = new Stack<Operand>();
        this.tempOperatorStack = new Stack<Double>();
    }

    public double calculate(final String expression){
        if( expression == null ){
            throw new IllegalArgumentException();
        }
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
                if( !operandStack.isEmpty() && operandStack.peek().getPriority() == 0){
                    Operand currentOperand = operand;
                    Double currentOperator = operatorStack.pop();
                    Operand nextOperand = operandStack.pop();
                    Double first = operatorStack.pop();
                    Double second = operatorStack.pop();
                    operatorStack.push(nextOperand.calculate(first, second));
                    operatorStack.push(currentOperator);
                    operandStack.push(currentOperand);
                }
                else{
                    Double first = operatorStack.pop();
                    Double second = operatorStack.pop();
                    operatorStack.push(operand.calculate(first, second));
                }
            }
        }
        return operatorStack.pop();
    }

    private void parseExpression(final String expression){
        final StringTokenizer st = new StringTokenizer(expression, " ");
        boolean operatorNext = true;
        try {
            while (st.hasMoreTokens()) {
                if (operatorNext) {
                    Double operator = Double.parseDouble(st.nextToken());
                    tempOperatorStack.add(operator);
                    operatorNext = false;
                } else {
                    Operand operand = Operand.valueOf(st.nextToken().toCharArray()[0]);
                    tempOperandStack.add(operand);
                    operatorNext = true;
                }
            }
        }
        catch(final Exception e){
            operandStack.clear();
            operatorStack.clear();
            throw new IllegalArgumentException(expression + " is invalid", e);
        }
        while( !tempOperatorStack.isEmpty() ){
            operatorStack.push(tempOperatorStack.pop());
        }
        while( !tempOperandStack.isEmpty() ){
            operandStack.push(tempOperandStack.pop());
        }

    }

}
