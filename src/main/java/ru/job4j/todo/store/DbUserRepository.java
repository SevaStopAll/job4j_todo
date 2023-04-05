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

    /**
     * Save a new user in database.
     *
     * @param user user.
     * @return Optional of user with id from database.
     */
    @Override
    public Optional<User> save(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    /**
     * Find in database with user's login and password.
     *
     * @param login user's login.
     * @param password user's password.
     * @return Optional of found user.
     */
    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User where login = :fLogin and password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password)
        );
    }

    /**
     * Get all users.
     *
     * @return User list.
     */

    @Override
    public List<User> findAll() {
        return crudRepository.query("from User", User.class);
    }

    /**
     * Delete user from database by its id.
     *
     * @param id user's id.
     * @return successful delete .
     */
    @Override
    public boolean deleteById(int id) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", id)
        );
        return true;
    }

}
