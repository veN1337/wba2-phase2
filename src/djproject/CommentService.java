package djproject;

import java.io.File;
import java.text.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import djproject.comments.Comment;
import djproject.comments.Comments;
import djproject.comments.ObjectFactory;



@Path( "/comments" )
public class CommentService
{
//   @GET @Produces( "application/xml" )
//   public Comments comments() throws JAXBException
//   {
//	   ObjectFactory ob = new ObjectFactory();
//	   Comments list = ob.createComments();
//	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
//	   Unmarshaller unm = ctx.createUnmarshaller();
//	   list = (Comments) unm.unmarshal(new File("xml/comments.xml"));
//	   
//	   return list;
//   }

   @Path( "/{ID}" )
   @GET @Produces( "application/xml" )
   public Comment commentbyid(@PathParam("ID") int id) throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File("xml/comments.xml"));
	   
	   return list.getComment().get(id);
   }
   
   @GET @Produces( "application/xml" )
   public Comments querycomment( @QueryParam("rating") int r ) throws JAXBException, ParseException, DatatypeConfigurationException {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File("xml/comments.xml"));
	   Comments newlist = ob.createComments();
	   
//	   Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z", Locale.GERMAN).parse(date);
//	   GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
//	   gc.setTime(date2);
//	   XMLGregorianCalendar xmldate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

	   for (Comment c: list.getComment()) {
		   if(c.getRating() >= r) {
			   newlist.getComment().add(c);
		   }
	   }
	   
	   return newlist;
   }
   
}