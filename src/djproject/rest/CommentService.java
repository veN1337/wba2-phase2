package djproject.rest;

import java.io.File;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.xml.datatype.DatatypeConfigurationException;
import djproject.comments.Comment;
import djproject.comments.Comments;
import djproject.comments.ObjectFactory;

@Path( "/comments" )
public class CommentService
{

   private static final String COMMENTS_XML = "xml/comments.xml";

@Path( "/{ID}" )
   @GET @Produces( "application/xml" )
   public Comment commentbyid(@PathParam("ID") int id) throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File(COMMENTS_XML));
	   Comment newlist = ob.createComment();
	   
		for (Comment c: list.getComment()) {
			if (c.getId() == id) {
				newlist = c;
			}
		}
		return newlist;   
   }
   
   @Path( "/{ID}" )
   @DELETE
   public Comments deletebyid(@PathParam("ID") int id) throws JAXBException
   {	
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File(COMMENTS_XML));
	   Comments newlist = ob.createComments();
		
	   for (Comment c: list.getComment()) {
		   if (c.getId() != id) {
			   newlist.getComment().add(c);
		   }
	   }
		
	   Marshaller marshaller = ctx.createMarshaller();
	   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	   marshaller.marshal(newlist, (new File(COMMENTS_XML)));
		
	   return newlist;
	}
   
   @GET @Produces( "application/xml" )
   public Comments querycomment( @QueryParam("rating") int r ) throws JAXBException, ParseException, DatatypeConfigurationException {
	   ObjectFactory ob = new ObjectFactory();
	   Comments list = ob.createComments();
	   JAXBContext ctx = JAXBContext.newInstance(Comments.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Comments) unm.unmarshal(new File(COMMENTS_XML));
	   Comments newlist = ob.createComments();
	   
//	   Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z", Locale.GERMAN).parse(date);
//	   GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
//	   gc.setTime(date2);
//	   XMLGregorianCalendar xmldate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	   if (r > 0) {
		   for (Comment c: list.getComment()) {
			   if(c.getRating() >= r) {
				   newlist.getComment().add(c);
			   }
		   }		   
		   return newlist;
	   }
	   return list;
   }
   
   @POST @Consumes( "application/xml" )
   public Comments addcomment( Comment c ) throws JAXBException {
		   
		JAXBContext ctx = JAXBContext.newInstance(Comments.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		Comments list = (Comments) unm.unmarshal(new File(COMMENTS_XML));

		int i = list.getComment().size() - 1;
  
		list.getComment().add(c);
		list.getComment().get(i+1).setId(list.getComment().get(i).getId()+1);

		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(list, (new File(COMMENTS_XML)));
		   
		return list;
	}	
   
}