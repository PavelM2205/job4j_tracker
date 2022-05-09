package ru.job4j.function;

import java.util.function.Predicate;

public class StrategyUsage {

    public boolean check(Predicate<String> pred, String s) {
        return pred.test(s);
    }

    public static void main(String[] args) {
        StrategyUsage usage = new StrategyUsage();
        System.out.println(
                "Результат работы " + usage.check(str -> str.isEmpty(), "")
        );
        System.out.println(
                "Результат работы " + usage.check(str -> str.startsWith("Fun"),
                        "Functional Interface")
        );
        System.out.println(
                "Результат работы " + usage.check(str -> str.contains("rn"),
                        "Surname name")
        );
    }
}
