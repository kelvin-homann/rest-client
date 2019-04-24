package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("category")
public class CategoryResource {

    @GET
    @Path("{category-id}/json")
    @Produces( MediaType.APPLICATION_JSON )
    public String getRecipeJSON(@PathParam("category-id") Integer id)
    {
        return "";
    }

    @PUT
    @Path("{category-id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateByID(@PathParam("category-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("{category-id}")
    public Response deleteCategoryById(@PathParam("category-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}
