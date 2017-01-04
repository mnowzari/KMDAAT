import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class closeEventWindow extends JFrame{
	Event e;
	boolean isClassLeader;
	boolean isDayLeader;
	public closeEventWindow(Event event){
		this.e = event;
		isClassLeader = false;
		isDayLeader = false;
		setLayout(null);
		setTitle("End Event");
		setSize(400, 250);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel panel = new JPanel();
		panel.setLocation(5, 5);
		panel.setSize(380, 150);
		panel.setBackground(Color.GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel filename = new JLabel("Filename: " + e.filename);
		
		JLabel btcLabel = new JLabel("Best Time in Class:");
		final JTextField btcField = new JTextField();
		btcField.setBackground(Color.LIGHT_GRAY);
		
		final JRadioButton selfBestClass = new JRadioButton("Class Leader");
		selfBestClass.setBackground(Color.GRAY);
		selfBestClass.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (selfBestClass.isSelected() == true){
					isClassLeader = true;
				}
				else if (selfBestClass.isSelected() == false){
					isClassLeader = false;
				}
			}
		});
		
		JLabel btdLabel = new JLabel("Best Time of Day:");
		final JTextField btdField = new JTextField();
		btdField.setBackground(Color.LIGHT_GRAY);
		
		final JRadioButton selfBestDay = new JRadioButton("Event Leader");
		selfBestDay.setBackground(Color.GRAY);
		selfBestDay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (selfBestDay.isSelected() == true){
					isDayLeader = true;
				}
				else if (selfBestDay.isSelected() == false){
					isDayLeader = false;
				}
			}
		});
		
		JPanel okButtonPane = new JPanel();
		okButtonPane.setSize(175, 35);
		okButtonPane.setBackground(Color.GRAY);
		okButtonPane.setLocation(100, 175);
		
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
				if (!btcField.getText().isEmpty() || selfBestClass.isSelected()){
					if (!btdField.getText().isEmpty() || selfBestDay.isSelected()){
						if (isClassLeader == false){
							e.BTC = Double.valueOf(btcField.getText());
						}
						else if (isClassLeader == true){
							e.BTC = e.getOverallFastestLapTime();
						}
						
						if (isDayLeader == false){
							e.BTD = Double.valueOf(btdField.getText());
						}
						else if (isClassLeader == true){
							e.BTD = e.getOverallFastestLapTime();
						}
						
						e.calcPercentBTC();
						e.calcPercentBTD();
						e.updateBTC_BTD_percentages();
						e.resetEvent();
						setVisible(false);
						dispose();		
					}
					else {
						warningWindow ww = new warningWindow("Check Your Inputs!");						
					}
				}
				else {
					warningWindow ww = new warningWindow("Check Your Inputs!");
				}
			}
		});
		
		panel.add(filename);
		
		panel.add(btcLabel);
		panel.add(btcField);
		panel.add(selfBestClass);
		
		panel.add(btdLabel);
		panel.add(btdField);
		panel.add(selfBestDay);
		
		okButtonPane.add(okButton);
		okButtonPane.add(cancelButton);
		
		add(panel);
		add(okButtonPane);
	}
}
