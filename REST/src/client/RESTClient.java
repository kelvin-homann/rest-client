package client;

import com.google.gson.Gson;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClient {
	public String GET(String path){
		String string = "";
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

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

	public void PUT(String path, String update){
		try {
			Gson gson = new Gson();

			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.put(Entity.entity(gson.toJson(update), MediaType.APPLICATION_JSON));

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());

		} catch (WebApplicationException e){
			e.getResponse();
		}
	}

	public void POST(String path, String update){
		try {
			Gson gson = new Gson();
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(gson.toJson(update), MediaType.APPLICATION_JSON));

			if (response.getStatus() != (200 | 201)) {
				throw new WebApplicationException("Failed : HTTP error code : " + response.getStatus() + " " + response.getStatusInfo());
			}

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());
		} catch (WebApplicationException e){
			e.getResponse();
		}
	}

	public void DELETE(String path){
		try{
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request();
			Response response = invocationBuilder.delete();

			System.out.println("HTTP Response: " + response.getStatus() + " " + response.getStatusInfo());
		} catch (WebApplicationException e){
			e.getResponse();
		}
	}
}
