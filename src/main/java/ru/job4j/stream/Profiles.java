package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public static List<Address> collect(List<Profile> profiles) {
        List<Address> result = new ArrayList<>();
        result = profiles.stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
        return result;
    }

    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        List<Address> result = new ArrayList<>();
        result = profiles.stream()
                .map(Profile::getAddress)
                .sorted(Comparator.comparing(Address::getCity))
                .distinct()
                .collect(Collectors.toList());
        return result;
    }
}
