package pl.lenistwo.quarkus.repository;

import pl.lenistwo.quarkus.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {

    @Inject
    private EntityManager manager;

    @Override
    public Collection<User> allUsers() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return manager.createQuery("from User where id=" + id, User.class).getSingleResult();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        manager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        user.setId(id);
        manager.merge(user);
    }

    @Override
    public Collection<User> usersWithLimit(int limit) {
        return manager.createQuery("from User", User.class).setMaxResults(limit).getResultList();
    }

}
