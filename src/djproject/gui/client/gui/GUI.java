package djproject.gui.client.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class GUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ListenerHandler listener;
	
	JTabbedPane tab_pane = new JTabbedPane();
	MigLayout layout = new MigLayout();
	MigLayout layout2 = new MigLayout();
	MigLayout layout3 = new MigLayout();
	MigLayout layout4 = new MigLayout();
	MigLayout layout5 = new MigLayout();
	
	JPanel panel_list = new JPanel(layout);
	JPanel panel_events = new JPanel(layout2);
	JPanel panel_songs = new JPanel(layout3);
	JPanel panel_wishes = new JPanel(layout4);
	JPanel panel_comments = new JPanel(layout5);
	
	DefaultComboBoxModel<String> subs = new DefaultComboBoxModel<>();
	
	JComboBox cbox_subscriptions = new JComboBox(subs);
	JComboBox cbox_subscriptions2 = new JComboBox(subs);
	JComboBox cbox_subscriptions3 = new JComboBox(subs);
	
	JButton btn_subscribeDJ = new JButton("Subscribe");
	JButton btn_unsubscribeDJ = new JButton("Unsubscribe");
	JButton btn_subscribeEv = new JButton("Subscribe");
	JButton btn_unsubscribeEv = new JButton("Unsubscribe");
	JButton btn_wish = new JButton("Request");
	JButton btn_comment = new JButton("Send");
	JButton btn_meta = new JButton("Metadaten");
	
	JSlider sli_rating = new JSlider(1, 10);
	
	ButtonGroup grp_rating = new ButtonGroup();
	
	JTextArea txp_comments = new JTextArea();
	
	SongChoosePanel songs;
	public HistoryPanel history;
	
	DefaultListModel<String> listModelDJ = new DefaultListModel<String>();
	JList list_DJs = new JList(listModelDJ);
	
	DefaultListModel<String> listModelEvents = new DefaultListModel<String>();
	JList list_Events = new JList(listModelEvents);
	
	DefaultListModel<String> listModelSongs = new DefaultListModel<String>();
	JList list_Songs = new JList(listModelSongs);
	
	DefaultListModel<String> listModelHistory = new DefaultListModel<String>();
	JList list_History = new JList(listModelSongs);
	
	public GUI(ListenerHandler listen){
		listener = listen;
		
		//Listener werden vergeben
		btn_subscribeDJ.addActionListener(listener);
		btn_unsubscribeDJ.addActionListener(listener);
		btn_subscribeEv.addActionListener(listener);
		btn_unsubscribeEv.addActionListener(listener);
		btn_wish.addActionListener(listener);
		btn_comment.addActionListener(listener);
		
		cbox_subscriptions.addActionListener(listener);
		cbox_subscriptions2.addActionListener(listener);
		cbox_subscriptions3.addActionListener(listener);
		
		songs = new SongChoosePanel(listener);
		history = new HistoryPanel(listener);
		
		sli_rating.setMajorTickSpacing(9);
		sli_rating.setMinorTickSpacing(1);
		sli_rating.setPaintTicks(true);
		
				
		//Event Panel wird bestückt
		panel_events.add(new JScrollPane(list_Events), "dock west, width 300!, height 240!");
		panel_events.add(btn_subscribeEv, "width 140!,height 30!, wrap");
		panel_events.add(btn_unsubscribeEv, "width 140!,height 30!, wrap");
		
		//DJ Panel wird bestückt
		panel_list.add(new JScrollPane(list_DJs), "dock west, width 300!, height 240!");
		panel_list.add(btn_subscribeDJ, "width 140!,height 30!, wrap");
		panel_list.add(btn_unsubscribeDJ, "width 140!,height 30!, wrap");
		
		//Song Panel wird bestückt
		//panel_songs.add(lab_playingnow, "span 1, width 100!");
		//panel_songs.add(lab_currentsong, "span 2, wrap");
		panel_songs.add(cbox_subscriptions3, "width 430!, span 3, wrap");
		//panel_songs.add(btn_meta, "width 120!,height 30!, wrap");
		panel_songs.add(history, "width 430!, height 175!, span 3");
		
		//Wünsche Panel wird bestückt
		panel_wishes.add(cbox_subscriptions, "width 300!, span 2");
		panel_wishes.add(btn_wish, "width 120!,height 30!, wrap");
		panel_wishes.add(songs, "width 430!, height 200!, span 3");
		
		//Kommentar Panel wird bestückt
		panel_comments.add(cbox_subscriptions2, "width 300!, span 3");
		panel_comments.add(btn_comment, "width 120!,height 30!,span 2, wrap");
		panel_comments.add(new JLabel("Rating: "),"width 50!");
		panel_comments.add(sli_rating, "width 360!, span 4, wrap");
		panel_comments.add(new JScrollPane(txp_comments), "width 430!, height 165!, span 5");
		
		//Panels werden dem Tabbed Pane hinzugefügt
		//tab_pane.add("Events", panel_events);
		tab_pane.add("DJs", panel_list);
		tab_pane.add("Songhistory", panel_songs);
		tab_pane.add("Wishes", panel_wishes);
		tab_pane.add("Comments", panel_comments);
		add(tab_pane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(450,300));
		setResizable(false);
		setVisible(true);
		setTitle("DJ System - User Client");
	}

	public void addDJ(String s){
		listModelDJ.addElement(s);
	}
	
	public void addEvent(String s){
		listModelEvents.addElement(s);
	}
	
	public void updateSubs(String[] s){
		subs.removeAllElements();
		for(int i=0;i<s.length;i++){
			subs.addElement(s[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
