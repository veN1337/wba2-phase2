package djproject.gui.client.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import djproject.gui.client.gui.GUI;
import djproject.song_history.History;

public class ItemEventCoordinator implements ItemEventListener<Item>  {
	
	private GUI gui;
	
	@Override
	public void handlePublishedItems(ItemPublishEvent<Item> items) {
//		System.out.println("Item count: " + items.getItems().size());
//        System.out.println(items);
		System.out.println("new history");
        for(Item i: items.getItems()) {
        	
        	System.out.println(i.toXML());
			try {
				JAXBContext ctx = JAXBContext.newInstance(History.class);
				Unmarshaller unm = ctx.createUnmarshaller();
				String ii = i.toXML().replace("<item id='historyupdate'>", "").replace("</item>", "").replace(" xmlns=\"http://jabber.org/protocol/pubsub\"", "");
	     	   	StringReader reader = new StringReader(ii);
	     	   	History h = (History) unm.unmarshal(reader);
	     	   	gui.history.updateList(h);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	   
        }
        
	}
	
	public ItemEventCoordinator(GUI gui){
		this.gui = gui;
	}

}
