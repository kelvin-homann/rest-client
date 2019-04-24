package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("test")

public class MessageResource {

	@GET
	@Path("serverinfo")
	@Produces( MediaType.APPLICATION_JSON )
	public String serverinfo()
	{
		return System.getProperty( "os.name" )+" "+System.getProperty( "os.version" );
	}

	// GET: kein Parameter
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String message() {
		System.out.println("GET ohne Parameter");
		return "Hallo!";
	}

	// GET: 1 Parameter
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("user/{user}")
	public String message(@PathParam("user") String user) {
		System.out.println("GET 1 Parameter user: " + user);
		return "Hallo " + user + "!";
	}

	// PUT: 1 Parameter, "schreiben" mit PUT
	@PUT
	@Path("user/{user}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String postMessage(@PathParam("user") String user, String message) {
		System.out.println("PUT user: "+user);
		return "Hallo " + user + "! - Die PUT-Nachricht ist '" + message + "'";
	}
}
