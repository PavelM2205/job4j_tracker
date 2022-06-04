package ru.job4j.tracker;

public class MultipleDeleteAction implements UserAction {
    private final Output out;

    public MultipleDeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete many Items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("Deleting many Items");
        int number = input.askInt("Enter number to delete: ");
        for (int i = number; i >= 1; i--) {
            tracker.delete(i);
        }
        return true;
    }
}
