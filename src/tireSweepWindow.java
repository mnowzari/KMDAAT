import java.awt.Color;

import javax.swing.JFrame;

public class tireSweepWindow extends JFrame{
	public tireSweepWindow(){
		setLayout(null);
		setTitle("Test Day Suite");
		setSize(800, 600);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
