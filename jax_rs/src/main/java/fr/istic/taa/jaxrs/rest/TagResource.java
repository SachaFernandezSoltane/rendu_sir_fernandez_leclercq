package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.TagDAO;
import fr.istic.taa.jaxrs.domain.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("tag")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TagResource {
    private TagDAO tagDAO = new TagDAO();

    @POST
    @Path("/new")
    @Operation(summary = "Create a new tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag created successfully"),
            @ApiResponse(responseCode = "500", description = "Failed to create new tag")
    })
    public Response newTag() {
        try {
            Tag tag = new Tag();
            tagDAO.save(tag);
            return Response.ok().entity("SUCCESS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create new tag: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    @Operation(summary = "Get all tags")
    public List<Tag> getAllTags() {
        try {
            return tagDAO.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an empty list in case of failure
            return Collections.emptyList();
        }
    }

    @DELETE
    @Path("/all")
    @Operation(summary = "Delete all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tags deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Failed to delete all tags")
    })
    public Response deleteAllTags() {
        try {
            tagDAO.deleteAll();
            return Response.ok().entity("Tags deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete all tags: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idTag}")
    @Operation(summary = "Get a tag by ID")
    public Tag getTagById(@PathParam("idTag") Long idTag) {
        try {
            return tagDAO.findOne(idTag);
        } catch (Exception e) {
            // Return null or handle the exception as per your application's logic
            return null;
        }
    }

    @DELETE
    @Path("/{idTag}")
    @Operation(summary = "Delete a tag by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found"),
            @ApiResponse(responseCode = "500", description = "Failed to delete tag")
    })
    public Response deleteTag(@PathParam("idTag") Long idTag) {
        try {
            Tag tag = tagDAO.findOne(idTag);
            if (tag == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Tag not found").build();
            }
            tagDAO.delete(tag);
            return Response.ok().entity("Tag deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete tag: " + e.getMessage()).build();
        }
    }
}
