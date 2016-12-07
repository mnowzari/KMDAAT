import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class lapManager extends JFrame{
	Lap newLap;
	Driver selectedDriver;
	public lapManager(final Event e){
		newLap = new Lap(e.dl);
		setLayout(null);
		setTitle("Add New Lap");
		setSize(275, 275);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		selectedDriver = new Driver("null");
		
		final Color buttonColor1 = new Color(50, 200, 56);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setBackground(Color.GRAY);
		radioPanel.setSize(225, 80);
		radioPanel.setLocation(20, 5);
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLocation(20, 95);
		panel.setSize(225, 75);
		panel.setLayout(new GridLayout(0, 2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
		final JLabel rawLapTimeLabel = new JLabel("Raw Lap Time:");
		final JTextField rawLapTimeField = new JTextField(10);
		rawLapTimeField.setBackground(Color.LIGHT_GRAY);
		
		final JLabel recordingLabel = new JLabel(" ");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setLocation(20, 175);
		buttonPanel.setSize(225, 45);
		buttonPanel.setLayout(new GridLayout(0, 2));
		
		JButton saveLap = new JButton("Save Lap");
		saveLap.setBackground(buttonColor1);
		saveLap.setForeground(Color.DARK_GRAY);
		
		saveLap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (!rawLapTimeField.getText().isEmpty() && selectedDriver.name != "null"){
					newLap.driverName = selectedDriver.name;
					newLap.lapNumber = String.valueOf(lapNumber(selectedDriver) + 1);
					newLap.rawLaptime = Double.valueOf(rawLapTimeField.getText());
					newLap.adjustedLaptime = newLap.rawLaptime * e.adjustmentCoeff;
					selectedDriver.addLap(newLap);
					
					grapher g = new grapher(e);
					staticPlotWindow spw0 = new staticPlotWindow(g.makeSingleTireTempGraph(newLap.data, "all"), selectedDriver.name + " Lap " + (lapNumber(selectedDriver)) + ", " + " Tire Temp/t, ALL");
					staticPlotWindow spw1 = new staticPlotWindow(g.makeSingleVelocityGraph(newLap.data), selectedDriver.name + " Lap " + (lapNumber(selectedDriver)) + ", " + " V/t Trace");
					
					setVisible(false);
					dispose();
				}
				else {
					warningWindow ww = new warningWindow("Check Your Inputs!");
				}
			}
		});
		
		final JButton recordLiveData = new JButton("RECORD");
		recordLiveData.setBackground(buttonColor1);
		recordLiveData.setForeground(Color.DARK_GRAY);
		
		recordLiveData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Thread record = new Thread(newLap);
				if (newLap.isRecording == false){
					newLap.isRecording = true;
					recordingLabel.setText("RECORDING LIVE DATA");
					recordLiveData.setBackground(Color.RED);
					record.start();
				}
				else if (newLap.isRecording = true){
					newLap.isRecording = false;
					recordingLabel.setText(" ");
					recordLiveData.setBackground(buttonColor1);
				}
			}
		});
		
		panel.add(rawLapTimeLabel);
		panel.add(rawLapTimeField);
		panel.add(recordingLabel);
		
		buttonPanel.add(saveLap);
		buttonPanel.add(recordLiveData);
				
		add(radioPanel);
		add(buttonPanel);
		add(panel);
	}
	
	public int lapNumber(Driver selectedDriver){
		int lapNumber = selectedDriver.laps.size();
		return lapNumber;
	}
}
