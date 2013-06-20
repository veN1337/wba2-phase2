package djproject.gui.dj;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import djproject.rest.RESTHandler;
import djproject.rest.RESTServer;
import djproject.songs.Song;

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
	
	JLabel label_songs = new JLabel();
	JLabel label_currsong = new JLabel();
	JLabel label_nextsong = new JLabel();
	JLabel label_filtertype = new JLabel();
	JLabel label_filtertext = new JLabel();
	
	JButton btn_filter = new JButton("Filter");
	JButton btn_filterreset = new JButton("Reset");
	
	JTextField txt_currsong = new JTextField();
	JTextField txt_nextsong = new JTextField();
	
	DefaultListModel<String> listModelSongs = new DefaultListModel<String>();
	JList<String> list_songs = new JList<String>(listModelSongs);
	
	JPanel panel_filter = new JPanel(layout5);
	
	DefaultTableModel tableModelSongs = new DefaultTableModel();
	JTable table_songs = new JTable(tableModelSongs);
	
	DefaultComboBoxModel<String> cboxModelFilter = new DefaultComboBoxModel<>();
	JComboBox<String> cbox_filter = new JComboBox<String>(cboxModelFilter);
	
	JTextField txt_filter = new JTextField();
			
	public GUI_main(ListenerHandler_main listenerHandler_main){
		listener = listenerHandler_main;
		rest = new RESTServer();
		rest.start();
		
		table_songs.setAutoCreateRowSorter(true);
		table_songs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_songs.setDragEnabled(true);
		table_songs.setTransferHandler(new TableTransferHandler());
		table_songs.getTableHeader().setReorderingAllowed(false);
		
		tableModelSongs.addColumn("ID");
		tableModelSongs.addColumn("Artist");
		tableModelSongs.addColumn("Title");
		tableModelSongs.addColumn("Album");
		tableModelSongs.addColumn("Genre");
		tableModelSongs.addColumn("Length");
				
		//Listener werden vergeben
		btn_filter.addActionListener(listener);
		btn_filterreset.addActionListener(listener);
		txt_filter.addActionListener(listener);
		
		label_songs.setText("Drag song:");
		label_currsong.setText("Drop current song:");
		label_nextsong.setText("Drop next song:");
		label_filtertype.setText("Filter by:");
		label_filtertext.setText("Filter keyword:");
		
		txt_currsong.setEditable(false);
		txt_nextsong.setEditable(false);
		
//		list_songs.setDragEnabled(true);
//		list_songs.setTransferHandler(new ListTransferHandler());
//		list_songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		txt_currsong.setTransferHandler(new TableTransferHandler());
		txt_nextsong.setTransferHandler(new TableTransferHandler());
		
		updateSongList();
		
//		for (int index = 0; index < 10; index++) {
//            listModelSongs.addElement("Item " + index);
//        }
		
		cboxModelFilter.addElement("Artist");
		cboxModelFilter.addElement("Title");
		cboxModelFilter.addElement("Genre");
		
		panel_filter.add(label_filtertype, "width 150!, height 30!, wrap");
		panel_filter.add(cbox_filter, "width 150!, height 30!, wrap");
		panel_filter.add(label_filtertext, "width 150!, height 30!, wrap");
		panel_filter.add(txt_filter, "width 150!, height 30!, wrap");
		panel_filter.add(btn_filter, "width 150!, height 30!");
		
		//Song Control Panel wird bestückt
		panel_songs.add(label_songs, "width 350!, height 30!, wrap");
		panel_songs.add(new JScrollPane(table_songs), "width 350!, height 180!");
		panel_songs.add(panel_filter, "width 320!, height 180!, wrap");
		panel_songs.add(label_currsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_currsong, "width 350!, height 30!, wrap");
		panel_songs.add(label_nextsong, "width 150!, height 30!, wrap");
		panel_songs.add(txt_nextsong, "width 350!, height 30!, wrap");

		//Panels werden dem GUI hinzugefügt
		tab_pane.add("Song Control", panel_songs);
		tab_pane.add("Wünsche", panel_wishes);
		tab_pane.add("Kommentare", panel_comments);
		tab_pane.add("Song hinzufügen", panel_addsong);
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

	public void updateSongList() {
		tableModelSongs.getDataVector().removeAllElements();
		for(Song s: RESTHandler.getSongs(String.valueOf(cbox_filter.getSelectedItem()), txt_filter.getText()).getSong()) {
			Vector<String> v = new Vector<String>();
			v.add(String.valueOf(s.getId()));
			v.add(s.getArtist());
			v.add(s.getTitle());
			v.add(s.getAlbum());
			v.add(s.getGenre());
			Format formatter = new SimpleDateFormat( "mm:ss" );
			v.add(String.valueOf(formatter.format((s.getLength()*1000))));
			tableModelSongs.addRow(v);
		}
		
		for(int j=0; j < table_songs.getColumnModel().getColumnCount(); j++) {

			TableColumn column = table_songs.getColumnModel().getColumn(j);
	
			//Set the width to be the header width of this column
			TableCellRenderer headerRenderer = table_songs.getTableHeader().getDefaultRenderer();
			Component comp = headerRenderer.getTableCellRendererComponent(table_songs, column.getHeaderValue(),	false, false, 0, 0);
			int width = comp.getPreferredSize().width;
	
			//Set the width to be the larger of the header or a cell width in this column
			for (int i=0; i< table_songs.getRowCount(); i++)
			{
				TableCellRenderer renderer = table_songs.getCellRenderer(i, j);
				Component c = renderer.getTableCellRendererComponent(table_songs, table_songs.getValueAt(i, j), false, false, i, j);
				width = Math.max(width, c.getPreferredSize().width);
				
			}
	
			//Usethe width of the largest component (header or cell) plus a margin on other side.
			column.setMinWidth(width + 2);
			column.setMaxWidth(width + 2);
			column.setPreferredWidth(width + 2);
		}
		tableModelSongs.fireTableDataChanged();
		table_songs.repaint();

	}

}
