package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception exc) {
            throw new IllegalStateException(exc);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement(
                "insert into items(name, created) values(?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    item.setId(generatedKey.getInt(1));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(
                "delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    private Item getFromDB(ResultSet set) throws SQLException {
        return new Item(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getTimestamp("created").toLocalDateTime()
            );
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "select * from items;")) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    result.add(getFromDB(set));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "select id, name, created from items where name = ?;")) {
            statement.setString(1, key);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    result.add(getFromDB(set));
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement statement = cn.prepareStatement(
                "select id, name, created from Items where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    result = getFromDB(set);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}


