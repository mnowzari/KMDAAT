import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class COMPortSelectionWindow extends JFrame{
	public COMPortSelectionWindow(){
		setLayout(null);
		setTitle("Select COM Port");
		setSize(400, 150);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setVisible(true);
		//--------------------------------------------------
		
		JPanel panel = new JPanel();
		panel.setLocation(5, 5);
		panel.setSize(350, 60);
		panel.setBackground(Color.GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel selectLabel = new JLabel("Select COM port to use");
		final JTextField comText = new JTextField();
		comText.setBackground(Color.LIGHT_GRAY);
		
		JButton contButton = new JButton("Continue");
		contButton.setBackground(Color.LIGHT_GRAY);
		contButton.setForeground(Color.DARK_GRAY);
		
		contButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String comPort = comText.getText();
				if (comPort.contains("COM")){
					dataLogger dl = new dataLogger(comPort);
			        Event event = new Event(dl);
					mainWindow mw = new mainWindow(event);
					setVisible(false);
					dispose();	
				}
			}
		});
		
		panel.add(selectLabel);
		panel.add(comText);
		panel.add(contButton);
		add(panel);
	}
}
