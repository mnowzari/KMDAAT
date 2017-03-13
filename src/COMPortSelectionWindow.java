import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class COMPortSelectionWindow extends JFrame{
	public COMPortSelectionWindow(){
		setLayout(null);
		setTitle("Select COM Port");
		setSize(400, 175);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setVisible(true);
		//--------------------------------------------------
		ArrayList<String> availComs = new portIdentifier().list();

		JPanel panel = new JPanel();
		panel.setLocation(5, 5);
		panel.setSize(350, 80);
		panel.setBackground(Color.GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel selectLabel = new JLabel("Select COM port and baud rate to use:");
		final JComboBox comList = new JComboBox(availComs.toArray());
		comList.setSelectedIndex(availComs.size() - 1);
		
		String[] baudRates = new String[12];
		baudRates[0] = String.valueOf(300);
		baudRates[1] = String.valueOf(1200);
		baudRates[2] = String.valueOf(2400);
		baudRates[3] = String.valueOf(4800);
		baudRates[4] = String.valueOf(9600);
		baudRates[5] = String.valueOf(19200);
		baudRates[6] = String.valueOf(38400);
		baudRates[7] = String.valueOf(57600);
		baudRates[8] = String.valueOf(74880);
		baudRates[9] = String.valueOf(115200);
		baudRates[10] = String.valueOf(230400);
		baudRates[11] = String.valueOf(250000);

		final JComboBox baudRateList = new JComboBox(baudRates);
		baudRateList.setSelectedIndex(9);
		
		JPanel OKPane = new JPanel();
		OKPane.setLocation(10, 90);
		OKPane.setSize(75, 35);
		OKPane.setBackground(Color.GRAY);
		
		JButton contButton = new JButton("Continue");
		contButton.setBackground(Color.LIGHT_GRAY);
		contButton.setForeground(Color.DARK_GRAY);
		
		JPanel cancelPane = new JPanel();
		cancelPane.setLocation(90, 90);
		cancelPane.setSize(75, 35);
		cancelPane.setBackground(Color.GRAY);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setForeground(Color.DARK_GRAY);
		
		contButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String comPort = (String) comList.getSelectedItem();
				String baudRate = (String) baudRateList.getSelectedItem();
				if (comPort.contains("COM")){
					dataLogger dl = new dataLogger(comPort, baudRate);
			        Event event = new Event(dl);
					mainWindow mw = new mainWindow(event);
					setVisible(false);
					dispose();	
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		panel.add(selectLabel);
		panel.add(comList);
		panel.add(baudRateList);
		OKPane.add(contButton);
		cancelPane.add(cancelButton);
		add(cancelPane);
		add(OKPane);
		add(panel);
	}
}
