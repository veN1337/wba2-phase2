package rest_example;

import javax.ws.rs.*;

@Path( "" )
public class HWService
{
   @GET @Produces( "text/plain" )
   public String halloText()
   {
      return "Hello World";
   }
}