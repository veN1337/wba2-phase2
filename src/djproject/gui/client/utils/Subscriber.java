package djproject.gui.client.utils;

import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.pubsub.*;

import djproject.gui.client.gui.GUI;

public class Subscriber {
	
	private final static String server = "v2201206130288594.yourvserver.net";
	private final static int port = 5222;
	
	GUI gui;
	
	PubSubManager mgr;
	DiscoverItems discoItems;
	Iterator<Item> it;
	Iterator<Item> it2;
	Item[] list;
	List<Subscription> subs;
	ItemEventCoordinator ilistener;
	public String user;
	
	public Subscriber(GUI gui, String username, String password){
		this.gui = gui;
		this.user = username;
		connect(username,password);
		ilistener = new ItemEventCoordinator(gui);
	}
	
	public void connect(String username, String password){
		try {
			
			ConnectionConfiguration config = new ConnectionConfiguration(server, port);
			config.setCompressionEnabled(true);
			config.setSASLAuthenticationEnabled(true);
	
			Connection con = new XMPPConnection(config);
		
			//System.out.println("Connecting to: " + server +":"+port);
			// Connect to the server
			con.connect();
			// Log into the server
			con.login(username, password, "java");
			System.out.println("Connected");
			
			// Create a pubsub manager using an existing Connection
			mgr = new PubSubManager(con);
	
			// Get the node
			//LeafNode node = mgr.getNode("test2");
			
			discoItems = mgr.discoverNodes(null);
		      
//			node.addItemEventListener(new ItemEventCoordinator());
//	    	node.subscribe("user2@"+server);

			it = discoItems.getItems();
			
			list = new Item[50];
			
			while(it.hasNext()) {
				Integer i = 0;
				Item i1 = it.next();
				list[i++] = i1;
				//System.out.println("Event: "+i1.getNode() + " ("+i+")");
				gui.addDJ(i1.getNode());
				System.out.println(i1.getNode());
				/*discoItems = mgr.discoverNodes(i1.getNode());
				it2 = discoItems.getItems();
				while(it2.hasNext()) {
					Item i2 = it2.next();
					list[i++] = i2;					
					//System.out.println("DJ: "+i2.getNode() + " ("+i+")");
					gui.addDJ(i2.getNode());
				}*/
			}			
			gui.updateSubs(getSubs());
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void subscribe(String nodeSub){
		try {
			/*System.out.print("Press a number you want to subscribe: ");
			Scanner scanner = new Scanner(System.in);
	        Integer input = scanner.nextInt();*/
	        
			boolean subPossible=true;
			
	        LeafNode node;
			node = (LeafNode) mgr.getNode(nodeSub);
			
			for(int i = 0; i < subs.size();i++){
				if(subs.get(i).getNode().equals(nodeSub)){
					subPossible=false;
				}
			}
			
			if(subPossible){
		        node.addItemEventListener(ilistener);
		    	Subscription sub = node.subscribe(user+"@"+server);
		    	System.out.println("Subscribed: " + sub.getId());
			}
	       
	        /*System.out.println("Subscribed to: " + node);	
	        
	        System.out.println("Press ENTER to disconnect");
	        input = scanner.nextInt();
	        scanner.close();
	        System.out.println("Disconneting");	*/
	        
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void unsubscribe(String nodeUnsub){
		try {
	        Node node;
			node = mgr.getNode(nodeUnsub);
			
			for(int i = 0; i < subs.size();i++){
				if(subs.get(i).getNode().equals(nodeUnsub)){
					node.removeItemEventListener(ilistener);
			    	node.unsubscribe(user+"@"+server, subs.get(i).getId());
			    	subs.remove(i);
				}
			}
			
	        
	       
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String[] getSubs(){
		try {
			//System.out.println("fuck");
			subs = mgr.getSubscriptions();
			String[] subs_string = new String[subs.size()];
			for(int i = 0; i < subs.size();i++){
				subs_string[i]=subs.get(i).getNode();
				//System.out.println(subs.get(i).getElementName());
			}
			//System.out.println("fuck2");
			return subs_string;
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
