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

    /**
     * Save in database.
     *
     * @param task task.
     * @return task with id from DB.
     */
    @Override
    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    /**
     * Replace task in database.
     *
     * @param task goal task, what we put in database instead of another task by its id.
     * @return successful replace.
     */

    @Override
    public boolean replace(Task task) {
        crudRepository.run(session -> session.merge(task));
        return true;
    }

    /**
     * Delete from database.
     *
     * @param id - id of task we delete.
     * @return successful detele.
     */
    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

    /**
     * Find all tasks.
     *
     * @return list of all tasks.
     */
    @Override
    public List<Task> findAll() {
        return crudRepository.query("from Task AS t JOIN FETCH t.categories JOIN FETCH t.priority", Task.class);
    }

    /**
     * Find in base, based on task.id.
     *
     * @param id id of task we find.
     * @return found task.
     */
    @Override
    public Task findById(int id) {
        return crudRepository.optional(
                "from Task AS t JOIN FETCH t.categories JOIN FETCH t.priority where t.id = :fId", Task.class,
                Map.of("fId", id)
        ).get();
    }

    /**
     * Find in base, based on task.done.
     *
     * @param done status task.Done(true/false).
     * @return list of done/active tasks.
     */
    @Override
    public List<Task> findDone(boolean done) {
        return crudRepository.query(
                "from Task AS t JOIN FETCH t.categories JOIN FETCH t.priority where done = :fDone", Task.class,
                Map.of("fDone", done)
        );
    }
}
