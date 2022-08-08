package utilities;

import javax.swing.JFrame;

public class WindowBridge {
	public static void switchWindowsPermanent(JFrame cur_frame,JFrame next_frame) {

		next_frame.setVisible(true);
		cur_frame.dispose();

		next_frame.requestFocus();
	}
	public static void switchWindowsTemp(JFrame cur_frame, JFrame next_frame) {
		next_frame.setVisible(true);
		next_frame.requestFocus();
		cur_frame.setEnabled(false);
	}
	
	public static void revert(JFrame cur_frame, JFrame next_frame) {
		next_frame.setEnabled(true);
		next_frame.requestFocus();
	}
}
