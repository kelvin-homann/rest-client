package resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import resources.entitys.Recipe;
import server.DatabaseClient;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Path("recipes")
public class RecipeResource {
    @GET
    @Path("{recipe-id}/json")
    @Produces( MediaType.APPLICATION_JSON )
    public String getRecipeJSON(@Context UriInfo info, @PathParam("recipe-id") Integer id)
    {
        System.out.println(id);
        DatabaseClient dbclient = new DatabaseClient();
        System.out.println("Created DBClient");
        Recipe recipe = dbclient.getRecipeById(id);
        if (recipe == null) {
            throw new UnknownResourceException();
        }
        dbclient.CloseConnection();
        return recipe.toJSON();
    }


    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRecipeJSON(@Context UriInfo info)
    {
        DatabaseClient dbclient = new DatabaseClient();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Created DBClient");
        ArrayList<Recipe> recipeList = dbclient.getRecipes();
        if (recipeList == null) {
            throw new UnknownResourceException();
        }
        dbclient.CloseConnection();
        System.out.println(recipeList.size());
        return gson.toJson(recipeList);
    }
    @PUT
    @Path("{recipe-id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateByID(@PathParam("recipe-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("{recipe-id}")
    public Response deleteRecipebyId(@PathParam("recipe-id") Integer id)
    {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}
