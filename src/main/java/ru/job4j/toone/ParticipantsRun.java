package ru.job4j.toone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.Item;

import java.util.List;

public class ParticipantsRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata()
                    .buildSessionFactory();
            Role role = new Role();
            role.setName("ADMIN");
            create(sf, role);
            User user = new User();
            user.setName("Admin Admin");
            user.setRole(role);
            user.setMessengers(List.of(
                    new UserMessenger(0, "tg", "@tg"),
                    new UserMessenger(0, "wu", "@wu")
            ));
            create(sf, user);
            Item item = new Item();
            item.setName("Learn Hibernate");
            item.setParticipants(List.of(user));
            create(sf, item);
            sf.openSession()
                    .createQuery("FROM Item WHERE id = :fId", Item.class)
                    .setParameter("fId", item.getId())
                    .getSingleResult()
                    .getParticipants()
                    .forEach(System.out::println);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(SessionFactory sf, T model) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.persist(model);
            session.getTransaction().commit();
        } catch (Exception exc) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exc;
        } finally {
            session.close();
        }
    }

    public static void update(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception exc) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exc;
        } finally {
            session.close();
        }
    }

    public static void delete(SessionFactory sf, Integer id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
        } catch (Exception exc) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exc;
        } finally {
            session.close();
        }
    }

    public static List<Item> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("FROM Item", Item.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
