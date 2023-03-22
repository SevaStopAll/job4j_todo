package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.DbTaskRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class SimpleTaskService implements TaskService {

    private final DbTaskRepository dbTaskRepository;

    public SimpleTaskService(DbTaskRepository dbTaskRepository) {
        this.dbTaskRepository = dbTaskRepository;
    }

    @Override
    public Task add(Task task) {
        return dbTaskRepository.add(task);
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        return dbTaskRepository.delete(id);
    }

    @Override
    public boolean update(Task task) {
        return dbTaskRepository.replace(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.ofNullable(dbTaskRepository.findById(id));
    }

    @Override
    public Collection<Task> findAll() {
        return dbTaskRepository.findAll();
    }

    @Override
    public Collection<Task> findDone(boolean done) {
        return dbTaskRepository.findDone(done);
    }

}
