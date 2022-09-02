package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class);
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata()
            .buildSessionFactory();

    @Override
    public Item add(Item item) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception when adding Item into DB: ", exc);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Transaction transaction = null;
        boolean result = false;
        int update;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            update = session.createMutationQuery(
                    "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                            .setParameter("fName", item.getName())
                            .setParameter("fCreated", item.getCreated())
                            .setParameter("fId", id)
                            .executeUpdate();
            transaction.commit();
            result = update > 0;
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception when replace Item into DB: ", exc);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        boolean result = false;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            result = session.createMutationQuery(
                    "DELETE Item WHERE id = :fId").setParameter("fId", id)
                            .executeUpdate() > 0;
            transaction.commit();
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception when delete Item into DB: ", exc);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            result = session.createQuery("from Item", Item.class).list();
        } catch (Exception exc) {
            LOG.error("Exception when findAll into DB: ", exc);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            result = session.createQuery(
                    "FROM Item WHERE name = :fName", Item.class)
                    .setParameter("fName", key)
                    .list();
        } catch (Exception exc) {
            LOG.error("Exception when findByName into DB: ", exc);
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (Session session = sf.openSession()) {
            result = session.createQuery("FROM Item WHERE id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
        } catch (Exception exc) {
            LOG.error("Exception where findById into DB: ", exc);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
