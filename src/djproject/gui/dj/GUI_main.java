package djproject.gui.dj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	
	JPanel panel_drop = new JPanel(new MigLayout("insets 0 0 0 0"));
	
	JLabel label_songs = new JLabel();
	JLabel label_songs2 = new JLabel();
	JLabel label_currsong = new JLabel();
	JLabel label_nextsong = new JLabel();
	JLabel label_addsong = new JLabel();
	JLabel label_dropsong = new JLabel();
	
	JTextField txt_currsong = new JTextField();
	JTextField txt_nextsong = new JTextField();
	JTextField txt_artist = new JTextField();
	JTextField txt_title = new JTextField();
	JTextField txt_album = new JTextField();
	JTextField txt_albumartist = new JTextField();
	JTextField txt_number_album = new JTextField();
	JTextField txt_genre = new JTextField();
	JTextField txt_length = new JTextField();
	
	JButton btn_addsong = new JButton("Add");
	JButton btn_resetsong = new JButton("Reset");
	JButton btn_updatesong = new JButton("Update");
	JButton btn_deletesong = new JButton("Delete");
	
	SongChoosePanel song_choose1;
	SongChoosePanel song_choose2;

	SongInfoPanel song_info;
	SongInfoPanel song_info2;
	
	public GUI_main(ListenerHandler_main listenerHandler_main){
		listener = listenerHandler_main;
		rest = new RESTServer();
		rest.start();
		
		song_choose1 = new SongChoosePanel(listener, true);
		song_choose2 = new SongChoosePanel(listener, false);
		
		song_info = new SongInfoPanel(listener);
		song_info2 = new SongInfoPanel(listener);
		
		panel_drop.setBorder(BorderFactory.createDashedBorder(new Color(0, 0, 0)));
		panel_drop.setDropTarget(new PanelDropTarget(song_info2));
				
		label_songs.setText("Drag song:");
		label_currsong.setText("Drop current song:");
		label_nextsong.setText("Drop next song:");
		label_addsong.setText("Add song:");
		label_dropsong.setText("Or drop song:");
				
		label_songs2.setText("Choose song:");
		
		//Listener werden vergeben
		btn_addsong.addActionListener(listener);
		btn_resetsong.addActionListener(listener);
		btn_updatesong.addActionListener(listener);
		btn_deletesong.addActionListener(listener);
		
		txt_currsong.setEditable(false);
		txt_nextsong.setEditable(false);
		
		txt_currsong.setTransferHandler(new TableTransferHandler());
		txt_nextsong.setTransferHandler(new TableTransferHandler());		
		
		//Song Control Panel wird bestückt
		panel_songs.add(label_songs, "width 350!, height 30!, wrap");
		panel_songs.add(song_choose1, "width 550!, height 180!, wrap");
		panel_songs.add(label_currsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_currsong, "width 350!, height 30!, wrap");
		panel_songs.add(label_nextsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_nextsong, "width 350!, height 30!, wrap");
		
		//Song hinzufügen Panel wird bestückt
		JLabel dummy = new JLabel();
		panel_addsong.add(label_addsong, "width 550!, height 30!, span 3, wrap");
		panel_addsong.add(song_info2, "width 550!, height 220!, span 3, wrap");
		panel_addsong.add(label_dropsong, "width 550!, height 30!, span 3, wrap");
		panel_addsong.add(panel_drop, "width 150!, height 150!, span 1, wrap");
		panel_addsong.add(btn_addsong, "width 150!, height 30!, span 1");
		panel_addsong.add(dummy, "width 150!, height 30!, span 1");
		panel_addsong.add(btn_resetsong, "width 150!, height 30!, span 1");
		
		
		//Song ändern Panel wird bestückt
		JLabel dummy2 = new JLabel();
		
		panel_editsong.add(label_songs2, "width 450!, height 30!, span 3, wrap");
		panel_editsong.add(song_choose2, "width 550!, height 180!, span 3, wrap");
		panel_editsong.add(song_info, "width 550!, height 220!, span 3, wrap");
		panel_editsong.add(btn_updatesong, "width 150!, height 30!, span 1");
		panel_editsong.add(dummy2, "width 150!, height 30!, span 1");
		panel_editsong.add(btn_deletesong, "width 150!, height 30!, span 1");
		

		//Panels werden dem GUI hinzugefügt
		tab_pane.add("Song Control", panel_songs);
		tab_pane.add("Wishes", panel_wishes);
		tab_pane.add("Comments", panel_comments);
		tab_pane.add("Add song", panel_addsong);
		tab_pane.add("Change song", panel_editsong);
		add(tab_pane);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(550,550));
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
