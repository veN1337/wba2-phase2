package djproject.rest;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import djproject.song_history.History;
import djproject.song_history.ObjectFactory;

@Path( "/history" )
public class HistoryService
{
	
	private static final String HISTORY_LIST_XML = "xml/song_history.xml";

	@PUT @Consumes( "application/xml" )
	public History updateHistory(History h) throws JAXBException
	{	
		ObjectFactory ob = new ObjectFactory();
		History list = ob.createHistory();
		JAXBContext ctx = JAXBContext.newInstance(History.class);

		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(h, (new File(HISTORY_LIST_XML)));
		
		return list;
	}

   @GET @Produces( "application/xml" )
   public History getHistory() throws JAXBException
   {
	   ObjectFactory ob = new ObjectFactory();
	   History list = ob.createHistory();
	   JAXBContext ctx = JAXBContext.newInstance(History.class);
	   Unmarshaller unm = ctx.createUnmarshaller();
	   list = (History) unm.unmarshal(new File(HISTORY_LIST_XML));
	
	   return list;
   }
   
   
   
}
