package ru.job4j.tracker;

import java.util.List;

public class MemTrackerProfiling {
    public static void main(String[] args) {
        ConsoleOutput out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(
                new MultipleCreateAction(out),
                new MultipleDeleteAction(out),
                new ShowAllActions(out),
                new Exit()
        );
        new StartUI(out).init(input, tracker, actions);
    }
}
