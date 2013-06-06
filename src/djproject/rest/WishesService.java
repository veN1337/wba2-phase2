package djproject.rest;

import java.io.File;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import djproject.wishes.*;

@Path( "/wishes" )
public class WishesService
{

   private static final String WISHES_XML = "xml/wishes.xml";

   @Path( "/{ID}" )
   @GET @Produces( "application/xml" )
   public Wish wishbyid(@PathParam("ID") int id) throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   Wishes list = ob.createWishes();
	   JAXBContext ctx = JAXBContext.newInstance(Wishes.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Wishes) unm.unmarshal(new File(WISHES_XML));
	   Wish newlist = ob.createWish();
	   
		for (Wish w: list.getWish()) {
			if (w.getId() == id) {
				newlist = w;
			}
		}
		return newlist;   
   }
   
   @Path( "/{ID}" )
   @DELETE
   public Wishes deletebyid(@PathParam("ID") int id) throws JAXBException
   {	
	   ObjectFactory ob = new ObjectFactory();
	   Wishes list = ob.createWishes();
	   JAXBContext ctx = JAXBContext.newInstance(Wishes.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Wishes) unm.unmarshal(new File(WISHES_XML));
	   Wishes newlist = ob.createWishes();
		
	   for (Wish w: list.getWish()) {
		   if (w.getId() != id) {
			   newlist.getWish().add(w);
		   }
	   }
		
	   Marshaller marshaller = ctx.createMarshaller();
	   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	   marshaller.marshal(newlist, (new File(WISHES_XML)));
		
	   return newlist;
	}
   
   
   @POST @Consumes( "application/xml" )
   public Wishes addwish( Wish w ) throws JAXBException {
		   
		JAXBContext ctx = JAXBContext.newInstance(Wishes.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		Wishes list = (Wishes) unm.unmarshal(new File(WISHES_XML));

		boolean exists = false;
		for(Wish wish: list.getWish()) {
			if(w.getSongId() == wish.getSongId()) {
				exists = true;
				wish.setCount(wish.getCount() + 1);
				break;
			}
		}
		
		if(!exists) {
			int i = list.getWish().size() - 1;
			list.getWish().add(w);
			list.getWish().get(i+1).setId(list.getWish().get(i).getId()+1);
			list.getWish().get(i+1).setCount(1);
		}

		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(list, (new File(WISHES_XML)));
		   
		return list;
	}	
   
   @GET @Produces( "application/xml" )
   public Wishes wishes() throws JAXBException
   {
	   JAXBContext ctx = JAXBContext.newInstance(Wishes.class);
	   Unmarshaller unm = ctx.createUnmarshaller();

	   return (Wishes) unm.unmarshal(new File(WISHES_XML));
   }
   
}