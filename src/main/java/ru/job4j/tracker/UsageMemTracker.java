package ru.job4j.tracker;

public class UsageMemTracker {
    public static void main(String[] args) {
        MemTracker tracker = new MemTracker();
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 10_000_000; i++) {
            tracker.add(new Item("Item" + i));
        }
        System.out.printf("Общее время работы %d", System.currentTimeMillis() - start);
    }

}
