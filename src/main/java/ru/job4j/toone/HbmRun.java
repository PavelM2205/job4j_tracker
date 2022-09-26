package ru.job4j.toone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata()
                    .buildSessionFactory();
            var role = new Role();
            role.setName("ADMIN");
            create(role, sf);
            var user = new User();
            user.setName("Admin Admin");
            user.setRole(role);
            user.setMessengers(List.of(
                    new UserMessenger(0, "tg", "@tg"),
                    new UserMessenger(0, "wu", "@wu")
            ));
            create(user, sf);
            User stored = sf.openSession()
                    .createQuery("FROM User WHERE id = :fId", User.class)
                    .setParameter("fId", user.getId())
                    .getSingleResult();
            stored.getMessengers().forEach(System.out::println);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> void create(T model, SessionFactory sf) {
        Session session = null;
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.persist(model);
            session.getTransaction().commit();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("FROM " + cl.getName(),
                cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
