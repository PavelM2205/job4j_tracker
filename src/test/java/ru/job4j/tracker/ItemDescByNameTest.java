package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void compare() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("One");
        Item item2 = new Item("Two");
        Item item3 = new Item("Three");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        List<Item> expected = Arrays.asList(item2, item3, item1);
        Collections.sort(items, new ItemDescByName());
        assertEquals(expected, items);
    }
}