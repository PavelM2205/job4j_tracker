package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new ConsoleOutput();
        Input in = new StubInput(new String[] {"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(out), new Exit()};
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        UserAction[] actions = {new EditAction(), new Exit()};
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
        replacedName, "1"});
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        UserAction[] actions = {new DeleteAction(), new Exit()};
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
        "1"});
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input input = new StubInput(new String[] {"0"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new Exit()};
        new StartUI(out).init(input, tracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
        + "0. Exit Program" + System.lineSeparator()));
    }
}