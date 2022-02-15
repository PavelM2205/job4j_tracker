package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872",
                "Bryansk")
        );
        ArrayList<Person> result = phones.find("Petr");
        assertEquals("Arsentev", result.get(0).getSurname());
    }

    @Test
    public void whenNotFound() {
        PhoneDictionary phone = new PhoneDictionary();
        phone.add(
                new Person("Ivan", "Ivanov", "338856",
                        "Irkutsk")
        );
        ArrayList<Person> result = phone.find("Petr");
        assertTrue(result.isEmpty());
    }
}