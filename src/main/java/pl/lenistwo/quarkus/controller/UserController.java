package pl.lenistwo.quarkus.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import pl.lenistwo.quarkus.entities.ErrorResponse;
import pl.lenistwo.quarkus.entities.User;
import pl.lenistwo.quarkus.repository.UserDAOImpl;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

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
    public Response getUser(@PathParam("id") int id) {
        try {
            User user = repository.getUserById(id);
            return Response.ok(user).build();
        } catch (NoResultException e) {
            return Response.ok().entity(new ErrorResponse(HttpResponseStatus.NOT_FOUND.code(), "User not found")).build();
        }

    }

    @POST
    public void addUser(@Valid User user) {
        repository.addUser(user);
    }

    @PATCH
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, @Valid User user) {
        try {
            repository.updateUser(id, user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.ok(new ErrorResponse(HttpResponseStatus.NO_CONTENT.code(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/limit/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getUsersWithLimit(@PathParam("limit") int limit){
        return repository.usersWithLimit(limit);
    }
}
