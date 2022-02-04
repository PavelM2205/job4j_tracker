package ru.job4j.oop;

import java.sql.SQLOutput;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int m) {
        return m - x;
    }

    public int divide(int d) {
        return d / x;
    }

    public int sumAllOperation(int o) {
        return sum(o) + multiply(o) + minus(o) + divide(o);
    }

    public static void main(String[] args) {
        int result = sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println(rsl);
        System.out.println(minus(8));
        System.out.println(calculator.divide(20));
        System.out.println(calculator.sumAllOperation(10));
    }
}
