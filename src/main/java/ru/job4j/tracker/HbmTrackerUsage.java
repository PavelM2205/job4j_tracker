package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbmTrackerUsage {
    private static final Logger LOG = LoggerFactory.getLogger(HbmTrackerUsage.class);

    public static void main(String[] args) {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("Item 4");
            tracker.add(item);
            System.out.println(item);
            Item changedItem = new Item("New 4 Item");
            boolean res = tracker.replace(4, changedItem);
            System.out.println(res);
            boolean res1 = tracker.delete(7);
            System.out.println(res);
            tracker.findAll().forEach(System.out::println);
            tracker.findByName("Item 1").forEach(System.out::println);
            System.out.println(tracker.findById(2));
        } catch (Exception exc) {
            LOG.error("Exception: ", exc);
        }
    }
}
