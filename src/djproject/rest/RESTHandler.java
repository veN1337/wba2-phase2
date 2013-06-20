package djproject.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import djproject.songs.Song;
import djproject.songs.Songs;

public class RESTHandler {
	
	
	static ClientConfig config = new DefaultClientConfig();
	static Client client = Client.create(config);
	
	public static Songs getSongs(String type, String text) {
		WebResource service = client.resource("http://localhost/songs/?type=" + type + "&text=" + text);
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(Songs.class);
	}
	
	public static void updateSong(int id, String artist, String title, String album, String albumartist, String numberalbum, String genre, String length) {
//		WebResource service = client.resource("http://localhost/songs/" + String.valueOf(id));
//		ClientResponse response = service.type("application/xml")
//	            .accept("application/xml")
//	            .put();
	}
	
	public static void deleteSong(int id) {
		WebResource service = client.resource("http://localhost/songs/" + String.valueOf(id));
		service.type("application/xml").delete();
	}
	
}
