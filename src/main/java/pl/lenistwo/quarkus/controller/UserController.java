package pl.lenistwo.quarkus.controller;

import pl.lenistwo.quarkus.entities.User;
import pl.lenistwo.quarkus.exceptions.UserNotFoundException;
import pl.lenistwo.quarkus.repository.UserDAOImpl;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;

@Path("/user")
public class UserController {

    private UserDAOImpl repository;

    @Inject
    public UserController(UserDAOImpl repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        return repository.allUsers();
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {
        Optional<User> user = Optional.ofNullable(repository.getUserById(id));
        return user.orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not exist!"));
    }
}
