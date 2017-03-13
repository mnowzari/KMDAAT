import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class dataAnalyzerWindow extends JFrame{
	Event e;
	Driver selectedDriver;
	String tire = "fr";
	public dataAnalyzerWindow(Event event){
		this.e = event;
		setLayout(null);
		setTitle("Data Analyzer");
		setSize(400, 480);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		selectedDriver = new Driver("null");
		final Color buttonColor1 = new Color(50, 200, 56);

		JPanel radioPanel = new JPanel();
		radioPanel.setBackground(Color.GRAY);
		radioPanel.setSize(325, 40);
		radioPanel.setLocation(10, 10);
		
		final ButtonGroup radioButtonGroup = new ButtonGroup();
		
		for (int i = 0; i < e.drivers.size(); i++){
			final JRadioButton rb1 = new JRadioButton(e.drivers.get(i).name);
			rb1.setBackground(Color.GRAY);
			radioButtonGroup.add(rb1);
			
			rb1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent argo0) {
					if (rb1.isSelected() == true){
						for (int i = 0; i < e.drivers.size(); i++){
							if (e.drivers.get(i).name.equals(rb1.getText())){
								selectedDriver = e.drivers.get(i);
							}
						}
					}
				}
			});
			
			radioPanel.add(rb1);
		}
		
		JLabel lapNumberLabel = new JLabel("Lap number to generate trace for:");
		final JTextField lapNumberField = new JTextField("1");
				
		JPanel buttonList = new JPanel();
		buttonList.setBackground(Color.GRAY);
		buttonList.setLocation(20, 60);
		buttonList.setSize(350, 250);
		buttonList.setLayout(new GridLayout(0, 1));
		
		JButton singleSpeedTrace = new JButton("Single Lap Speed/t Trace");
		singleSpeedTrace.setForeground(Color.DARK_GRAY);
		singleSpeedTrace.setBackground(buttonColor1);
		
		singleSpeedTrace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				grapher g = new grapher(e);
				int lapToTraceIndex = Integer.valueOf(lapNumberField.getText()) - 1;
				if (lapToTraceIndex + 1 > selectedDriver.numberOfLaps){
					warningWindow ww1 = new warningWindow("Lap does not exist!");
				}
				else {
					staticPlotWindow spw = new staticPlotWindow(g.makeSingleSpeedGraph(selectedDriver.laps.get(lapToTraceIndex).data, Color.GREEN), selectedDriver.name + " Lap " + (lapToTraceIndex + 1) + " Speed/t Trace");					
				}
			}
		});
		
		JButton overlaidSpeedTrace = new JButton("Fastest vs. Last Speed/t Trace");
		overlaidSpeedTrace.setForeground(Color.DARK_GRAY);
		overlaidSpeedTrace.setBackground(buttonColor1);
		
		overlaidSpeedTrace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				grapher g = new grapher(e);
				staticPlotWindow spw = new staticPlotWindow(g.overlaidSpeedGraph(selectedDriver), selectedDriver.name + " Fastest vs. Last Speed/t Trace");
			}
		});
		
		JButton tireTempAllButton = new JButton("Tire Temperatures/t Trace, All");
		tireTempAllButton.setForeground(Color.DARK_GRAY);
		tireTempAllButton.setBackground(buttonColor1);
		
		tireTempAllButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				grapher g = new grapher(e);
				int lapToTraceIndex = Integer.valueOf(lapNumberField.getText()) - 1;
				if (lapToTraceIndex + 1 > selectedDriver.numberOfLaps){
					warningWindow ww1 = new warningWindow("Lap does not exist!");
				}
				else {
					staticPlotWindow spw = new staticPlotWindow(g.makeSingleTireTempGraph(selectedDriver.laps.get(lapToTraceIndex).data, "all"), selectedDriver.name + " Lap " + (lapToTraceIndex + 1) + " TT/t ALL");					
				}
			}
		});
		
		JButton tireTempSelective = new JButton("Tire Temperature/t Trace, Corner");
		tireTempSelective.setForeground(Color.DARK_GRAY);
		tireTempSelective.setBackground(buttonColor1);
		tireTempSelective.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				grapher g = new grapher(e);
				int lapToTraceIndex = Integer.valueOf(lapNumberField.getText()) - 1;
				if (lapToTraceIndex + 1 > selectedDriver.numberOfLaps){
					warningWindow ww1 = new warningWindow("Lap does not exist!");
				}
				else {
					JFrame subwindow = new JFrame();
					subwindow.setLayout(new GridLayout(0, 1));
					subwindow.setTitle("TT/t Menu");
					subwindow.setSize(300, 150);
					subwindow.getContentPane().setBackground(Color.GRAY);
					subwindow.setLocationRelativeTo(null);
					subwindow.setResizable(false);
					subwindow.setAlwaysOnTop(false);
					subwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					subwindow.setVisible(true);
					
					final JRadioButton fr = new JRadioButton("FR");
					fr.setBackground(Color.GRAY);
					fr.setSelected(true);
					final JRadioButton fl = new JRadioButton("FL");
					fl.setBackground(Color.GRAY);
					final JRadioButton rr = new JRadioButton("RR");
					rr.setBackground(Color.GRAY);
					final JRadioButton rl = new JRadioButton("RL");
					rl.setBackground(Color.GRAY);
					
					fr.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							if (fr.isSelected()){
								tire = "fr";
							}
						}
					});
					
					fl.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							if (fl.isSelected()){
								tire = "fl";
							}
						}
					});
					
					rr.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							if (rr.isSelected()){
								tire = "rr";
							}
						}
					});
					
					rl.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							if (rl.isSelected()){
								tire = "rl";
							}
						}
					});
					
					final ButtonGroup radioButtonGroup = new ButtonGroup();
					
					radioButtonGroup.add(fr);
					radioButtonGroup.add(fl);
					radioButtonGroup.add(rr);
					radioButtonGroup.add(rl);
					
					JPanel radioPanel = new JPanel();
					radioPanel.setBackground(Color.GRAY);
					radioPanel.setSize(250, 40);
					radioPanel.setLocation(10, 10);
					
					radioPanel.add(fr);
					radioPanel.add(fl);
					radioPanel.add(rr);
					radioPanel.add(rl);
					
					JButton genGraph = new JButton("Generate Graph");
					genGraph.setForeground(Color.DARK_GRAY);
					genGraph.setBackground(buttonColor1);
					
					genGraph.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
							
							grapher g = new grapher(e);
							int lapToTraceIndex = Integer.valueOf(lapNumberField.getText()) - 1;
							if (lapToTraceIndex + 1 > selectedDriver.numberOfLaps){
								warningWindow ww1 = new warningWindow("Lap does not exist!");
							}
							else {
								staticPlotWindow spw = new staticPlotWindow(g.makeSingleTireTempGraph(selectedDriver.laps.get(lapToTraceIndex).data, tire), selectedDriver.name + " Lap " + (lapToTraceIndex + 1) + " TT/t " + tire.toUpperCase());					
							}						
						}
					});
					
					subwindow.add(radioPanel);
					subwindow.add(genGraph);
				}
			}
		});
		
		buttonList.add(lapNumberLabel);
		buttonList.add(lapNumberField);
		buttonList.add(singleSpeedTrace);
		buttonList.add(overlaidSpeedTrace);
		buttonList.add(tireTempAllButton);
		buttonList.add(tireTempSelective);
		
		add(radioPanel);
		add(buttonList);
	}
}
