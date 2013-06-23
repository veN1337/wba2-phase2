package djproject.gui.dj.gui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SongInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTextField txt_artist = new JTextField();
	public JTextField txt_title = new JTextField();
	public JTextField txt_album = new JTextField();
	public JTextField txt_albumartist = new JTextField();
	public JTextField txt_number_album = new JTextField();
	public JTextField txt_genre = new JTextField();
	public JTextField txt_length = new JTextField();

	JLabel label_artist = new JLabel();
	JLabel label_title = new JLabel();
	JLabel label_album = new JLabel();
	JLabel label_albumartist = new JLabel();
	JLabel label_number_album = new JLabel();
	JLabel label_genre = new JLabel();
	JLabel label_length = new JLabel();
	JLabel label_cover = new JLabel();
	
	private int id = -1;
	
	public SongInfoPanel(ListenerHandler_main listener) {
		
		setLayout(new MigLayout());
		
		label_artist.setText("Artist");
		label_title.setText("Title");
		label_album.setText("Album");
		label_albumartist.setText("Album Artist");
		label_number_album.setText("Number in Album");
		label_genre.setText("Genre");
		label_length.setText("Length");
		label_cover.setText("Cover");
		
		txt_artist.addKeyListener(listener);
		txt_title.addKeyListener(listener);
		txt_genre.addKeyListener(listener);
		txt_length.addKeyListener(listener);
		
		JPanel cover = new JPanel();
		
		cover.setBorder(BorderFactory.createDashedBorder(new Color(0, 0, 0)));
		
		add(label_artist, "width 150!, height 30!, span 1");
		add(label_title, "width 150!, height 30!, span 1");
		add(label_album, "width 150!, height 30!, span 1, wrap");
		add(txt_artist, "width 150!, height 30!, span 1");
		add(txt_title, "width 150!, height 30!, span 1");
		add(txt_album, "width 150!, height 30!, span 1, wrap");
		add(label_albumartist, "width 150!, height 30!, span 1");
		add(label_number_album, "width 150!, height 30!, span 1");
		add(label_genre, "width 150!, height 30!, span 1, wrap");
		add(txt_albumartist, "width 150!, height 30!, span 1");
		add(txt_number_album, "width 150!, height 30!, span 1");
		add(txt_genre, "width 150!, height 30!, span 1, wrap");
		add(label_length, "width 150!, height 30!, span 1");
		add(label_cover, "width 150!, height 30!, span 1, wrap");
		add(txt_length, "width 150!, height 30!, span 1");
		add(cover, "width 30!, height 30!, span 1");

	}
	
	public void setSong(Vector<String> song) {
		reset();
		id = Integer.parseInt(song.get(0));
		txt_artist.setText(song.get(1));
		txt_title.setText(song.get(2));
		txt_album.setText(song.get(3));
		txt_albumartist.setText(song.get(4));
		txt_number_album.setText(song.get(5));
		txt_genre.setText(song.get(6));
		txt_length.setText(song.get(7));
		//cover
	}

	public int getId() {
		return id;
	}

	public void reset() {
		id = -1;
		txt_artist.setText("");
		txt_title.setText("");
	    txt_album.setText("");
	    txt_number_album.setText("");
	    txt_albumartist.setText("");
	    txt_genre.setText("");
	    txt_length.setText("");
	}
	
}
