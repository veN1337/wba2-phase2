package djproject;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import djproject.comments.Comments;
import djproject.comments.ObjectFactory;



@Path( "/comments" )
public class CommentService
{
   @GET @Produces( "application/xml" )
   public Comments comments() throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File("xml/comments.xml"));
	   
	   return list;
   }
   
   @GET @Produces( "application/xml" )
   public Comments querycomment( @QueryParam("author") String from, @QueryParam("date") String date ) throws JAXBException {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File("xml/comments.xml"));
	   
	   return list;
   }
   
}