package ru.job4j.tracker.store;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class SqlTrackerTest {
    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception exc) {
            throw new IllegalStateException(exc);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertEquals(item, tracker.findById(item.getId()));
    }

    @Test
    public void whenReplaceItemAndFindByGeneratedIdThenMustBeNewItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.replace(first.getId(), second);
        assertEquals(tracker.findById(first.getId()).getName(), second.getName());
        assertEquals(tracker.findById(first.getId()).getCreated().withNano(0),
                second.getCreated().withNano(0));
    }

    @Test
    public void whenAddTwoItemsThenFindAllMustBeReturnTheSameItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        List<Item> list = tracker.findAll();
        assertEquals(2, list.size());
        assertEquals(first.getName(), list.get(0).getName());
        assertEquals(second.getName(), list.get(1).getName());
    }

    @Test
    public void whenAddItemAndDeleteThenFindAllMustBeEmpty() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        tracker.delete(first.getId());
        List<Item> list = tracker.findAll();
        assertEquals(0, list.size());
    }

    @Test
    public void whenAddAndFindByNameThenMustBeTheSameItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        List<Item> list = tracker.findByName("First");
        assertEquals(1, list.size());
        assertEquals(first, list.get(0));
    }
}
