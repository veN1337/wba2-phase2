package djproject;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import djproject.songs.List;
import djproject.songs.ObjectFactory;
import djproject.songs.Song;

@Path( "/songs" )
public class SongService
{
   @GET @Produces( "application/xml" )
   public List songs() throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   List list = ob.createList();
	   JAXBContext ctx = JAXBContext.newInstance(List.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (List) unm.unmarshal(new File("xml/song_list.xml"));
	   
	   return list;
   }
   
   @Path( "{ID}" )
   @GET @Produces( "application/xml" )
   public List song(@PathParam("ID") int id) throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   List list = ob.createList();
	   JAXBContext ctx = JAXBContext.newInstance(List.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (List) unm.unmarshal(new File("xml/song_list.xml"));
	   List song = ob.createList();
	   song.getSong().add(list.getSong().get(id));
	   
	   return song;
   }
   
   @POST @Consumes( "application/xml" )
   public List createBuch( Song s ) throws JAXBException
   {
	   
	   JAXBContext ctx = JAXBContext.newInstance(List.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   List list = (List) unm.unmarshal(new File("xml/song_list.xml"));
		
	   list.getSong().add(s);

	   Marshaller marshaller = ctx.createMarshaller();
	   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	   marshaller.marshal(list, (new File("xml/song_list.xml")));
	   
	   return list;
   }
   
}
