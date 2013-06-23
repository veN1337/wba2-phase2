package djproject.gui.client.utils;

import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.TransferHandler;

public class TableTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof JTextField) && support.isDataFlavorSupported(TableTransferable.TABLE_ITEM_DATA_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(TableTransferable.TABLE_ITEM_DATA_FLAVOR);
                if (value instanceof String) {
                    Component component = support.getComponent();
                    if (component instanceof JTextField) {
                        ((JTextField)component).setText(((String)value));
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return accept;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof JTable) {
			JTable table = (JTable) c;
            Object value = table.getSelectedRow();
            if (value instanceof Integer) {
            	String li = "";
            	for(int i=0; i < 3; i++) {
            		li = li + (String) table.getValueAt((Integer) value, i) + " - ";
            	}
                t = new TableTransferable(li.substring(0, li.length()-3));
            }
        }
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        // Here you need to decide how to handle the completion of the transfer,
        // should you remove the item from the list or not...
    }

}
