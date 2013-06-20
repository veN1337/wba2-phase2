package djproject.gui.dj;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import djproject.rest.RESTServer;
import net.miginfocom.swing.MigLayout;

public class GUI_main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ListenerHandler_main listener;
	RESTServer rest;
	
	MigLayout layout = new MigLayout();
	MigLayout layout2 = new MigLayout();
	MigLayout layout3 = new MigLayout();
	MigLayout layout4 = new MigLayout();
	MigLayout layout5 = new MigLayout();
	
	JTabbedPane tab_pane = new JTabbedPane();
	JPanel panel_songs = new JPanel(layout);
	JPanel panel_wishes = new JPanel(layout2);
	JPanel panel_comments = new JPanel(layout3);
	JPanel panel_addsong = new JPanel(layout4);
	JPanel panel_editsong = new JPanel(layout5);
	
	JLabel label_songs = new JLabel();
	JLabel label_currsong = new JLabel();
	JLabel label_nextsong = new JLabel();
	
	JTextField txt_currsong = new JTextField();
	JTextField txt_nextsong = new JTextField();
	
	SongChoosePanel song_choose;
	
//	DefaultListModel<String> listModelSongs = new DefaultListModel<String>();
//	JList<String> list_songs = new JList<String>(listModelSongs);
	
			
	public GUI_main(ListenerHandler_main listenerHandler_main){
		listener = listenerHandler_main;
		rest = new RESTServer();
		rest.start();
		
		song_choose = new SongChoosePanel(listener);
		
		label_songs.setText("Drag song:");
		label_currsong.setText("Drop current song:");
		label_nextsong.setText("Drop next song:");
				
		//Listener werden vergeben

		
		txt_currsong.setEditable(false);
		txt_nextsong.setEditable(false);
		
//		list_songs.setDragEnabled(true);
//		list_songs.setTransferHandler(new ListTransferHandler());
//		list_songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		txt_currsong.setTransferHandler(new TableTransferHandler());
		txt_nextsong.setTransferHandler(new TableTransferHandler());
		
//		for (int index = 0; index < 10; index++) {
//            listModelSongs.addElement("Item " + index);
//        }
		
		
		//Song Control Panel wird bestückt
		panel_songs.add(label_songs, "width 350!, height 30!, wrap");
		panel_songs.add(song_choose, "width 550!, height 180!, wrap");
		panel_songs.add(label_currsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_currsong, "width 350!, height 30!, wrap");
		panel_songs.add(label_nextsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_nextsong, "width 350!, height 30!, wrap");
		
		//Song ändern Panel wird bestückt
		//panel_editsong.add(label_songs, "width 350!, height 30!, wrap");

		//Panels werden dem GUI hinzugefügt
		tab_pane.add("Song Control", panel_songs);
		tab_pane.add("Wünsche", panel_wishes);
		tab_pane.add("Kommentare", panel_comments);
		tab_pane.add("Song hinzufügen", panel_addsong);
		tab_pane.add("Song ändern", panel_editsong);
		add(tab_pane);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(550,500));
		setResizable(false);
		setVisible(true);
		setTitle("DJ System - DJ Client");
	}
	
	@Override
	public void processWindowEvent(WindowEvent e) {
	    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	        rest.stop();
	        dispose();
	    }
	}

}
