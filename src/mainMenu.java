import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class mainMenu extends JFrame{
	public mainMenu(int xPos, int yPos, final Event e){
		setLayout(null);
		setTitle("Main Menu");
		setSize(350, 225);
		setLocation(xPos, yPos);
		getContentPane().setBackground(Color.GRAY);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		//-----------------------------------------------
		
		Color buttonColor1 = new Color(50, 200, 56);
		
		JPanel buttonList = new JPanel();
		buttonList.setBackground(Color.GRAY);
		buttonList.setLocation(20, 10);
		buttonList.setSize(300, 100);
		buttonList.setLayout(new GridLayout(0, 2));

		
		JButton createNewEvent = new JButton("New Event");
		createNewEvent.setForeground(Color.DARK_GRAY);
		createNewEvent.setBackground(buttonColor1);
		
		createNewEvent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				newEventWindow newEventWin = new newEventWindow(e);
			}
		});
		
		JButton dataAnalyzer = new JButton("Data Analyzer");
		dataAnalyzer.setForeground(Color.DARK_GRAY);
		dataAnalyzer.setBackground(buttonColor1);
		
		dataAnalyzer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				
			}
		});
		
		JButton testDaySuite = new JButton("Test Day Suite");
		testDaySuite.setForeground(Color.DARK_GRAY);
		testDaySuite.setBackground(buttonColor1);
		
		testDaySuite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				tireSweepWindow tsw = new tireSweepWindow();
			}
		});
		
		JButton suspensionSetup = new JButton("Suspension Setup");
		suspensionSetup.setForeground(Color.DARK_GRAY);
		suspensionSetup.setBackground(buttonColor1);
		
		suspensionSetup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (e.eventName != null){
					setupWindow sw = new setupWindow(e);
				}
				else {
					warningWindow mew = new warningWindow("Make A New Event!");
				}
			}
		});
		
		JButton newLap = new JButton("New Lap");
		newLap.setForeground(Color.DARK_GRAY);
		newLap.setBackground(buttonColor1);
		
		newLap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (e.eventName != null){
					lapManager lmp = new lapManager(e);					
				}
				else {
					warningWindow mew = new warningWindow("Make A New Event!");
				}
			}
		});

		buttonList.add(createNewEvent);
		buttonList.add(suspensionSetup);
		buttonList.add(newLap);
		buttonList.add(dataAnalyzer);
		buttonList.add(testDaySuite);
		add(buttonList);

	}
}
