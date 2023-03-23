package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.DbCategoryRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {
    private final DbCategoryRepository dbCategoryRepository;

    @Override
    public Collection<Category> findAll() {
        return dbCategoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return dbCategoryRepository.findById(id);
    }

    @Override
    public Collection<Category> findByIds(List<Integer> ids) {
        return dbCategoryRepository.findByIds(ids);
    }
}
