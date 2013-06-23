package djproject.gui.dj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;

import djproject.gui.dj.utils.NodeHandler;
import djproject.gui.dj.utils.RESTHandler;
import djproject.song_history.History;
import djproject.song_history.Next;
import djproject.song_history.Now;
import djproject.song_history.Nowandnext;
import djproject.songs.ObjectFactory;
import djproject.songs.Song;

public class ListenerHandler_main extends MouseMotionAdapter implements
MouseListener, KeyListener, ActionListener, ChangeListener,
CaretListener, DocumentListener {
	
	GUI_main gui;
	NodeHandler nh;
	LeafNode node;
	
	public ListenerHandler_main(NodeHandler nh, LeafNode node){
		gui = new GUI_main(this, nh, node);
		this.nh = nh;
		this.node = node;
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			if(e.getSource().equals(gui.tab_pane)) {
				if(((JTabbedPane) e.getSource()).getSelectedIndex() == 0) {
					gui.song_control_choose.updateSongList();
				} else if(((JTabbedPane) e.getSource()).getSelectedIndex() == 4) {
					gui.song_change_choose.updateSongList();
				} else if(((JTabbedPane) e.getSource()).getSelectedIndex() == 1) {
					gui.wish_choose.updateSongList();
				}
				else if(((JTabbedPane) e.getSource()).getSelectedIndex() == 2) {
					gui.comment_choose.updateCommentList();
				}
			}	
		} catch (NullPointerException ex) {
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gui.song_control_choose.btn_filter) || e.getSource().equals(gui.song_control_choose.txt_filter) || e.getSource().equals(gui.song_control_choose.btn_filterreset)) {
			if(e.getSource().equals(gui.song_control_choose.btn_filterreset)) {
				gui.song_control_choose.txt_filter.setText("");
			}
			gui.song_control_choose.updateSongList();
		}
		if(e.getSource().equals(gui.song_change_choose.btn_filter) || e.getSource().equals(gui.song_change_choose.txt_filter) || e.getSource().equals(gui.song_change_choose.btn_filterreset)) {
			if(e.getSource().equals(gui.song_change_choose.btn_filterreset)) {
				gui.song_change_choose.txt_filter.setText("");
			}
			gui.song_change_choose.updateSongList();
		}
		if(e.getSource().equals(gui.btn_updatesong)) {
			ObjectFactory ob = new ObjectFactory();
			Song s = ob.createSong();
			s.setId(gui.song_change_info.getId());
			s.setArtist(gui.song_change_info.txt_artist.getText());
			s.setTitle(gui.song_change_info.txt_title.getText());
			if(gui.song_change_info.txt_album.getText().length() > 0) {
				s.setAlbum(gui.song_change_info.txt_album.getText());
			} else {
				s.setAlbum(null);
			}
			if(gui.song_change_info.txt_albumartist.getText().length() > 0) {
				s.setAlbumArtist(gui.song_change_info.txt_albumartist.getText());
			} else {
				s.setAlbumArtist(null);
			}
			if(gui.song_change_info.txt_number_album.getText().length() > 0) {
				s.setNumberInAlbum(Integer.parseInt(gui.song_change_info.txt_number_album.getText()));
			} else {
				s.setNumberInAlbum(null);
			}
			String l[] = gui.song_change_info.txt_length.getText().split(":");	
			s.setLength(Integer.parseInt(l[0])*60 + Integer.parseInt(l[1]));
			s.setGenre(gui.song_change_info.txt_genre.getText());
			RESTHandler.updateSong(gui.song_change_info.getId(), s);
			gui.song_change_choose.updateSongList();
			gui.song_change_info.reset();
			gui.btn_updatesong.setEnabled(false);
			gui.btn_deletesong.setEnabled(false);
		}
		if(e.getSource().equals(gui.btn_deletesong)) {
			RESTHandler.deleteSong(gui.song_change_info.getId());
			gui.song_change_choose.updateSongList();
			gui.song_change_info.reset();
			gui.btn_updatesong.setEnabled(false);
			gui.btn_deletesong.setEnabled(false);
		}	
		if(e.getSource().equals(gui.btn_resetsong)) {
			gui.song_add_info.reset();
			gui.btn_addsong.setEnabled(false);
		}
		if(e.getSource().equals(gui.btn_addsong)) {
			ObjectFactory ob = new ObjectFactory();
			Song s = ob.createSong();
			s.setId(0);
			s.setArtist(gui.song_add_info.txt_artist.getText());
			s.setTitle(gui.song_add_info.txt_title.getText());
			if(gui.song_add_info.txt_album.getText().length() > 0) {
				s.setAlbum(gui.song_add_info.txt_album.getText());
			} else {
				s.setAlbum(null);
			}
			if(gui.song_add_info.txt_albumartist.getText().length() > 0) {
				s.setAlbumArtist(gui.song_add_info.txt_albumartist.getText());
			} else {
				s.setAlbumArtist(null);
			}
			if(gui.song_add_info.txt_number_album.getText().length() > 0) {
				s.setNumberInAlbum(Integer.parseInt(gui.song_add_info.txt_number_album.getText()));
			} else {
				s.setNumberInAlbum(null);
			}
			String l[] = gui.song_add_info.txt_length.getText().split(":");	
			s.setLength(Integer.parseInt(l[0])*60 + Integer.parseInt(l[1]));
			s.setGenre(gui.song_add_info.txt_genre.getText());
			RESTHandler.addSong(s);
			JOptionPane.showMessageDialog(gui, "Song added", "Success", JOptionPane.INFORMATION_MESSAGE);
			gui.song_add_info.reset();
			gui.btn_addsong.setEnabled(false);
		}
		if(e.getSource().equals(gui.btn_deletewish)) {
			RESTHandler.deleteWish(gui.wish_choose.getId());
			gui.wish_choose.updateSongList();
			gui.btn_deletewish.setEnabled(false);
		}
		if(e.getSource().equals(gui.btn_deletecomment)) {
			RESTHandler.deleteComment(gui.comment_choose.getId());
			gui.comment_choose.updateCommentList();
			gui.btn_deletecomment.setEnabled(false);
		}
		if(e.getSource().equals(gui.btn_updatehistory)) {
			Nowandnext nn = new Nowandnext();
			nn.setNow(new Now());
			nn.setNext(new Next());
			nn.getNow().setSong(new djproject.song_history.Song());
			nn.getNext().setSong(new djproject.song_history.Song());
			
			
			try {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
		        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
				XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
				
				nn.getNow().getSong().setSongId(Integer.parseInt(gui.txt_currsong.getText().split(" - ")[0]));
				nn.getNow().getSong().setTimePlayedAt(now);
				
				nn.getNext().getSong().setSongId(Integer.parseInt(gui.txt_nextsong.getText().split(" - ")[0]));
				nn.getNext().getSong().setTimePlayedAt(now);
				
				History h = RESTHandler.getHistory();
				
				StringWriter writer = new StringWriter();

				JAXBContext ctx = JAXBContext.newInstance(History.class);
								
				h.getSong().add(h.getNowandnext().getNow().getSong());
				
				h.setNowandnext(nn);	
				
				Marshaller marshaller = ctx.createMarshaller();
				//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				marshaller.marshal(h, writer);
				
				SimplePayload payload = new SimplePayload("historyupdate","history", writer.toString().replace("\n", "").replace("\r", "").replace(" ", "").replace("\t","").replace("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?>", ""));

		        PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>("historyupdate", payload);
		        
//		        SimplePayload payload = new SimplePayload("book","pubsub:test:book", "<book xmlns='pubsub:test:book'><title>Lord of the Rings</title></book>");
//
//		        PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>("asd", payload);

		        //System.out.println(writer.toString().replace("\n", "").replace("\r", "").replace(" ", "").replace("\t",""));
		        
		        //node.send(new PayloadItem<SimplePayload>("test" + System.currentTimeMillis(), new SimplePayload("book", "pubsub:test:book", "")));
				node.publish(item);
				
				RESTHandler.updateHistory(h);
				
			} catch (DatatypeConfigurationException | JAXBException e1) {
				e1.printStackTrace();
			}        

			gui.txt_currsong.setText("");
			gui.txt_nextsong.setText("");
			gui.btn_updatehistory.setEnabled(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(gui.song_add_info.txt_artist) || e.getSource().equals(gui.song_add_info.txt_title) || e.getSource().equals(gui.song_add_info.txt_genre) || e.getSource().equals(gui.song_add_info.txt_length)) {
			if(gui.song_add_info.txt_artist.getText().length() > 0 && gui.song_add_info.txt_title.getText().length() > 0 && gui.song_add_info.txt_genre.getText().length() > 0 && gui.song_add_info.txt_length.getText().length() > 0) {
				gui.btn_addsong.setEnabled(true);
			} else {
				gui.btn_addsong.setEnabled(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(gui.song_change_choose.table_songs)) {
			Vector<String> song = new Vector<String>();
			for(int i = 0; i < 8; i++) {
				song.add((String) gui.song_change_choose.table_songs.getValueAt(gui.song_change_choose.table_songs.getSelectedRow(), i));
			}
			gui.song_change_info.setSong(song);
			gui.btn_updatesong.setEnabled(true);
			gui.btn_deletesong.setEnabled(true);
		}
		if(e.getSource().equals(gui.wish_choose.table_wishes)) {
			gui.wish_choose.setId(Integer.parseInt((String) gui.wish_choose.table_wishes.getValueAt(gui.wish_choose.table_wishes.getSelectedRow(), 6)));
			gui.btn_deletewish.setEnabled(true);
		}
		if(e.getSource().equals(gui.comment_choose.table_comments)) {
			gui.comment_choose.setId(Integer.parseInt((String) gui.comment_choose.table_comments.getValueAt(gui.comment_choose.table_comments.getSelectedRow(), 4)));
			gui.btn_deletecomment.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if( (e.getDocument().getProperty("parent") == gui.txt_currsong) || (e.getDocument().getProperty("parent") == gui.txt_nextsong) ) {
			if(gui.txt_currsong.getText().length() > 0 && gui.txt_nextsong.getText().length() > 0)
			gui.btn_updatehistory.setEnabled(true);
		} else if(e.getDocument().getProperty("parent") == gui.txt_nextsong) {
			gui.btn_updatehistory.setEnabled(false);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}