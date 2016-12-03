import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class makeEventWarningWindow extends JFrame{
	public makeEventWarningWindow(){
		setLayout(null);
		setTitle("Achtung!");
		setSize(220, 75);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel pane = new JPanel();
		pane.setSize(200, 150);
		pane.setLocation(0, 0);
		pane.setBackground(Color.GRAY);
		final JLabel warningMsg = new JLabel("Make An Event First!");
		
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
