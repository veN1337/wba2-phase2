package djproject.gui.dj;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ListTransferable implements Transferable {

	public static final DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(String.class, "java/ListItem");
    private String listString;

    public ListTransferable(String listString) {
        this.listString = listString;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{LIST_ITEM_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(LIST_ITEM_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        return listString;

    }

}