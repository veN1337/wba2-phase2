package djproject.gui.dj;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
			new ListenerHandler_start();
	}
}
