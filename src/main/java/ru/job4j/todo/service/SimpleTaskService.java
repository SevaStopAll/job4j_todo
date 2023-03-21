package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.DbTaskStore;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class SimpleTaskService implements TaskService {

    private final DbTaskStore dbTaskStore;

    public SimpleTaskService(DbTaskStore dbTaskStore) {
        this.dbTaskStore = dbTaskStore;
    }

    @Override
    public Task add(Task task) {
        return dbTaskStore.add(task);
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        return dbTaskStore.delete(id);
    }

    @Override
    public boolean update(Task task) {
        return dbTaskStore.replace(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.ofNullable(dbTaskStore.findById(id));
    }

    @Override
    public Collection<Task> findAll() {
        return dbTaskStore.findAll();
    }

    @Override
    public Collection<Task> findDone() {
        return dbTaskStore.findDone();
    }

    @Override
    public Collection<Task> findNew() {
        return dbTaskStore.findNew();
    }
}
