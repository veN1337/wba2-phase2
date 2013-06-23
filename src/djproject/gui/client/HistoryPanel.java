package djproject.gui.client;

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
import javax.swing.table.TableRowSorter;

import djproject.gui.client.RESTHandler;
import djproject.gui.dj.DateStringComparator;
import djproject.song_history.Song;

import net.miginfocom.swing.MigLayout;

public class HistoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5607180063105514228L;
	
	MigLayout layout = new MigLayout("insets 0 0 0 0");
	MigLayout layout2 = new MigLayout();
	
	DefaultTableModel tableModelHistory = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	JTable table_history = new JTable(tableModelHistory);
	TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModelHistory);
	
	public HistoryPanel(ListenerHandler listener) {
		
		setLayout(layout);	
		
		table_history.setAutoCreateRowSorter(true);
		table_history.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_history.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table_history.addMouseListener(listener);
		//sorter.setComparator(3, new DateStringComparator());
		
		table_history.getTableHeader().setReorderingAllowed(false);
		
		tableModelHistory.addColumn("Time played at");
		tableModelHistory.addColumn("Artist");
		tableModelHistory.addColumn("Title");
		tableModelHistory.addColumn("Album");
		tableModelHistory.addColumn("Album Artist");
		tableModelHistory.addColumn("Number in Album");
		tableModelHistory.addColumn("Genre");
		tableModelHistory.addColumn("Length");
		tableModelHistory.addColumn("ID");
		
		updateList();
		
		this.add(new JScrollPane(table_history), "width 430!, height 175!");

	}
	
	public void updateList() {
		table_history.setRowSorter(null);
		tableModelHistory.getDataVector().removeAllElements();
		for(Song s: RESTHandler.getHistory().getSong()) {
			Vector<String> v = new Vector<String>();
			Format formatter = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" );
			v.add(String.valueOf(formatter.format((s.getTimePlayedAt().toGregorianCalendar().getTime()))));
			djproject.songs.Song s2 = RESTHandler.getSong(s.getSongId());
			v.add(s2.getArtist());
			v.add(s2.getTitle());
			v.add(s2.getAlbum());
			v.add(s2.getAlbumArtist());			
			v.add((s2.getNumberInAlbum() == null) ? "" : String.valueOf(s2.getNumberInAlbum()));
			v.add(s2.getGenre());
			formatter = new SimpleDateFormat( "mm:ss" );
			v.add(String.valueOf(formatter.format((s2.getLength()*1000))));
			v.add(String.valueOf(s2.getId()));
			tableModelHistory.addRow(v);
		}
		
		autoFitTable();
		tableModelHistory.fireTableDataChanged();
		table_history.setRowSorter(sorter);
		sortByColumn(0);

	}
	
	public int getSongID(){
		return Integer.parseInt((String)(table_history.getValueAt(table_history.getSelectedRow(),0)));
	}

	public void autoFitTable() {
		for(int j=0; j < table_history.getColumnModel().getColumnCount(); j++) {

			//Set the width to be the header width of this column
			TableCellRenderer headerRenderer = table_history.getTableHeader().getDefaultRenderer();
			TableColumn column = table_history.getColumnModel().getColumn(j);
			Component comp = headerRenderer.getTableCellRendererComponent(table_history, column.getHeaderValue(),	false, false, 0, 0);
			int width = comp.getPreferredSize().width;
	
			//Set the width to be the larger of the header or a cell width in this column
			for (int i=0; i< table_history.getRowCount(); i++)
			{
				TableCellRenderer renderer = table_history.getCellRenderer(i, j);
				Component c = renderer.getTableCellRendererComponent(table_history, table_history.getValueAt(i, j), false, false, i, j);
				width = Math.max(width, c.getPreferredSize().width);
			}
	
			//Usethe width of the largest component (header or cell) plus a margin on other side.
			column.setMinWidth(width + 14);
			column.setMaxWidth(width + 14);
			column.setPreferredWidth(width + 14);
		}
	}
	
	public void replaceColumn(int index, String newname) {

		JTableHeader th = table_history.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setHeaderValue(newname);
		
		autoFitTable();
	
	}
	
	public void sortByColumn(int index) {
		RowSorter<? extends TableModel> sorter = table_history.getRowSorter();
    	ArrayList<RowSorter.SortKey> list = new ArrayList<RowSorter.SortKey>();
    	list.add( new RowSorter.SortKey(index, SortOrder.DESCENDING) );
    	sorter.setSortKeys(list);
	}

}
