package djproject.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import djproject.comments.Comments;
import djproject.song_history.History;
import djproject.songs.Song;
import djproject.songs.Songs;
import djproject.wishes.Wishes;

public class RESTHandler {
	
	
	static ClientConfig config = new DefaultClientConfig();
	static Client client = Client.create(config);
	
	final static String host = "http://localhost:4444";
	
	public static Songs getSongs(String type, String text) {
		WebResource service = client.resource(host + "/songs/?type=" + type + "&text=" + text);
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(Songs.class);
	}
	
	public static Song getSongById(int id) {
		WebResource service = client.resource(host + "/songs/"+ id);
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(Song.class);
	}
	
	public static Wishes getWishes() {
		WebResource service = client.resource(host + "/wishes");
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(Wishes.class);
	}
	
	public static void updateSong(int id, Song s) {
		WebResource service = client.resource(host + "/songs/" + String.valueOf(id));
		service.type("application/xml")
	            .accept("application/xml")
	            .entity(s)
	            .put();
	}
	
	public static void deleteSong(int id) {
		WebResource service = client.resource(host + "/songs/" + String.valueOf(id));
		service.type("application/xml").delete();
	}
	
	public static void addSong(Song s) {
		WebResource service = client.resource(host + "/songs/");
		service.type("application/xml")
	            .accept("application/xml")
	            .entity(s)
	            .post();
	}

	public static void deleteWish(int id) {
		WebResource service = client.resource(host + "/wishes/" + String.valueOf(id));
		service.type("application/xml").delete();
	}

	public static Comments getComments() {
		WebResource service = client.resource(host + "/comments");
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(Comments.class);
	}

	public static void deleteComment(int id) {
		WebResource service = client.resource(host + "/comments/" + String.valueOf(id));
		service.type("application/xml").delete();
	}
	
	public static void updateHistory(History h) {
		WebResource service = client.resource(host + "/history/");
		service.type("application/xml")
	            .accept("application/xml")
	            .entity(h)
	            .put();
	}

	public static History getHistory() {
		WebResource service = client.resource(host + "/history/");
		ClientResponse response = service.type("application/xml")
	            .accept("application/xml")
	            .get(ClientResponse.class);
		return response.getEntity(History.class);
	}
	
}
