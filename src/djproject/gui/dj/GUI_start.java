package djproject.gui.dj;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class GUI_start extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ListenerHandler_start listener;

	MigLayout layout = new MigLayout();
	
	JPanel panel_start = new JPanel(layout);
	
	JButton btn_ok = new JButton("OK");
	JButton btn_reset = new JButton("Reset");
	
	JLabel label_event = new JLabel();
	JLabel label_dj = new JLabel();
	
	JLabel dummy = new JLabel();
	
	JTextField txt_event = new JTextField();
	JTextField txt_dj = new JTextField();
			
	public GUI_start(ListenerHandler_start listen){
		listener = listen;
		
		btn_ok.setEnabled(false);
		
		//Listener werden vergeben
		btn_ok.addActionListener(listener);
		btn_reset.addActionListener(listener);
		
		txt_event.addKeyListener(listener);
		txt_dj.addKeyListener(listener);
		
		label_event.setText("Event Name:");
		
		label_dj.setText("DJ Name:");
				
		//DJ Panel wird bestückt
		panel_start.add(label_event, "width 300!, height 30!, span 3, wrap");
		panel_start.add(txt_event, "width 300!, height 30!, span 3, wrap");
		panel_start.add(label_dj, "width 300!, height 30!, span 3, wrap,");
		panel_start.add(txt_dj, "width 300!, height 30!, span 3, wrap");
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
		
	}
}
