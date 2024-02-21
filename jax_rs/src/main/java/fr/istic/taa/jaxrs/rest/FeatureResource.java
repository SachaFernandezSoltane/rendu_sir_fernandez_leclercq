package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.FeatureDAO;
import fr.istic.taa.jaxrs.domain.Feature;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("feature")
@Produces({"application/json", "application/xml"})
public class FeatureResource {
    private FeatureDAO featureDAO = new FeatureDAO();

    @POST
    @Path("/new")
    public Response newFeature() {
        try {
            Feature feature = new Feature();
            featureDAO.save(feature);
            return Response.ok().entity("SUCCESS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create new feature: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    public List<Feature> getAllFeatures() {
        try {
            return featureDAO.findAll();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return an empty list in case of failure
            return Collections.emptyList();
        }
    }

    @DELETE
    @Path("/all")
    public Response deleteAllFeatures() {
        try {
            featureDAO.deleteAll();
            return Response.ok().entity("Features deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete all features: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idFeature}")
    public Feature getStatusById(@PathParam("idFeature") Long idFeature) {
        try {
            return featureDAO.findOne(idFeature);
        } catch (Exception e) {
            // Return null or handle the exception as per your application's logic
            return null;
        }
    }

    @DELETE
    @Path("/{idFeature}")
    public Response deleteFeature(@PathParam("idFeature") Long idFeature) {
        try {
            Feature feature = featureDAO.findOne(idFeature);
            if (feature == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Feature not found").build();
            }
            featureDAO.delete(feature);
            return Response.ok().entity("Feature deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete feature: " + e.getMessage()).build();
        }
    }
}
