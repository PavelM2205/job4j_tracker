package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HQLUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        try (SessionFactory sf = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory()) {
            Session session = sf.openSession();
            Query<Item> query = session.createQuery("from Item", Item.class);
            for (var st : query.list()) {
                System.out.println(st);
            }
            unique(session);
            findById(session, 4);
            update(session, 4);
            delete(session, 4);
            insert(session);
            session.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void unique(Session session) {
        Query<Item> query = session.createQuery(
                "from Item as i where i.id = 3", Item.class);
        System.out.println(query.uniqueResult());
    }

    public static void findById(Session session, int id) {
        Query<Item> query = session.createQuery(
                "from Item as i where i.id = :fId", Item.class)
                        .setParameter("fId", id);
        System.out.println(query.uniqueResult());
    }

    public static void update(Session session, int id) {
        try {
            session.beginTransaction();
            session.createMutationQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exc) {
            session.getTransaction().rollback();
        }
    }

    public static void delete(Session session, int id) {
        try {
            session.beginTransaction();
            session.createMutationQuery(
                    "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exception) {
            session.getTransaction().rollback();
        }
    }

    public static void insert(Session session) {
        try {
            session.beginTransaction();
            session.createMutationQuery(
                    "INSERT INTO Item (name) VALUES (:fName)")
                    .setParameter("fName", "NEW NAME")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exc) {
            session.getTransaction().rollback();
        }
    }
}
