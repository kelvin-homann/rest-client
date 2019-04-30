package client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <h1>REST Client Controller</h1>
 * Controller for the Jersey REST Client API
 * Implementation of CRUD-Operations
 *
 * @author Kelvin Homann
 */
class RESTController {
    /**
     * Create: POST new Resource with specified path and JSON param
     *
     * @param path   URL e.g https://www.sebastianzander.de/cookaweb/api/v1/tags
     * @param create JSON string with accesstoken, userid and object data
     * @return api response as json
     */
    String POST(String path, String create) {
        Response response = null;

        try {
            Client client = ClientBuilder.newClient(); // Creating Client
            WebTarget webTarget = client.target(path); // Setting target path

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON); // Setting Mediatype
            response = invocationBuilder.post(Entity.entity(create, MediaType.APPLICATION_JSON)); // Executing POST
        } catch (WebApplicationException e) {
            e.getResponse();
        }
        assert response != null;
        return response.readEntity(String.class);
    }

    /**
     * Retrieve: GET request to retrieve JSON response
     *
     * @param path URL e.g https://www.sebastianzander.de/cookaweb/api/v1/tags
     * @return http response as JSON string
     */
    String GET(String path) {
        String string = "";
        try {
            Client client = ClientBuilder.newClient(); // Creating Client
            WebTarget webTarget = client.target(path); // Setting target path

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON); // Setting Mediatype
            Response response = invocationBuilder.get(); // Executing GET
            string = response.readEntity(String.class); // Setting string to response JSON
        } catch (WebApplicationException e) {
            e.getResponse();
        }
        return string;
    }

    /**
     * Update: PUT adjust ressource based on path and JSON param
     *
     * @param path   URL e.g https://www.sebastianzander.de/cookaweb/api/v1/tags
     * @param update JSON string with accesstoken, userid and object data
     */
    void PUT(String path, String update) {
        try {
            Client client = ClientBuilder.newClient(); // Creating Client
            WebTarget webTarget = client.target(path); // Setting target path

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON); // Setting Mediatype
            invocationBuilder.put(Entity.entity(update, MediaType.APPLICATION_JSON)); // Executing PUT with update JSON string

        } catch (WebApplicationException e) {
            e.getResponse();
        }

    }

    /**
     * Delete: DELETE ressource from path
     *
     * @param path URL e.g https://www.sebastianzander.de/cookaweb/api/v1/tags
     * @return api response as status string
     */
    String DELETE(String path) {
        try {
            Client client = ClientBuilder.newClient(); // Creating Client
            WebTarget webTarget = client.target(path); // Setting target path

            Invocation.Builder invocationBuilder = webTarget.request();
            Response response = invocationBuilder.delete(); // Executing DELETE

            return response.getStatusInfo() + " | " + response.getStatus();
        } catch (WebApplicationException e) {
            e.getResponse();
        }
        return "Unknown Error";
    }
}
