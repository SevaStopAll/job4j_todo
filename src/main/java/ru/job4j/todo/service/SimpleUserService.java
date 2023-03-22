package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.DbTaskStore;
import ru.job4j.todo.store.DbUserStore;

import java.util.Optional;

@Service
@ThreadSafe
public class SimpleUserService implements UserService {

    private final DbUserStore dbUserStore;

    public SimpleUserService(DbUserStore dbUserStore) {
        this.dbUserStore = dbUserStore;
    }

    @Override
    public Optional<User> save(User user) {
        return dbUserStore.save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return dbUserStore.findByLoginAndPassword(login, password);
    }
}
