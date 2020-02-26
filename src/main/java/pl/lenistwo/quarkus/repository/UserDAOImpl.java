package pl.lenistwo.quarkus.repository;

import pl.lenistwo.quarkus.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;

@ApplicationScoped
public class UserDAOImpl implements UserDAO {

    @Inject
    private EntityManager manager;

    @Override
    public Collection<User> allUsers() {
        return manager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return manager.createQuery("from User where id=" + id, User.class).getSingleResult();
    }

}
