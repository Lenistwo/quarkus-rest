package pl.lenistwo.quarkus.repository;

import pl.lenistwo.quarkus.entities.User;

import java.util.Collection;

public interface UserDAO {

    Collection<User> allUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(int id, User user);
    Collection<User> usersWithLimit(int limit);
}
