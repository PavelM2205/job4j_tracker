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
import java.util.ArrayList;
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
        Item item = tracker.add(new Item("item"));
        assertEquals(item, tracker.findById(item.getId()));
    }

    @Test
    public void whenReplaceItemAndFindByGeneratedIdThenMustBeNewItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = tracker.add(new Item("First"));
        Item second = new Item("Second");
        tracker.replace(first.getId(), second);
        assertEquals(tracker.findById(first.getId()).getName(), second.getName());
    }

    @Test
    public void whenAddTwoItemsThenFindAllMustBeReturnTheSameItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = tracker.add(new Item("First"));
        Item second = tracker.add(new Item("Second"));
        assertEquals(List.of(first, second), tracker.findAll());
    }

    @Test
    public void whenAddItemAndDeleteThenFindAllMustBeEmpty() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = tracker.add(new Item("First"));
        tracker.delete(first.getId());
        assertNull(tracker.findById(first.getId()));
        assertEquals(0, tracker.findAll().size());
    }

    @Test
    public void whenAddAndFindByNameThenMustBeTheSameItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = tracker.add(new Item("First"));
        Item second = tracker.add(new Item("Second"));
        assertEquals(List.of(first), tracker.findByName("First"));
    }
}
