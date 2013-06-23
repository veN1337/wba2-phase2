package djproject.gui.client.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class GUI_start extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MigLayout layout = new MigLayout();
	
	JPanel panel_start = new JPanel(layout);
	
	JButton btn_ok = new JButton("OK");
	JButton btn_reset = new JButton("Reset");
	
	JLabel label_password = new JLabel();
	JLabel label_user = new JLabel();
	
	JLabel dummy = new JLabel();
	
	JPasswordField txt_password = new JPasswordField();
	JTextField txt_user = new JTextField();
			
	public GUI_start(){
		
		//btn_ok.setEnabled(false);
		
		//Listener werden vergeben
		btn_ok.addActionListener(this);
		btn_reset.addActionListener(this);
		
		label_password.setText("Passwort:");
		label_user.setText("Benutzername:");
		
		txt_password.setText("asd");
		txt_user.setText("user2");
				
		//DJ Panel wird bestückt
		panel_start.add(label_user, "width 300!, height 30!, span 3, wrap,");
		panel_start.add(txt_user, "width 300!, height 30!, span 3, wrap");
		panel_start.add(label_password, "width 300!, height 30!, span 3, wrap");
		panel_start.add(txt_password, "width 300!, height 30!, span 3, wrap");
		panel_start.add(btn_ok, "width 100!,height 30!, span 1");
		panel_start.add(dummy, "width 90!,height 30!, span 1");
		panel_start.add(btn_reset, "width 100!,height 30!, span 1, wrap");
		

		//Panels werden dem GUI hinzugefügt
		add(panel_start);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(320,220));
		setResizable(false);
		setVisible(true);
		setTitle("DJ System - DJ Client");		
	}

	public void addDJ(String s){
		
	}
	
	public void addEvent(String s){

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn_ok)){
			ListenerHandler listener = new ListenerHandler(txt_user.getText(),txt_password.getText());
			this.dispose();
		}
	}
}
