package djproject.gui.dj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jivesoftware.smackx.pubsub.LeafNode;

import djproject.rest.RESTHandler;
import djproject.songs.ObjectFactory;
import djproject.songs.Song;

public class ListenerHandler_main extends MouseMotionAdapter implements
MouseListener, KeyListener, ActionListener, ChangeListener,
CaretListener {
	
	GUI_main gui;
//	NodeHandler nh;
//	LeafNode node;
	
	public ListenerHandler_main(NodeHandler nh, LeafNode node){
		gui = new GUI_main(this, nh, node);
//		this.nh = nh;
//		this.node = node;
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
			gui.wish_choose.setId(Integer.parseInt((String) gui.wish_choose.table_wishes.getValueAt(gui.wish_choose.table_wishes.getSelectedRow(), 4)));
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
	

}