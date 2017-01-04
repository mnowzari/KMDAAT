import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		buttonList.setSize(300, 150);
		buttonList.setLayout(new GridLayout(0, 2));

		
		JButton createNewEvent = new JButton("New Event");
		createNewEvent.setForeground(Color.DARK_GRAY);
		createNewEvent.setBackground(Color.CYAN);
		
		createNewEvent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				newEventWindow newEventWin = new newEventWindow(e);
			}
		});
		
		JButton dataAnalyzer = new JButton("Data Analyzer");
		dataAnalyzer.setForeground(Color.DARK_GRAY);
		dataAnalyzer.setBackground(Color.LIGHT_GRAY);
		
		dataAnalyzer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				if (e.eventExists == true){
					dataAnalyzerWindow dataAnalyzer = new dataAnalyzerWindow(e);
				}
				else {
					warningWindow ww1 = new warningWindow("Make a new Event!");
				}
			}
		});
		
		JButton testDaySuite = new JButton("Test Day Suite");
		testDaySuite.setForeground(Color.DARK_GRAY);
		testDaySuite.setBackground(Color.LIGHT_GRAY);
		
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
					warningWindow mew = new warningWindow("Make a new Event!");
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
					warningWindow mew = new warningWindow("Make a new Event!");
				}
			}
		});
		
		JButton closeEvent = new JButton("End Event");
		closeEvent.setForeground(Color.DARK_GRAY);
		closeEvent.setBackground(Color.CYAN);
		
		closeEvent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (e.eventName != null){
					closeEventWindow cew = new closeEventWindow(e);
				}
				else {
					warningWindow mew = new warningWindow("Make a new Event!");
				}
			}	
		});
		
		JButton loadEvent = new JButton("Load Event");
		loadEvent.setForeground(Color.DARK_GRAY);
		loadEvent.setBackground(Color.CYAN);
		
		loadEvent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					     "xml files (*.xml)", "xml");			    
				chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(mainMenu.this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filepath = chooser.getSelectedFile().getPath();
			    	e.loadFromPreviousXML(filepath);
			    }
			}
		});
		
		JButton leaderboard = new JButton("Leaderboard");
		leaderboard.setForeground(Color.DARK_GRAY);
		leaderboard.setBackground(buttonColor1);
		
		leaderboard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (e.eventExists == true){
					Thread ldwThread = new Thread(new leaderboardWindow(e));
					ldwThread.start();
				}
				else {
					warningWindow ww1 = new warningWindow("Make a new Event!");
				}
			}
		});
		
		JButton eventDash = new JButton("Event Info");
		eventDash.setForeground(Color.DARK_GRAY);
		eventDash.setBackground(buttonColor1);
		
		eventDash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (e.eventExists == true){
					eventDashboard edb = new eventDashboard(e);					
				}
				else {
					warningWindow ww1 = new warningWindow("Make a new Event!");
				}
			}
		});
		
		JButton tireMonitor = new JButton("Tire Monitor");
		tireMonitor.setForeground(Color.DARK_GRAY);
		tireMonitor.setBackground(buttonColor1);
		
		tireMonitor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				tireTempDisplay ttd = new tireTempDisplay(10, 40, e.dl);
				Thread ttdThread = new Thread(ttd);
				ttdThread.start();
			}
		});

		buttonList.add(createNewEvent);
		buttonList.add(loadEvent);
		buttonList.add(closeEvent);
		buttonList.add(eventDash);
		buttonList.add(suspensionSetup);
		buttonList.add(leaderboard);
		buttonList.add(newLap);
		buttonList.add(tireMonitor);
		buttonList.add(dataAnalyzer);
		buttonList.add(testDaySuite);
		add(buttonList);

	}
}
