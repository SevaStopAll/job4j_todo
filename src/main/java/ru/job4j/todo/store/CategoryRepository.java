package ru.job4j.todo.store;

import ru.job4j.todo.model.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository {
    Collection<Category> findAll();

    Category findById(int id);

    Collection<Category> findByIds(List<Integer> ids);

}
