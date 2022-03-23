package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext("quit")){
            FractionExpression expression = new FractionExpression(
                    scanner.nextLine());
            try{
                expression.compute();
                System.out.print("Result: ");
                expression.soutAnswer();
            } catch (InvalidExpressionException e) {
                e.printStackTrace();
            } catch (InvalidFractionException invalidFractionException) {
                invalidFractionException.printStackTrace();
            } catch (ZeroDenominatorException zeroDenominatorException) {
                zeroDenominatorException.printStackTrace();
            }
        }
    }
}
