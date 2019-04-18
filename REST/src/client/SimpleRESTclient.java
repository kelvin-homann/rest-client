package client;

import org.glassfish.jersey.client.ClientResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.*;

public class SimpleRESTclient {

	public static void main(String[] args) {
		
		// Initialisierung
		String restURL = "http://localhost:8080/rest";
		String s = "";
		System.out.println();

		// Mehrere GET-Kommandos und 1* PUT

		try {
			System.out.println("GET: "+restURL+"/message");
			s =  ClientBuilder.newClient()
					.target(restURL).path( "message" )
					.request().get( String.class );
			System.out.println("  Aufruf von GET ohne Parameter lieferte: " + s );
			System.out.println();

		} catch (NotFoundException e){
			System.out.println("404");
		}

		try {
			System.out.println("GET: " + restURL + "/message/user/Susi");
			s =  ClientBuilder.newClient()
					.target(restURL).path( "message/user/Susi" )
					.request().get( String.class );
			System.out.println("  Aufruf von GET mit 1 Parameter lieferte:  "  + s );
			System.out.println();

		} catch (NotFoundException e){
			System.out.println("404");
		}

		try {
			System.out.println("PUT: " + restURL + "/message/user/Studierende");
			s =  ClientBuilder.newClient()
					.target(restURL)
					.path( "message/user/Studierende" )
					.request().put( Entity.text("Findet Wissen!") )
					.readEntity(String.class);
			System.out.println( "  PUT lieferte: "+ s );

		} catch (NotFoundException e){
			System.out.println("404");
		}

		System.out.println();
	}
}
