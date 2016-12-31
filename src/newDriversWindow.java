import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class newDriversWindow extends JFrame{
	public newDriversWindow(final Event e){
		setLayout(null);
		setTitle("Event Drivers");
		setSize(285, 300);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		JPanel inputPane = new JPanel();
		inputPane.setBackground(Color.GRAY);
		inputPane.setSize(250, 130);
		inputPane.setLocation(10, 20);
		inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
		
		final JLabel driver1Label = new JLabel("Driver 1:");
		final JTextField driver1Field = new JTextField(10);
		driver1Field.setBackground(Color.LIGHT_GRAY);
		
		JLabel driver2Label = new JLabel("Driver 2 (Optional):");
		final JTextField driver2Field = new JTextField(10);
		driver2Field.setBackground(Color.LIGHT_GRAY);
		
		JLabel driver3Label = new JLabel("Driver 3 (Optional):");
		final JTextField driver3Field = new JTextField(10);
		driver3Field.setBackground(Color.LIGHT_GRAY);
		
		JPanel okButtonPane = new JPanel();
		okButtonPane.setSize(175, 35);
		okButtonPane.setLocation(60, 225);
		okButtonPane.setBackground(Color.GRAY);
		
		JButton okButton = new JButton("OK");
		okButton.setBackground(Color.LIGHT_GRAY);
		okButton.setForeground(Color.DARK_GRAY);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setForeground(Color.DARK_GRAY);
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (!driver1Field.getText().isEmpty()){
					Driver driver1 = new Driver(driver1Field.getText());
					e.drivers.add(driver1);
					
					if (!driver2Field.getText().isEmpty()){
						Driver driver2 = new Driver(driver2Field.getText());
						e.drivers.add(driver2);
						
						if (!driver3Field.getText().isEmpty()){
							Driver driver3 = new Driver(driver3Field.getText());
							e.drivers.add(driver3);
						}
					}
					eventDashboard ed = new eventDashboard(e);
					leaderboardWindow lbw = new leaderboardWindow(e);
					Thread leaderboard = new Thread(lbw);
					leaderboard.start();
					
					e.createNewXML();
					
					setVisible(false);
					dispose();
				}
			}
		});
		okButtonPane.add(okButton);
		okButtonPane.add(cancelButton);
		inputPane.add(driver1Label);
		inputPane.add(driver1Field);
		inputPane.add(driver2Label);
		inputPane.add(driver2Field);
		inputPane.add(driver3Label);
		inputPane.add(driver3Field);
		add(inputPane);
		add(okButtonPane);
	}
}
