package djproject.xmpp;

import java.util.Iterator;
import java.util.Scanner;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.*;


public class NodeCreation {
	
	private final static String server = Settings.server();
	private final static int port = Settings.port();
	
	private static Connection con;

	public static void main(String[] args) {
		
		// Create the configuration for this new connection
		ConnectionConfiguration config = new ConnectionConfiguration(server, port);
		config.setCompressionEnabled(true);
		config.setSASLAuthenticationEnabled(true);

		con = new XMPPConnection(config);
		
		try {
			System.out.println("Connecting to: " + server +":"+port);
			// Connect to the server
			con.connect();
			// Log into the server
			con.login("user1", "asd", "java");
			System.out.println("Connected");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
		// Create a pubsub manager using an existing Connection
        PubSubManager mgr = new PubSubManager(con);
 
        System.out.println("Creating or open nodes");
        LeafNode leaf = null;
//		try {
//			ConfigureForm form = new ConfigureForm(FormType.submit);
//		    form.setAccessModel(AccessModel.open);
//		    form.setDeliverPayloads(true);
//		    form.setPersistentItems(false);
//		    form.setPublishModel(PublishModel.open);
//		    form.setNodeType(NodeType.collection);
//		    form.setTitle("Event 1");
//		     	
//		    boolean exists[] = checkNodes("Event 1", "test2");
//		    
//		    // Create the collection node
//		    if(!exists[0]) {
//		    	CollectionNode col = (CollectionNode) mgr.createNode("test1", form);
//		    } else {
//		    	// Get the collection node
//		    	CollectionNode col = mgr.getNode("Event 1");
//		    }
//		      
//		    // Create the leaf node
//		    if(!exists[1]) {
//				form.setCollection("Event 1");
//				form.setNodeType(NodeType.leaf);
//				form.setTitle("test2");
//				leaf = (LeafNode) mgr.createNode("test2", form);
////				form.setTitle("test3");
////				leaf = (LeafNode) mgr.createNode("test3", form);
////				form.setTitle("test4");
////				leaf = (LeafNode) mgr.createNode("test4", form);
////				form.setTitle("test5");
////				leaf = (LeafNode) mgr.createNode("test5", form);
////				form.setTitle("test6");
////				leaf = (LeafNode) mgr.createNode("test6", form);
//		    } else {
//		      	leaf = mgr.getNode("test2");
//		    }
//			
//		} catch (XMPPException e) {
//			e.printStackTrace();
//		}
		
		String input = "";
		Scanner scanner = new Scanner(System.in);
		while(!input.equals("q")) {
			System.out.print("Type a text to publish: ");
	        input = scanner.nextLine();
	
	        System.out.println("Publishing");
	        
	        SimplePayload payload = new SimplePayload("playingnow","", "<song><id>0</id><artist>Nirvana</artist><title>Smells Like Teen Spirit</title><length>270</length><album>Nevermind</album><album_artist>Nirvana</album_artist><number_in_album>1</number_in_album><genre>Grunge</genre></song>");

	        PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>("test", payload);
	        
	        //leaf.publish(item);

		}
        
//        System.out.print("Press ENTER to disconnect");
//        input = scanner.nextLine();
		scanner.close();
        System.out.println("Disconneting");
        try {
        	mgr.deleteNode("test1");
        	mgr.deleteNode("DJ 1");
        	mgr.deleteNode("DJ 2");
        	mgr.deleteNode("DJ 3");
        	
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		// Disconnect from the server
		con.disconnect();
		System.out.println("Disconnected");

	}

	private static boolean[] checkNodes(String root, String node) throws XMPPException {
		
		boolean exists[] = {false, false};
//        try {
//			List<Affiliation> affiliations = mgr.getAffiliations();
//			
//	        for(Affiliation a: affiliations) {
//	            if(a.getNodeId().equals(node)) {
//	            	exists = true;
//	            }
//	        }
//        } catch(Exception e) {
//        	e.printStackTrace();
//        	return false;
//        }
		
		// Obtain the ServiceDiscoveryManager associated with my Connection
        ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(con);
        
        // Get the items of a given XMPP entity
        // This example gets the items associated with online catalog service
        DiscoverItems discoItems = discoManager.discoverItems("pubsub."+server);

        // Get the discovered items of the queried XMPP entity
        Iterator<DiscoverItems.Item> it = discoItems.getItems();        
		
		while (it.hasNext()) {
        	DiscoverItems.Item item = (DiscoverItems.Item) it.next();
        	System.out.println(item.getNode());
            if(item.getNode().equals(root)) {
            	exists[0] = true;
            }
        }
		
		if(exists[0]) {
			discoItems = discoManager.discoverItems("pubsub."+server, root);
	        it = discoItems.getItems();		
			while (it.hasNext()) {
	        	DiscoverItems.Item item = (DiscoverItems.Item) it.next();
	        	System.out.println(item.getNode());
	            if(item.getNode().equals(node)) {
	            	exists[1] = true;
	            }
	        }
		}
		
		return exists;
	}

}
