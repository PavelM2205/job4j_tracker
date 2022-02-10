package ru.job4j.tracker;

public class FindActionsByNames implements UserAction {

    private final Output out;

    public FindActionsByNames(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by names";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("===Find Items by name===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                out.println(item);
            }
        } else {
            out.println("Заявки с именем: " + name + " не найдены");
        }
        return true;
    }
}
