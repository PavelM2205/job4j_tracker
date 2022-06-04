package ru.job4j.tracker;

public class MultipleCreateAction implements  UserAction {
    private final Output out;

    public MultipleCreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add many Items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("Creating many Items");
        String name = input.askStr("Enter name: ");
        int number = input.askInt("Enter number: ");
        for (int i = 1; i <= number; i++) {
            tracker.add(new Item(String.format("%s%d", name, i)));
        }
        return true;
    }
}
