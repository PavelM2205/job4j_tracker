package ru.job4j.function;

import java.util.*;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = (num, str) -> map.put(num, str);
        biCon.accept(1, "one");
        biCon.accept(2, "two");
        biCon.accept(3, "three");
        biCon.accept(4, "four");
        biCon.accept(5, "five");
        biCon.accept(6, "six");
        biCon.accept(7, "seven");

        BiPredicate<Integer, String> pred = (key, value) -> {
            return key % 2 == 0 || value.length() == 4;
        };
        for (Map.Entry<Integer, String> mp : map.entrySet()) {
            Integer key = mp.getKey();
            String value = mp.getValue();
            if (pred.test(key, value)) {
                System.out.println("key: " + key + " value " + value);
            }
        }
        Supplier<List<String>> sup = () -> new ArrayList<>(map.values());
        Consumer<String> con = (s) -> System.out.println(s);
        Function<String, String> func = (s) -> s.toUpperCase();
        for (String s : sup.get()) {
            String st = func.apply(s);
            con.accept(st);
        }
    }
}
