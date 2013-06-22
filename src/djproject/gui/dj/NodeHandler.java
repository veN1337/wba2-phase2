package djproject.gui.dj;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.*;


public class NodeHandler {
	
	private final static String server = "v2201206130288594.yourvserver.net";
	private final static int port = 5222;
	
	private static Connection con;
	private PubSubManager mgr;

	public NodeHandler() {
		
		// Create the configuration for this new connection
		ConnectionConfiguration config = new ConnectionConfiguration(server, port);
		config.setCompressionEnabled(true);
		config.setSASLAuthenticationEnabled(true);

		con = new XMPPConnection(config);
 
	}
	
	public PubSubManager connect(String name, String pw, String res) throws XMPPException {
		con.connect();
		con.login(name, pw, res);
		mgr = new PubSubManager(con);
		return mgr;
	}
	
	public void disconnect() throws XMPPException {
		con.disconnect();
	}
	
	public LeafNode createNode(String name) throws XMPPException, NullPointerException {
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setAccessModel(AccessModel.open);
		form.setDeliverPayloads(true);
		form.setPersistentItems(false);
		form.setPublishModel(PublishModel.open);
		form.setNodeType(NodeType.leaf);
		form.setTitle(name);

		return (LeafNode) mgr.createNode(name, form);	
	}
	
	public void deleteNode(LeafNode n) throws XMPPException {
		deleteNode(n.getId());
	}
	
	public void deleteNode(String n) throws XMPPException {
		mgr.deleteNode(n);
	}
	
	public static void main(String args[]) {
		NodeHandler nh = new NodeHandler();
		try {
			nh.connect("user1", "asd", "java");
			nh.createNode("DJ 1");
			nh.deleteNode("DJ 1");
		} catch (XMPPException e) {
			if(e.getXMPPError().getCode() == 409) {
				System.out.println("node already exists");
			} else if(e.getXMPPError().getCode() == 404) {
				System.out.println("node does not exist");
			} else {
				System.out.println("unknow error:" + e.getXMPPError().getCode());
			}
		} catch (NullPointerException e2) {
			System.out.println("not connected");
		}
	}

}
