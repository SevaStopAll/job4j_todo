package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DbTaskRepository implements TaskRepository {
    private final CrudRepository crudRepository;

    @Override
    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    @Override
    public boolean replace(Task task) {
        crudRepository.run(session -> session.merge(task));
        return true;
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

    @Override
    public List<Task> findAll() {
        return crudRepository.query("from Task AS t JOIN FETCH t.priority", Task.class);
    }

    @Override
    public Task findById(int id) {
        return crudRepository.optional(
                "from Task AS t JOIN FETCH t.priority where t.id = :fId", Task.class,
                Map.of("fId", id)
        ).get();
    }

    @Override
    public List<Task> findDone(boolean done) {
        return crudRepository.query(
                "from Task AS t JOIN FETCH t.priority where done = :fDone", Task.class,
                Map.of("fDone", done)
        );
    }
}
