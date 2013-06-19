package djproject.rest;

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
	   /*WebResource service = client.resource("http://localhost/songs");
	   String response = service.type("application/xml")
               .accept("application/xml")
               .entity(new File("xml/song.xml"))
               .post(String.class);
	   System.out.println(response);*/
	   
//	   WebResource service2 = client.resource("http://localhost/comments");
//	   String response2 = service2.type("application/xml")
//               .accept("application/xml")
//               .entity(new File("xml/comment.xml"))
//               .post(String.class);
//	   System.out.println(response2);
	   
	   WebResource service3 = client.resource("http://localhost/wishes");
	   String response3 = service3.type("application/xml")
               .accept("application/xml")
               .entity(new File("xml/wish.xml"))
               .post(String.class);
	   System.out.println(response3);
   }
}