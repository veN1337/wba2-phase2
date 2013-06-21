package djproject.rest;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import djproject.songs.Songs;
import djproject.songs.ObjectFactory;
import djproject.songs.Song;

@Path( "/songs" )
public class SongService
{
	
	private static final String SONG_LIST_XML = "xml/song_list.xml";


	@Path( "/{ID}" )
	@GET @Produces( "application/xml" )
	public Song songbyid(@PathParam("ID") int id) throws JAXBException
	{
		ObjectFactory ob = new ObjectFactory();
		Songs list = ob.createSongs();
		JAXBContext ctx = JAXBContext.newInstance(Songs.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		list = (Songs) unm.unmarshal(new File(SONG_LIST_XML));
		Song newlist = ob.createSong();
		   
		for (Song s: list.getSong()) {
			if (s.getId() == id) {
				newlist = s;
			}
		}
		return newlist;
	}
	
	@Path( "/{ID}" )
	@DELETE
	public Songs deletebyid(@PathParam("ID") int id) throws JAXBException
	{	
		ObjectFactory ob = new ObjectFactory();
		Songs list = ob.createSongs();
		JAXBContext ctx = JAXBContext.newInstance(Songs.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		list = (Songs) unm.unmarshal(new File(SONG_LIST_XML));
		Songs newlist = ob.createSongs();
		
		for (Song s: list.getSong()) {
			if (s.getId() != id) {
				newlist.getSong().add(s);
			}
		}
		
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(newlist, (new File(SONG_LIST_XML)));
		
		return newlist;
	}
	
	@Path( "/{ID}" )
	@PUT @Consumes( "application/xml" )
	public Songs putsong(@PathParam("ID") int id, Song song) throws JAXBException
	{	
		ObjectFactory ob = new ObjectFactory();
		Songs list = ob.createSongs();
		JAXBContext ctx = JAXBContext.newInstance(Songs.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		list = (Songs) unm.unmarshal(new File(SONG_LIST_XML));
		
		for (Song s: list.getSong()) {
			if (s.getId() == id) {
				list.getSong().set(id, song);
			}
		}
		
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(list, (new File(SONG_LIST_XML)));
		
		return list;
	}
   
	@POST @Consumes( "application/xml" )
	public Songs addsong( Song s ) throws JAXBException {
		   
		JAXBContext ctx = JAXBContext.newInstance(Songs.class);
		Unmarshaller unm = ctx.createUnmarshaller();
		Songs list = (Songs) unm.unmarshal(new File(SONG_LIST_XML));

		int i = list.getSong().size() - 1;
   
		list.getSong().add(s);
		list.getSong().get(i+1).setId(list.getSong().get(i).getId()+1);

		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(list, (new File(SONG_LIST_XML)));
		   
		return list;
	}	
	

   @GET @Produces( "application/xml" )
   public Songs songs(@DefaultValue("") @QueryParam("type") String type, @DefaultValue("") @QueryParam("text") String text) throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   Songs list = ob.createSongs();
	   JAXBContext ctx = JAXBContext.newInstance(Songs.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (Songs) unm.unmarshal(new File(SONG_LIST_XML));
	   Songs newlist = ob.createSongs();
	   /*if (artist != null && !artist.isEmpty()) {
		   for (Song s: list.getSong()) {
			   if (s.getArtist().startsWith(artist)) {
				   newlist.getSong().add(s);
			   }
		   }
		   return newlist;
	   }*/
	   switch(type.toLowerCase()) {
	   case "artist":
		   for (Song s: list.getSong()) {
			   if (s.getArtist().toLowerCase().startsWith(text.toLowerCase())) {
				   newlist.getSong().add(s);
			   }
		   }
		   return newlist;
	   case "title":
		   for (Song s: list.getSong()) {
			   if (s.getTitle().toLowerCase().startsWith(text.toLowerCase())) {
				   newlist.getSong().add(s);
			   }
		   }
		   return newlist;
	   case "genre":
		   for (Song s: list.getSong()) {
			   if (s.getGenre().toLowerCase().startsWith(text.toLowerCase())) {
				   newlist.getSong().add(s);
			   }
		   }
		   return newlist;
	   }
	   
	   return list;
   }
   
   
   
}
