package djproject.gui.dj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import djproject.rest.RESTHandler;

public class ListenerHandler_main extends MouseMotionAdapter implements
MouseListener, KeyListener, ActionListener, ChangeListener,
CaretListener {
	
	GUI_main gui = new GUI_main(this);
	
	public ListenerHandler_main(){
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gui.song_choose1.btn_filter) || e.getSource().equals(gui.song_choose1.txt_filter) || e.getSource().equals(gui.song_choose1.btn_filterreset)) {
			if(e.getSource().equals(gui.song_choose1.btn_filterreset)) {
				gui.song_choose1.txt_filter.setText("");
			}
			gui.song_choose1.updateSongList();
		}
		if(e.getSource().equals(gui.song_choose2.btn_filter) || e.getSource().equals(gui.song_choose2.txt_filter) || e.getSource().equals(gui.song_choose2.btn_filterreset)) {
			if(e.getSource().equals(gui.song_choose2.btn_filterreset)) {
				gui.song_choose2.txt_filter.setText("");
			}
			gui.song_choose2.updateSongList();
		}
		if(e.getSource().equals(gui.btn_updatesong)) {
			
		}
		if(e.getSource().equals(gui.btn_deletesong)) {
			RESTHandler.deleteSong(gui.song_info.getId());
			gui.song_choose2.updateSongList();
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
	    
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(gui.song_choose2.table_songs)) {
			Vector<String> song = new Vector<String>();
			for(int i = 0; i < 8; i++) {
				song.add((String) gui.song_choose2.table_songs.getValueAt(gui.song_choose2.table_songs.getSelectedRow(), i));
			}
			gui.song_info.setSong(song);
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