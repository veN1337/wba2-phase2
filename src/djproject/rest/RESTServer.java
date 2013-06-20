package djproject.rest;
import java.io.IOException;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

public class RESTServer
{
	
	private String url;
	private SelectorThread srv;
	
	public RESTServer() {
		url = "http://localhost";
	}
	
	public void start() {
		try {
			srv = GrizzlyServerFactory.create( url );
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		System.out.println("Shutdown");
		srv.stopEndpoint();
	}
	
//	public static void main( String[] args ) throws Exception
//	{
//	
//	   SelectorThread 
//	
//	   System.out.println( "URL: " + url );
//	   Thread.sleep( 1000 * Integer.parseInt( sec ) * 10 );
//	   
//	   System.out.println( "Shutdown" );
//	}
}