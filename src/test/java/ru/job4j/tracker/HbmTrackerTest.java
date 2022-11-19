package ru.job4j.tracker;

import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HbmTrackerTest {

    @After
    public void tableCleaning() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            List<Item> items = tracker.findAll();
            items.forEach(item -> tracker.delete(item.getId()));
        }
    }

    @Test
    public void whenAddThenFindByIdReturnsSameItemWithId() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Item");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
            assertThat(result.getId(), not(0));
        }
    }

    @Test
    public void whenReplaceThenFindByIdReturnsChangedItem() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Item");
            tracker.add(item);
            Item changedItem = new Item();
            changedItem.setName("ChangedItem");
            tracker.replace(item.getId(), changedItem);
            Item itemFromDB = tracker.findById(item.getId());
            assertThat(itemFromDB.getName(), is(changedItem.getName()));
        }
    }

    @Test
    public void whenAddTwoItemThenFindAllReturnsBoth() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("First");
            Item item2 = new Item();
            item2.setName("Second");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> itemsFromDB = tracker.findAll();
            assertThat(itemsFromDB.get(0).getId(), is(item1.getId()));
            assertThat(itemsFromDB.get(0).getName(), is(item1.getName()));
            assertThat(itemsFromDB.get(1).getId(), is(item2.getId()));
            assertThat(itemsFromDB.get(1).getName(), is(item2.getName()));
        }
    }

    @Test
    public void whenDeleteThenFindAllReturnsEmptyList() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Item");
            tracker.add(item);
            tracker.delete(item.getId());
            assertTrue(tracker.findAll().isEmpty());
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("Item");
            tracker.add(item);
            Item itemFromDB = tracker.findByName(item.getName()).get(0);
            assertThat(itemFromDB.getId(), is(item.getId()));
            assertThat(itemFromDB.getName(), is(item.getName()));
        }
    }
}