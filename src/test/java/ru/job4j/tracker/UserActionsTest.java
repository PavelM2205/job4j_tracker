package ru.job4j.tracker;

import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class UserActionsTest {

    @Test
    public void whenExecuteEditActionSuccess() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced Item"));
        String replacedName = "New item name";
        EditAction action = new EditAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Edit Item===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenExecuteEditActionFail() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced Item"));
        EditAction action = new EditAction(out);
        Input input = mock(Input.class);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Edit Item===" + ln + "Ошибка замены заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced Item"));
    }

    @Test
    public void whenDeleteActionSuccess() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New Item"));
        DeleteAction action = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Delete Item===" + ln
                + "Заявка удалена успешно." + ln));
        assertThat(tracker.findAll(), is(List.of()));
    }

    @Test
    public void whenDeleteFail() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New Item"));
        DeleteAction action = new DeleteAction(out);
        Input input = mock(Input.class);
        int wrongItemIndex = 2;
        when(input.askInt(any(String.class))).thenReturn(wrongItemIndex);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Delete Item===" + ln
                + "Ошибка удаления заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("New Item"));
    }

    @Test
    public void whenCreateActionSuccess() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        CreateAction action = new CreateAction(out);
        Input input = mock(Input.class);
        String itemName = "New Item";
        when(input.askStr(any(String.class))).thenReturn(itemName);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Create a new Item===" + ln
                + "Добавленная заявка: id: 1, name: " + itemName + ", created: "
                + tracker.findAll().get(0).getCreated().format(DateTimeFormatter
                .ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss"))
                + ln));
        assertThat(tracker.findAll().get(0).getName(), is(itemName));
    }

    @Test
    public void whenFindByIdActionSuccess() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item");
        tracker.add(item);
        FindActionById action = new FindActionById(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Find Item by id===" + ln + "id: " + item.getId()
                + ", name: " + item.getName() + ", created: " + tracker.findAll().get(0).getCreated()
                .format(DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss")) + ln));
        assertThat(tracker.findById(1).getName(), is(item.getName()));
    }

    @Test
    public void whenFindByIdActionFail() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindActionById action = new FindActionById(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Find Item by id===" + ln
                + "Заявка с введенным id: " + 1 + " не найдена." + ln));
    }

    @Test
    public void whenFindByNameActionSuccess() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item");
        tracker.add(item);
        FindActionsByNames action = new FindActionsByNames(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Find Items by name===" + ln
        + "id: " + item.getId() + ", name: " + item.getName() + ", created: "
                + tracker.findAll().get(0).getCreated()
                .format(DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss")) + ln));
    }

    @Test
    public void whenFindByNameActionFail() {
        String ln = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item");
        tracker.add(item);
        FindActionsByNames action = new FindActionsByNames(out);
        String wrongName = "Wrong Name";
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(wrongName);
        action.execute(input, tracker);
        assertThat(out.toString(), is("===Find Items by name===" + ln
        + "Заявки с именем: " + wrongName + " не найдены" + ln));
    }
}
