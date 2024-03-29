import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class warningWindow extends JFrame{
	public warningWindow(String msg){
		setLayout(null);
		setTitle("Achtung!");
		setSize(250, 115);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel pane = new JPanel();
		pane.setSize(200, 50);
		pane.setLocation(0, 0);
		pane.setBackground(Color.GRAY);
		final JLabel warningMsg = new JLabel(msg);
		
		JButton OK = new JButton("OK");
		OK.setBackground(Color.LIGHT_GRAY);
		OK.setForeground(Color.DARK_GRAY);
		
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		pane.add(warningMsg);
		pane.add(OK);
		add(pane);
	}
}
