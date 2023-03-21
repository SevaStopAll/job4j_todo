package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.Optional;

public class SimpleUserService implements UserService {
    @Override
    public Optional<User> save(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.empty();
    }
}
