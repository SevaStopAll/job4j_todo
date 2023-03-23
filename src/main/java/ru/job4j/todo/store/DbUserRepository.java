package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DbUserRepository implements UserRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password)
        );
    }

    @Override
    public List<User> findAll() {
        return crudRepository.query("from User", User.class);
    }

    @Override
    public boolean deleteById(int id) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

}
