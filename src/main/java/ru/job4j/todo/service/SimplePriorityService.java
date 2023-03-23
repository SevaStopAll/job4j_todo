package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.DbPriorityRepository;
import ru.job4j.todo.store.DbTaskRepository;

import java.util.Collection;

@Service
@ThreadSafe
public class SimplePriorityService implements PriorityService {
    private final DbPriorityRepository dbPriorityRepository;

    public SimplePriorityService(DbPriorityRepository dbPriorityRepository) {
        this.dbPriorityRepository = dbPriorityRepository;
    }

    @Override
    public Collection<Priority> findAll() {
        return dbPriorityRepository.findAll();
    }
}
