package client;

import com.google.gson.Gson;
import resources.entitys.Recipe;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class RESTClient {
	private static final String BASE_URL = "https://www.sebastianzander.de/cookaweb/api/v1";
	private static Scanner reader = new Scanner(System.in);

	private String GET(String path){
		String string = "";
		try {
			System.out.println("GET: " + BASE_URL + "/" + path);

			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(BASE_URL + "/" + path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();
//
//			RecipeEntity recipe = response.getEntity(RecipeEntity.class);

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());

			if (response.getStatus() == 200){
				string = response.readEntity(String.class);
			}
		} catch (NotFoundException e){
			System.out.println(Response.Status.NOT_FOUND);
		} catch (BadRequestException e){
			System.out.println(Response.Status.BAD_REQUEST);
		} catch (NotAllowedException e){
			System.out.println(Response.Status.METHOD_NOT_ALLOWED);
		}
		return string;
	}

	private void PUT(String path, String update){
		try {
			System.out.println("PUT: " + BASE_URL + "/" + path);
			Gson gson = new Gson();

			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(BASE_URL + "/" + path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.put(Entity.entity(gson.toJson(update), MediaType.APPLICATION_JSON));

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());

		} catch (NotFoundException e){
			System.out.println(Response.Status.NOT_FOUND);
		} catch (BadRequestException e){
			System.out.println(Response.Status.BAD_REQUEST);
		} catch (NotAllowedException e){
			System.out.println(Response.Status.METHOD_NOT_ALLOWED);
		}
	}

	private void POST(String path, String update){
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client.target(BASE_URL + "/" + path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(gson.toJson(update), MediaType.APPLICATION_JSON));

			if (response.getStatus() != (200 | 201)) {
				throw new WebApplicationException("Failed : HTTP error code : " + response.getStatus() + " " + response.getStatusInfo());
			}

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());
		} catch (NotFoundException e){
			System.out.println(Response.Status.NOT_FOUND);
		} catch (BadRequestException e){
			System.out.println(Response.Status.BAD_REQUEST);
		} catch (NotAllowedException e){
			System.out.println(Response.Status.METHOD_NOT_ALLOWED);
		}
	}

	private void DELETE(String path){
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(BASE_URL).path(path);

		Invocation.Builder invocationBuilder =  webTarget.request();
		Response response = invocationBuilder.delete();

		System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());
	}

	public static void main(String[] args) {
		RESTClient restClient = new RESTClient();
		System.out.print("Type in the Request Type: ");
		String input = reader.nextLine();

		switch (input.toUpperCase()) {
			case "GET":
				System.out.print("Specified Path: e.g. recipes ");
				String getPath = reader.nextLine();
				System.out.println(restClient.GET(getPath));
				break;
			case "POST":
				System.out.print("Specified Path: e.g. recipes ");
				String postPath = reader.nextLine();
				System.out.print("Input Changes ");
				String postChanges = reader.nextLine();
				restClient.POST(postPath, postChanges);
			case "PUT":
				System.out.print("Specified Path: e.g. recipes ");
				String putPath = reader.nextLine();
				System.out.print("Input Changes ");
				String putChanges = reader.nextLine();
				restClient.PUT(putPath, putChanges);
				break;
			case "DELETE":
				System.out.print("Specified Path: e.g. recipes ");
				String delPath = reader.nextLine();
				restClient.DELETE(delPath);
				break;
			default:
				System.out.println("Invalid Input");
				break;
		}
		reader.close();
	}
}
