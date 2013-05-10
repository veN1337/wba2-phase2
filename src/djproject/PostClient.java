package djproject;

import java.io.File;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class PostClient
{
   public static void main( String[] args )
   {
	   ClientConfig config = new DefaultClientConfig();
	   Client client = Client.create(config);
	   WebResource service = client.resource("http://localhost/songs");
	   String response = service.type("application/xml")
	                            .accept("application/xml")
	                            .entity(new File("xml/song.xml"))
	                            .post(String.class);
	   System.out.println(response);
   }
}