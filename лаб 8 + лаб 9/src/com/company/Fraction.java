package com.company;

public class Fraction {
    public int numerator;
    public int denominator;


    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction( ) {
        this.numerator = 1;
        this.denominator = 1;

    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public Fraction (String fraction) throws InvalidFractionException, ZeroDenominatorException{
        String[] fractionString = fraction.split("/");

        try{
            if (fractionString.length != 2){ //Проверка на корректность ввода дроби, например 1//2
                throw new InvalidFractionException();
            }
            numerator = Integer.parseInt(fractionString[0]);
            denominator = Integer.parseInt(fractionString[1]);
            if (denominator == 0) //проверка на равенство знаменателя 0
                throw new ZeroDenominatorException();
        }
        catch (NumberFormatException numberFormatException){
            numberFormatException.printStackTrace();
            System.out.println("Error invalid fraction. Please restart and try again.");
            System.exit(1);
        }

    }
    public static int gcd(int numerator, int denominator) {
        while (denominator !=0) {
            int tmp = numerator % denominator;
            numerator = denominator;
            denominator = tmp;

        }
        return numerator;

    }
    public static int lcm(int den1, int den2) {
        return den1 / gcd(den1, den2)*den2;

    }
    public static void showReduction(String a, Fraction obj) {
        while (gcd(obj.numerator, obj.denominator) != 1) {
            int x = gcd(obj.numerator, obj.denominator);
            obj.numerator /= x;
            obj.denominator /= x;
        }
        to_normal(obj);
    }

    public void reduct(){
        while (gcd(this.numerator, this.denominator) != 1) {
            int x = gcd(this.numerator, this.denominator);
            this.numerator /= x;
            this.denominator /= x;
        }
        normalize();
    }

    public void normalize(){
        this.numerator = this.denominator < 0 ? -this.numerator : this.numerator;
        this.denominator = this.numerator < 0 ? -this.denominator : this.denominator;
    }

    public static void show(Fraction obj) {
        String a = "entering fraction values";
        showReduction(a, new Fraction(obj.numerator, obj.denominator));

    }
    public static void to_normal(Fraction obj) {
        obj.numerator = obj.denominator < 0 ? -obj.numerator : obj.numerator;
        obj.denominator = obj.numerator < 0 ? -obj.denominator : obj.denominator;
    }


    public static void addition(Fraction num1, Fraction num2) {
        int nok = lcm(num1.denominator, num2.denominator);
        int first = nok / num1.denominator * num1.numerator;
        int second = nok / num2.denominator * num2.numerator;
        int final_numerator = first + second;
        String a = "addition";
        showReduction(a, new Fraction(final_numerator, nok));
    }
    public static void subtraction(Fraction obj1, Fraction obj2) {
        int nok = lcm(obj1.denominator, obj2.denominator);
        int first = nok / obj1.denominator * obj1.numerator;
        int second = nok / obj2.denominator * obj2.numerator;
        int final_numerator = first - second;
        String a = "subtraction";
        showReduction(a, new Fraction(final_numerator, nok));
    }
    public static void multiple(Fraction num1, Fraction num2) {
        int final_numerator = num1.numerator * num2.numerator;
        int final_denominator = num1.denominator * num2.denominator;
        String a = "multiple";
        showReduction(a, new Fraction(final_numerator, final_denominator));
    }
    public static void division(Fraction num1, Fraction num2) throws ExceptionForNull {
        if (num2.numerator == 0) {
            System.out.println("На ноль делить нельзя!");
            throw new ExceptionForNull();
        }
        int final_numerator = num1.numerator * num2.denominator;
        int final_denominator = num1.denominator * num2.numerator;
        String a = "division";
        showReduction(a, new Fraction(final_numerator, final_denominator));
    }

    public static Fraction getSum(Fraction f1, Fraction f2) throws NullPointerException{
        int num;
        int denom;

        if (f1.getDenominator() != f2.getDenominator()) {
            denom = f1.getDenominator() * f2.getDenominator();
            num = f1.getNumerator() * f2.getDenominator() + f2.getNumerator() * f1.getDenominator();
        } else {
            denom = f1.getDenominator();
            num = f1.getNumerator() + f2.getNumerator();
        }
        Fraction f3 = new Fraction(num, denom);
        f3.reduct();
        return f3;
    }

    public static Fraction getSubtract(Fraction f1, Fraction f2) {
        int num;
        int denom;

        if (f1.getDenominator() != f2.getDenominator()) {
            denom = f1.getDenominator() * f2.getDenominator();
            num = f1.getNumerator() * f2.getDenominator() - f2.getNumerator() * f1.getDenominator();
        } else {
            denom = f1.getDenominator();
            num = f1.getNumerator() - f2.getNumerator();
        }
        Fraction f3 = new Fraction(num, denom);
        f3.reduct();
        return f3;
    }

    public static Fraction getMultiply(Fraction f1, Fraction f2) {
        Fraction f3 = new Fraction(f1.getNumerator() * f2.getNumerator(), f1.getDenominator() * f2.getDenominator());
        f3.reduct();
        return f3;
    }

    public static Fraction getDivide(Fraction f1, Fraction f2) {
        Fraction f3 = new Fraction(f1.getNumerator() * f2.getDenominator(), f1.getDenominator() * f2.getNumerator());
        f3.reduct();
        return f3;
    }

    public String toString() { //Fraction print
        return (numerator + "/" + denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }
}
