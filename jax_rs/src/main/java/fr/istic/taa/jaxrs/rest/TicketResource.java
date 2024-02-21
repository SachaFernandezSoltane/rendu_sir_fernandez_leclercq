package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.TicketDAO;
import fr.istic.taa.jaxrs.dao.UserDAO;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("ticket")
@Produces({"application/json", "application/xml"})
public class TicketResource {
    private TicketDAO ticketDAO = new TicketDAO();

    @POST
    @Path("/new")
    public Response newTicket() {
        try {
            Ticket ticket = new Ticket();
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findOne(1L);
            ticket.setUser(user);
            user.setTicket(ticket);
            ticketDAO.save(ticket);
            userDAO.update(user);
            return Response.ok().entity("SUCCESS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create new ticket: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    public List<Ticket> getAllTickets() {
        try {
            return ticketDAO.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an empty list in case of failure
            return Collections.emptyList();
        }
    }

    @DELETE
    @Path("/all")
    public Response deleteAllTickets() {
        try {
            ticketDAO.deleteAll();
            return Response.ok().entity("Ticket deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete all tickets: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idTicket}")
    public Ticket getTicketById(@PathParam("idTicket") Long idTicket) {
        try {
            return ticketDAO.findOne(idTicket);
        } catch (Exception e) {
            // Return null or handle the exception as per your application's logic
            return null;
        }
    }

    @DELETE
    @Path("/{idTicket}")
    public Response deleteTicket(@PathParam("idTicket") Long idTicket) {
        try {
            Ticket ticket = ticketDAO.findOne(idTicket);
            if (ticket == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Ticket not found").build();
            }
            ticketDAO.delete(ticket);
            return Response.ok().entity("Ticket deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete Ticket: " + e.getMessage()).build();
        }
    }
}
