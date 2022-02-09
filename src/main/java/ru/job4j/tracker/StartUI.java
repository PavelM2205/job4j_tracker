package ru.job4j.tracker;

import org.w3c.dom.ls.LSOutput;

import java.time.format.DateTimeFormatter;

public class StartUI {

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + ". " + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(), new ShowAllActions(),
        new EditAction(), new DeleteAction(), new FindActionById(),
        new FindActionsByNames(), new Exit()};
        new StartUI().init(input, tracker, actions);
    }
}
