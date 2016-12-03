import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class lapManager extends JFrame{
	Lap newLap;
	public lapManager(final Event e){
		newLap = new Lap(e.dl);
		setLayout(null);
		setTitle("Add New Lap");
		setSize(275, 200);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		final Color buttonColor1 = new Color(50, 200, 56);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLocation(20, 10);
		panel.setSize(225, 85);
		panel.setLayout(new GridLayout(0, 2));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final JLabel lapNumberLabel = new JLabel("Lap Number " + lapNumber(e));
		
		final JLabel rawLapTimeLabel = new JLabel("Raw Lap Time:");
		final JTextField rawLapTimeField = new JTextField(10);
		rawLapTimeField.setBackground(Color.LIGHT_GRAY);
		
		final JLabel recordingLabel = new JLabel(" ");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setLocation(20, 100);
		buttonPanel.setSize(225, 45);
		buttonPanel.setLayout(new GridLayout(0, 2));
		
		JButton saveLap = new JButton("Save Lap");
		saveLap.setBackground(buttonColor1);
		saveLap.setForeground(Color.DARK_GRAY);
		
		saveLap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (!rawLapTimeField.getText().isEmpty()){
					newLap.id = lapNumber(e);
					newLap.rawLaptime = Double.valueOf(rawLapTimeField.getText());
					newLap.adjustedLaptime = newLap.rawLaptime * e.adjustmentCoeff;
					e.addLap(newLap);
					e.dumpData();
					setVisible(false);
					dispose();
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
		
		panel.add(lapNumberLabel);
		panel.add(rawLapTimeLabel);
		panel.add(rawLapTimeField);
		panel.add(recordingLabel);
		
		buttonPanel.add(saveLap);
		buttonPanel.add(recordLiveData);
		
		add(buttonPanel);
		add(panel);
	}
	
	public String lapNumber(Event e){
		String lapNumber = String.valueOf(e.laps.size() + 1);
		return lapNumber;
	}
}
