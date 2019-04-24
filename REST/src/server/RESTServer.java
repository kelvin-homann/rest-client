package server;/*
   HTTP-Server aus JDK fuer REST-Resourcen starten.
*/
import javax.ws.rs.*;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class RESTServer {
	private final static String REST_BASE_URL = "http://localhost:8080/cooka";
	private final static String RESOURCE_PATH = "resources";

	public static void main(String[] args) {
		ResourceConfig rc = new ResourceConfig().packages( RESOURCE_PATH );
		HttpServer server = JdkHttpServerFactory.createHttpServer( URI.create( REST_BASE_URL ), rc);

		javax.swing.JOptionPane.showMessageDialog( null, "Ende" );
		server.stop( 0 );
	}
}
