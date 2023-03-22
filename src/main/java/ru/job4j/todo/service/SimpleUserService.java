package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.DbUserRepository;

import java.util.Optional;

@Service
@ThreadSafe
public class SimpleUserService implements UserService {

    private final DbUserRepository dbUserRepository;

    public SimpleUserService(DbUserRepository dbUserRepository) {
        this.dbUserRepository = dbUserRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return dbUserRepository.save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return dbUserRepository.findByLoginAndPassword(login, password);
    }
}
