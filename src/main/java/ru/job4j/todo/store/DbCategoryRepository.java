package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DbCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    /**
     * Get all Category units.
     *
     * @return list of categories.
     */

    @Override
    public Collection<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    /**
     * Find category by id.
     *
     * @param id Category.id.
     * @return found Category.
     */

    @Override
    public Category findById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId", Category.class,
                Map.of("fId", id)
        ).get();
    }

    /**
     * Find Categories by its ids.
     *
     * @param ids.
     * @return автомобиль с id.
     */

    @Override
    public Collection<Category> findByIds(List<Integer> ids) {
        return crudRepository.query("from Category where id IN (:fValues)", Category.class, Map.of("fValues", ids));
    }
}
