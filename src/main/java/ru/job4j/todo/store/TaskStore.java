package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public Task add(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    public boolean replace(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    public boolean delete(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    public List<Task> findAll() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Task> findByName() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    public Task findById() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
