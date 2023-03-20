package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class TaskService {

    private final TaskStore taskStore;

    public TaskService(TaskStore taskStore) {
        this.taskStore = taskStore;
    }

    public Task add(Task task) {
        return taskStore.add(task);
    }

    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        var isDeleted = taskStore.delete(id);
        return isDeleted;
    }


    public boolean update(int id, Task task) {
        return taskStore.replace(id, task);
    }


    public Optional<Task> findById(int id) {
        return Optional.ofNullable(taskStore.findById(id));
    }


    public Collection<Task> findAll() {
        return taskStore.findAll();
    }


}
