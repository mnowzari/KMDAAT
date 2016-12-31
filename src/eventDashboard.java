import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class eventDashboard extends JFrame{
	Event e;
	public eventDashboard(Event event){
		this.e = event;
		setLayout(null);
		setTitle("Event Dashboard");
		setSize(300, 150);
		getContentPane().setBackground(Color.BLACK);
		setLocation(660, 40);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel generalInfo = new JPanel();
		generalInfo.setSize(300, 110);
		generalInfo.setLocation(5, 5);
		generalInfo.setBackground(Color.BLACK);
		generalInfo.setLayout(new GridLayout(0, 1));
		
		JLabel eventDateLabel = new JLabel(e.date);
		eventDateLabel.setForeground(Color.WHITE);
		eventDateLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
		JLabel eventNameLabel = new JLabel(e.eventName + " at " + e.eventLocation);
		eventNameLabel.setForeground(Color.WHITE);
		eventNameLabel.setFont(new Font("Monospaced", Font.BOLD, 12));

		generalInfo.add(eventNameLabel);
		generalInfo.add(eventDateLabel);
		
		for (int i = 0; i < e.drivers.size(); i++){
			JLabel lbl = new JLabel("Driver " + (i + 1) + ": " +  e.drivers.get(i).name);
			lbl.setForeground(Color.WHITE);
			lbl.setFont(new Font("Monospaced", Font.BOLD, 12));
			generalInfo.add(lbl);
		}
		
		JLabel competingClassLabel = new JLabel("Competing Class: " + e.competingClass);
		competingClassLabel.setForeground(Color.WHITE);
		competingClassLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		generalInfo.add(competingClassLabel);
		add(generalInfo);
	}
}
