package ru.job4j.todo.store;

import ru.job4j.todo.model.Task;

import java.util.List;

public interface TaskRepository {

  Task add(Task task);

    boolean replace(Task task);

     boolean delete(int id);

     List<Task> findAll();

    public Task findById(int id);

    List<Task> findDone(boolean done);

}
