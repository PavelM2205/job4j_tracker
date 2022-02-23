package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        if (o1.split("/")[0].equals(o2.split("/")[0])) {
            result = o1.compareTo(o2);
        } else {
            result = o2.split("/")[0].compareTo(o1.split("/")[0]);
        }
        return result;
    }
}


