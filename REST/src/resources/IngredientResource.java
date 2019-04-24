package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ingredient")
public class IngredientResource {
    @GET
    @Path("{ingredient-id}/json")
    @Produces( MediaType.APPLICATION_JSON )
    public String getRecipeJSON(@PathParam("ingredient-id") Integer id)
    {
        return "";
    }

    @PUT
    @Path("{ingredient-id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateByID(@PathParam("ingredient-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("{ingredient-id}")
    public Response deleteCategoryById(@PathParam("ingredient-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}
