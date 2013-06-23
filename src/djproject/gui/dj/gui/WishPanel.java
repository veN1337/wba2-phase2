package djproject.gui.dj.gui;

import java.awt.Component;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import djproject.gui.dj.utils.RESTHandler;
import djproject.songs.Song;
import djproject.wishes.Wish;

import net.miginfocom.swing.MigLayout;

public class WishPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5607180063105514228L;
	
	MigLayout layout = new MigLayout("insets 0 0 0 0");
	MigLayout layout2 = new MigLayout();
	
	DefaultTableModel tableModelWishes = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	JTable table_wishes = new JTable(tableModelWishes);

	private int id;
	
	public WishPanel(ListenerHandler_main listener) {
		
		setLayout(layout);
		
		table_wishes.setAutoCreateRowSorter(true);
		table_wishes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_wishes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		table_wishes.addMouseListener(listener);
		
		table_wishes.getTableHeader().setReorderingAllowed(false);
		
		tableModelWishes.addColumn("Count");
		tableModelWishes.addColumn("Artist");
		tableModelWishes.addColumn("Title");
		tableModelWishes.addColumn("Album");
		tableModelWishes.addColumn("Genre");
		tableModelWishes.addColumn("Length");
		tableModelWishes.addColumn("ID");
		
		updateSongList();
		
		this.add(new JScrollPane(table_wishes), "width 530!, height 180!");

	}
	
	public void updateSongList() {
		table_wishes.clearSelection();
		table_wishes.setRowSorter(null);
		tableModelWishes.getDataVector().removeAllElements();
		for(Wish w: RESTHandler.getWishes().getWish()) {
			Vector<String> v = new Vector<String>();
			v.removeAllElements();
			id = w.getId();
			v.add(String.valueOf(w.getCount()));
			Song s = RESTHandler.getSongById(w.getSongId());
			v.add(s.getArtist());
			v.add(s.getTitle());
			v.add(s.getAlbum());
			v.add(s.getGenre());
			Format formatter = new SimpleDateFormat( "mm:ss" );
			v.add(String.valueOf(formatter.format((s.getLength()*1000))));
			v.add(String.valueOf(w.getId()));
			tableModelWishes.addRow(v);
		}
		
		autoFitTable();
		tableModelWishes.fireTableDataChanged();
		table_wishes.setAutoCreateRowSorter(true);
		sortByColumn(0);

	}

	public void autoFitTable() {
		for(int j=0; j < table_wishes.getColumnModel().getColumnCount(); j++) {

			//Set the width to be the header width of this column
			TableCellRenderer headerRenderer = table_wishes.getTableHeader().getDefaultRenderer();
			TableColumn column = table_wishes.getColumnModel().getColumn(j);
			Component comp = headerRenderer.getTableCellRendererComponent(table_wishes, column.getHeaderValue(),	false, false, 0, 0);
			int width = comp.getPreferredSize().width;
	
			//Set the width to be the larger of the header or a cell width in this column
			for (int i=0; i< table_wishes.getRowCount(); i++)
			{
				TableCellRenderer renderer = table_wishes.getCellRenderer(i, j);
				Component c = renderer.getTableCellRendererComponent(table_wishes, table_wishes.getValueAt(i, j), false, false, i, j);
				width = Math.max(width, c.getPreferredSize().width);
			}
	
			//Usethe width of the largest component (header or cell) plus a margin on other side.
			column.setMinWidth(width + 14);
			column.setMaxWidth(width + 14);
			column.setPreferredWidth(width + 14);
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void replaceColumn(int index, String newname) {

		JTableHeader th = table_wishes.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setHeaderValue(newname);
		
		autoFitTable();
	
	}
	
	public void sortByColumn(int index) {
		RowSorter<? extends TableModel> sorter = table_wishes.getRowSorter();
    	ArrayList<RowSorter.SortKey> list = new ArrayList<RowSorter.SortKey>();
    	list.add(new RowSorter.SortKey(index, SortOrder.DESCENDING));
    	sorter.setSortKeys(list);
	}

}

