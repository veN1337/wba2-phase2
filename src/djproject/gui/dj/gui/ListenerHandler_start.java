package djproject.gui.dj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.LeafNode;

import djproject.gui.dj.utils.NodeHandler;

public class ListenerHandler_start extends MouseMotionAdapter implements
MouseListener, KeyListener, ActionListener, ChangeListener,
CaretListener {
	
	GUI_start gui = new GUI_start(this);
	//Subscriber sub = new Subscriber(gui);
	
	public ListenerHandler_start(){
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
		if(e.getSource().equals(gui.btn_reset)){
			//gui.txt_event.setText("");
			gui.txt_dj.setText("");
			gui.btn_ok.setEnabled(false);
			//subscribtion methode
		}
		if(e.getSource().equals(gui.btn_ok)){
			NodeHandler nh = new NodeHandler();
			try {
				nh.connect("user1", "asd", "java");
				LeafNode node = nh.createNode(gui.txt_dj.getText());
				new ListenerHandler_main(nh, node);
				gui.dispose();
			} catch (XMPPException e2) {
				if(e2.getXMPPError().getCode() == 409) {
					System.out.println("node already exists");
					int dialogResult = JOptionPane.showConfirmDialog(gui, "DJ already logged in\nOverride?", "Warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						try {
							nh.deleteNode(gui.txt_dj.getText());
						} catch (XMPPException e1) {
							if(e2.getXMPPError().getCode() == 404) {
								System.out.println("node does not exist");
								//JOptionPane.showMessageDialog(gui, "DJ already logged in", "Failed", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					try {
						nh.disconnect();
					} catch (XMPPException e1) {
						System.out.println("not connected");
					}
//				} else if(e2.getXMPPError().getCode() == 404) {
//					System.out.println("node does not exist");
//					JOptionPane.showMessageDialog(gui, "DJ already logged in", "Failed", JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println("unknow error:" + e2.getXMPPError().getCode());
					JOptionPane.showMessageDialog(gui, "Unknow error", "Failed", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NullPointerException e3) {
				System.out.println("not connected");
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource().equals(gui.txt_event) || e.getSource().equals(gui.txt_dj)){
//			if(gui.txt_event.getText().length() > 0 && gui.txt_dj.getText().length() > 0) {
//				gui.btn_ok.setEnabled(true);
//			} else {
//				gui.btn_ok.setEnabled(false);
//			}
//		}
		if(e.getSource().equals(gui.txt_dj)){
			if(gui.txt_dj.getText().length() > 0) {
				gui.btn_ok.setEnabled(true);
			} else {
				gui.btn_ok.setEnabled(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
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