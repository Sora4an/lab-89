package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class FractionExpression {

    private ArrayList<String> stingExpressionList;
    private Stack<Fraction> stack = new Stack<>();

    public FractionExpression(String myString){
        stingExpressionList = new ArrayList<>(Arrays.asList(getRPN(myString).split(" ")));
    }

    public String getRPN(String expression){
        ArrayList<String> myStringExpression = new ArrayList<>(Arrays.asList(expression.split(" ")));
        Stack<String> myStringStack = new Stack<>();
        StringBuilder myString = new StringBuilder();
        int priority;

        for (String s : myStringExpression) {
            priority = getPriority(s);
            if (priority == 0) {
                myString.append(" ").append(s);
            } else if (priority == 1) {
                myStringStack.push(s);
            }
            if (priority > 1) {
                while (!myStringStack.empty()) {
                    if (getPriority(myStringStack.peek()) >= priority) {
                        myString.append(" ").append(myStringStack.pop());
                    } else {
                        break;
                    }
                }
                myStringStack.push(s);
            }
            if (priority == -1) {
                while (getPriority(myStringStack.peek()) != 1) {
                    myString.append(" ").append(myStringStack.pop());

                }
                myStringStack.pop();
            }
        }
        while (!myStringStack.empty()) {
            myString.append(" ").append(myStringStack.pop());
        }

        myString.deleteCharAt(0);
        return myString.toString();
    }

    private static int getPriority(String symbol){
        return switch (symbol) {
            case ("*"), (":") -> 3;
            case ("+"), ("-") -> 2;
            case ("(") -> 1;
            case (")") -> -1;
            default -> 0;
        };
    }

    public void compute() throws InvalidFractionException, InvalidExpressionException, ZeroDenominatorException {
        if (correctExpression()) {
            for (int i = 0; i < stingExpressionList.size(); i++) {
                if (isFraction(stingExpressionList.get(i))) {
                    stack.push(new Fraction(stingExpressionList.get(i)));
                } else {
                    Fraction temp1 = (stack.pop());
                    Fraction temp2 = (stack.pop());
                    switch (stingExpressionList.get(i)) {
                        case "+":
                            stack.push(Fraction.getSum(temp1, temp2));
                            break;
                        case "-":
                            stack.push(Fraction.getSubtract(temp2, temp1));
                            break;
                        case "*":
                            stack.push(Fraction.getMultiply(temp1, temp2));
                            break;
                        case ":":
                            stack.push(Fraction.getDivide(temp2, temp1));
                            break;
                    }
                }
            }
            if (stack.empty())
                throw new InvalidExpressionException();
        }
        else {
            throw new InvalidExpressionException();
        }
    }

    public Fraction getAnswer(){
        return stack.peek();
    }

    public void soutAnswer(){
        System.out.println(stack.peek());
    }
    private boolean correctExpression(){
        int numberOfFractions = 0;
        int numberOfSigns = 0;
        boolean correct = true;
        if(!(isFraction(stingExpressionList.get(0)) | isFraction(stingExpressionList.get(1)))){
            correct = false;
        }
        for (int i = 0; i < stingExpressionList.size(); i ++){
            if (!(isFraction(stingExpressionList.get(i))) && !(stingExpressionList.get(i).equals("+"))
                    && !(stingExpressionList.get(i).equals("-"))
                    && !(stingExpressionList.get(i).equals("*"))
                    && !(stingExpressionList.get(i).equals(":"))){
                correct = false;
                break;
            }
            if (isFraction(stingExpressionList.get(i))){
                numberOfFractions++;
            }
            else numberOfSigns++;
        }
        if (numberOfFractions <= numberOfSigns) correct = false;
        return correct;
    }

    private static boolean isFraction(String myString) {
        if (!(myString.equals("+"))
                && !(myString.equals("-"))
                && !(myString.equals("*"))
                && !(myString.equals(":"))){
            return true;
        }
        else{
            return false;
        }
    }
}