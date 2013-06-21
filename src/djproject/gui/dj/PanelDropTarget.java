package djproject.gui.dj;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public final class PanelDropTarget extends DropTarget {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SongInfoPanel info_panel;
	private  GUI_main gui;
	
	public PanelDropTarget(GUI_main gui) {
		this.info_panel = gui.song_add_info;
		this.gui = gui;
	}

	public synchronized void drop(DropTargetDropEvent evt) {
	    try {
	        evt.acceptDrop(DnDConstants.ACTION_COPY);
	        List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	        for (File file : droppedFiles) {
	        	Mp3File mp3 = new Mp3File(file.getAbsolutePath());
	        	info_panel.reset();
	        	Format formatter = new SimpleDateFormat( "mm:ss" );
	        	info_panel.txt_length.setText(formatter.format(mp3.getLengthInSeconds()*1000));
	        	if (mp3.hasId3v1Tag()) {
	        	      ID3v1 id3v1Tag = mp3.getId3v1Tag();
	        	      info_panel.txt_artist.setText(id3v1Tag.getArtist());
	        	      info_panel.txt_title.setText(id3v1Tag.getTitle());
	        	      info_panel.txt_album.setText(id3v1Tag.getAlbum());
	        	      info_panel.txt_number_album.setText(id3v1Tag.getTrack());
	        	      info_panel.txt_genre.setText(id3v1Tag.getGenreDescription());
	        	      gui.btn_addsong.setEnabled(true);
	        	} else if (mp3.hasId3v2Tag()) {
	        	      ID3v2 id3v2Tag = mp3.getId3v2Tag();
	        	      info_panel.txt_artist.setText(id3v2Tag.getArtist());
	        	      info_panel.txt_title.setText(id3v2Tag.getTitle());
	        	      info_panel.txt_album.setText(id3v2Tag.getAlbum());
	        	      info_panel.txt_number_album.setText(id3v2Tag.getTrack().contains("/") ? id3v2Tag.getTrack().split("/")[0] : id3v2Tag.getTrack());
	        	      info_panel.txt_albumartist.setText(id3v2Tag.getAlbumArtist());
	        	      info_panel.txt_genre.setText(id3v2Tag.getGenreDescription());
	        	      gui.btn_addsong.setEnabled(true);
	        	} else {
	        		gui.btn_addsong.setEnabled(false);
	        	}
	        	
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
}