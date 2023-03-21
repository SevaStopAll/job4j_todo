package ru.job4j.todo.store;

import ru.job4j.todo.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserStore {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    Collection<User> findAll();

    boolean deleteById(int id);
}
