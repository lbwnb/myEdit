package myEdit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) {
		
	
		// TODO Auto-generated constructor stub
		FileWindow win = new FileWindow();
		win.pack();
		win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		win.setBounds(200,180,550,360);
		win.setVisible(true);
	
	}
}
