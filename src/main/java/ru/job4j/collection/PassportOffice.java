package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class PassportOffice {
    private Map<String, String> citizens = new HashMap<>();

    public boolean add(Citizen citizen) {
        boolean rsl = false;
        if (!citizens.containsKey(citizen.getPassport())) {
            citizens.put(citizen.getPassport(), citizen.getUsername());
            rsl = true;
        }
        return rsl;
    }

    public Citizen get(String passport) {
        Citizen citizen = null;
        if (citizens.containsKey(passport)) {
            citizen = new Citizen(passport, citizens.get(passport));
        }
        return citizen;
    }
}
