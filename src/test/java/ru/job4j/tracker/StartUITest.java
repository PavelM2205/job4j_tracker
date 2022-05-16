package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new ConsoleOutput();
        Input in = new StubInput(new String[] {"0", "Item name", "1"});
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new CreateAction(out), new Exit());
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new ConsoleOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        List<UserAction> actions = List.of(new EditAction(out), new Exit());
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
        replacedName, "1"});
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new ConsoleOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        List<UserAction> actions = List.of(new DeleteAction(out), new Exit());
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
        "1"});
        new StartUI(out).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input input = new StubInput(new String[] {"0"});
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new Exit());
        new StartUI(out).init(input, tracker, actions);
        assertThat(out.toString(), is("Menu." + System.lineSeparator()
        + "0. Exit Program" + System.lineSeparator()));
    }

    @Test
    public void whenEditItem() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
                replaceName, "1"});
        List<UserAction> actions = List.of(new EditAction(out), new Exit());
        new StartUI(out).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                + "0. Edit item" + ln
                + "1. Exit Program" + ln
                + "===Edit Item===" + ln
                + "Заявка изменена успешно." + ln
                + "Menu." + ln
                + "0. Edit item" + ln
                + "1. Exit Program" + ln
                )
        );
    }

    @Test
    public void whenShowAllItems() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test2"));
        Input input = new StubInput(new String[] {"0", "1"});
        List<UserAction> actions = List.of(new ShowAllActions(out), new Exit());
        new StartUI(out).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "===Show all items===" + ln
                + item + ln
                + "Menu." + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindItemsByName() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test3"));
        Input input = new StubInput(new String[] {"0", item.getName(), "1"});
        List<UserAction> actions = List.of(new FindActionsByNames(out), new Exit());
        new StartUI(out).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                + "0. Find items by names" + ln
                + "1. Exit Program" + ln
                + "===Find Items by name===" + ln
                + item + ln
                + "Menu." + ln
                + "0. Find items by names" + ln
                + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenFindItemByID() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test4"));
        Input input = new StubInput(new String[] {"0", String.valueOf(item.getId()),
                "1"});
        List<UserAction> actions = List.of(new FindActionById(out), new Exit());
        new StartUI(out).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
                + "===Find Item by id===" + ln
                + item + ln
                + "Menu." + ln
                + "0. Find item by id" + ln
                + "1. Exit Program" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input input = new StubInput(
                new String[] {"7", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = List.of(new Exit());
        new StartUI(out).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                + "0. Exit Program" + ln
                + "Wrong input, can select: 0 .. 0" + ln
                +   "Menu." + ln
                + "0. Exit Program" + ln
        ));
    }
}