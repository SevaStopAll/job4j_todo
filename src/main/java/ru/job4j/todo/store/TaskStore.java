package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskStore {

  Task add(Task task);

    boolean replace(Task task);

     boolean delete(int id);

     List<Task> findAll();

    public Task findById(int id);

    List<Task> findDone();

    List<Task> findNew();

    void close();
}
