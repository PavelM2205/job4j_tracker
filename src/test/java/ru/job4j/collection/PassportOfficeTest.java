package ru.job4j.collection;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void add() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertEquals(office.get(citizen.getPassport()), citizen);
    }

    @Test
    public void get() {
        PassportOffice office = new PassportOffice();
        Citizen out = office.get("1234");
        assertNull(out);
    }

    @Test
    public void whenAddDuplicateThenFalse() {
        Citizen citizen1 = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen2 = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen1);
        assertFalse(office.add(citizen2));
    }

}