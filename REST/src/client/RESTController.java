package client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTController {
	public String GET(String path){
		String string = "";
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			if (response.getStatus() == 200){
				string = response.readEntity(String.class);
			}
		} catch (WebApplicationException e){
            e.getResponse();
        }
		return string;
	}

	public void PUT(String path, String update){
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.put(Entity.entity(update, MediaType.APPLICATION_JSON));

		} catch (WebApplicationException e){
			e.getResponse();
		}

	}

	public String POST(String path, String update){
		Response response = null;

		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			response = invocationBuilder.post(Entity.entity(update, MediaType.APPLICATION_JSON));
		} catch (WebApplicationException e){
			e.getResponse();
		}
		assert response != null;
		return response.readEntity(String.class);
	}

	public String DELETE(String path){
		try{
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(path);

			Invocation.Builder invocationBuilder =  webTarget.request();
			Response response = invocationBuilder.delete();

            return response.getStatusInfo() + " | " + response.getStatus();
		} catch (WebApplicationException e){
			e.getResponse();
		}
		return "Unknown Error";
	}
}
