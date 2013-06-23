package djproject.gui.client;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import djproject.gui.client.gui.GUI_start;


public class MainClient {
	public static void main(String[] args) {
			try {
	            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            // If Nimbus is not available, you can set the GUI to another look
	            // and feel.
	        }
			GUI_start start = new GUI_start();
	}
}
