package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.UserDAO;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("user")
@Produces({"application/json", "application/xml"})
public class UserResource {
    private UserDAO userDAO = new UserDAO();

    @POST
    @Path("/new")
    public Response newUser() {
        try {
            User user = new User("UserTest", "usertest@gmail.com", "passwordtest");
            userDAO.save(user);
            return Response.ok().entity("SUCCESS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create new user: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    public List<User> getAllUsers() {
        try {
            return userDAO.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an empty list in case of failure
            return Collections.emptyList();
        }
    }

    @DELETE
    @Path("/all")
    public Response deleteAllUsers() {
        try {
            userDAO.deleteAll();
            return Response.ok().entity("User deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete all users: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idUser}")
    public User getUserById(@PathParam("idUser") Long idUser) {
        try {
            return userDAO.findOne(idUser);
        } catch (Exception e) {
            // Return null or handle the exception as per your application's logic
            return null;
        }
    }

    @DELETE
    @Path("/{idUser}")
    public Response deleteUser(@PathParam("idUser") Long idUser) {
        try {
            User user = userDAO.findOne(idUser);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
            }
            userDAO.delete(user);
            return Response.ok().entity("User deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete user: " + e.getMessage()).build();
        }
    }
}
