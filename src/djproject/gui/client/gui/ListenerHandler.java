package djproject.gui.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import djproject.comments.Comment;
import djproject.gui.client.utils.RESTHandler;
import djproject.gui.client.utils.Subscriber;
import djproject.wishes.Wish;

public class ListenerHandler extends MouseMotionAdapter implements
MouseListener, KeyListener, ActionListener, ChangeListener,
CaretListener {
	
	GUI gui = new GUI(this);
	Subscriber sub;
	
	public ListenerHandler(String username,String password){
		sub = new Subscriber(gui, username,password);
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
		// TODO Auto-generated method stub
		if(e.getSource().equals(gui.btn_subscribeDJ)){
			sub.subscribe((String)gui.list_DJs.getSelectedValue());
			gui.updateSubs(sub.getSubs());
		}
		else if(e.getSource().equals(gui.btn_unsubscribeDJ)){
			sub.unsubscribe((String)gui.list_DJs.getSelectedValue());
			gui.updateSubs(sub.getSubs());
		}
		else if (e.getSource().equals(gui.btn_comment)){
			if(!gui.txp_comments.getText().isEmpty()){
				Comment c = new Comment();
				c.setContent(gui.txp_comments.getText().replace("\n"," - "));
				c.setRating(gui.sli_rating.getValue());
				c.setAuthor(sub.user);
				try {
					GregorianCalendar gregor = new GregorianCalendar();
					DatatypeFactory datatype = DatatypeFactory.newInstance();
					XMLGregorianCalendar now = datatype.newXMLGregorianCalendar(gregor);
					c.setTime(now);
				} catch (DatatypeConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(gui.txp_comments.getText());
				
				RESTHandler.addComment(c);
				
				JOptionPane.showMessageDialog(gui,"The comment has been sent.");
				gui.txp_comments.setText("");
				gui.sli_rating.setValue(5);
			}
		}
		else if (e.getSource().equals(gui.btn_wish)){
			Wish w = new Wish();
			w.setSongId(gui.songs.getSongID());
			RESTHandler.addWish(w);
			JOptionPane.showMessageDialog(gui,"The wish has been sent.");
		}
		else if(e.getSource().equals(gui.songs.btn_filter) || e.getSource().equals(gui.songs.txt_filter) || e.getSource().equals(gui.songs.btn_filterreset)) {
			if(e.getSource().equals(gui.songs.btn_filterreset)) {
				gui.songs.txt_filter.setText("");
			}
			gui.songs.updateSongList();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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