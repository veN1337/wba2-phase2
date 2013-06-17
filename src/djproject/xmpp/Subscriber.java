package djproject.xmpp;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.pubsub.*;

import djproject.gui.GUI;

public class Subscriber {
	
	private final static String server = Settings.server();
	private final static int port = Settings.port();

	public Subscriber(GUI gui){
		try {
			
			ConnectionConfiguration config = new ConnectionConfiguration(server, port);
			config.setCompressionEnabled(true);
			config.setSASLAuthenticationEnabled(true);
	
			Connection con = new XMPPConnection(config);
		
			System.out.println("Connecting to: " + server +":"+port);
			// Connect to the server
			con.connect();
			// Log into the server
			con.login("user2", "asd", "java");
			System.out.println("Connected");
			
			// Create a pubsub manager using an existing Connection
			PubSubManager mgr = new PubSubManager(con);
	
			// Get the node
			//LeafNode node = mgr.getNode("test2");
			
			DiscoverItems discoItems = mgr.discoverNodes(null);
		      
//			node.addItemEventListener(new ItemEventCoordinator());
//	    	node.subscribe("user2@"+server);

			Iterator<Item> it = discoItems.getItems();
			
			Item[] list = new Item[50];
			
			while(it.hasNext()) {
				Integer i = 0;
				Item i1 = it.next();
				list[i++] = i1;
				System.out.println("Event: "+i1.getNode() + " ("+i+")");
				gui.addEvent(i1.getNode());
				discoItems = mgr.discoverNodes("test1");
				Iterator<Item> it2 = discoItems.getItems();
				while(it2.hasNext()) {
					Item i2 = it2.next();
					list[i++] = i2;					
					System.out.println("DJ: "+i2.getNode() + " ("+i+")");
					gui.addDJ(i2.getNode());
				}
			}			
			
			System.out.print("Press a number you want to subscribe: ");
			Scanner scanner = new Scanner(System.in);
	        Integer input = scanner.nextInt();
	        
	        Node node = mgr.getNode(list[input-1].getNode());
	        node.addItemEventListener(new ItemEventCoordinator(gui));
	    	Subscription sub = node.subscribe("user2@"+server);
	        
	        System.out.println("Subscribed to: " + node);	
	        
	        System.out.println("Press ENTER to disconnect");
	        input = scanner.nextInt();
	        scanner.close();
	        System.out.println("Disconneting");	
				
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
