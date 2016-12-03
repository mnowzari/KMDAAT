import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class newEventWindow extends JFrame{
	public newEventWindow(final Event e){
		setLayout(null);
		setTitle("New Event");
		setSize(285, 400);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.GRAY);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//------------------------------------------------
		JPanel inputPane = new JPanel();
		inputPane.setBackground(Color.GRAY);
		inputPane.setSize(250, 300);
		inputPane.setLocation(10, 20);
		inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
		
		final JLabel eventNameLabel = new JLabel("Event Name:");
		final JTextField eventNameField = new JTextField(10);
		eventNameField.setBackground(Color.LIGHT_GRAY);
		
		JLabel eventLocationLabel = new JLabel("Event Location:");
		final JTextField eventLocationField = new JTextField(10);
		eventLocationField.setBackground(Color.LIGHT_GRAY);
		
		JLabel classNameLabel = new JLabel("Competing Class:");
		final JTextField classNameField = new JTextField(10);
		classNameField.setBackground(Color.LIGHT_GRAY);
		
		JLabel classAdjustmentLabel = new JLabel("Class Adjustment Coefficient");
		final JTextField classAdjustmentField = new JTextField(10);
		classAdjustmentField.setBackground(Color.LIGHT_GRAY);
				
		JLabel carWeightLabel = new JLabel("Car Weight:");
		final JTextField carWeightField= new JTextField(10);
		carWeightField.setBackground(Color.LIGHT_GRAY);
		
		JLabel tireOptionLabel = new JLabel("Tire Option/Compound:");
		final JTextField tireOptionField= new JTextField(10);
		tireOptionField.setBackground(Color.LIGHT_GRAY);
		
		JLabel tireSizeLabel = new JLabel("Tire Size:");
		final JTextField tireSizeField = new JTextField(10);
		tireSizeField.setBackground(Color.LIGHT_GRAY);
		
		JPanel okButtonPane = new JPanel();
		okButtonPane.setSize(175, 35);
		okButtonPane.setLocation(60, 325);
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
				if (!eventNameField.getText().isEmpty()){
					if (e.eventName == ""){
						e.eventName = eventNameField.getText();
						e.eventLocation = eventLocationField.getText();
						e.competingClass = classNameField.getText();
						e.adjustmentCoeff = Double.valueOf(classAdjustmentField.getText());
						e.carWeight = Double.valueOf(carWeightField.getText());	
						e.tireName = tireOptionField.getText();
						e.tireSize = tireSizeField.getText();
//						System.out.println(e.eventName + ", " + 
//								e.eventLocation + ", " + e.carWeight + ", " + e.tireName 
//								+ ", " + e.tireSize);
					}
					else {
						e.saveEventToXML();
						e.resetEvent();
						e.eventName = eventNameField.getText();
						e.eventLocation = eventLocationField.getText();
						e.competingClass = classNameField.getText();
						e.adjustmentCoeff = Double.valueOf(classAdjustmentField.getText());
						e.carWeight = Double.valueOf(carWeightField.getText());
						e.tireName = tireOptionField.getText();
						e.tireSize = tireSizeField.getText();
//						System.out.println(e.eventName + ", " + 
//								e.eventLocation + ", " + e.carWeight + ", " + e.tireName 
//								+ ", " + e.tireSize);
					}	
				}
				else {
					setVisible(false);
					dispose();
				}
				e.genFilename();
				setVisible(false);
				dispose();
			}
		});
		
		inputPane.add(eventNameLabel);
		inputPane.add(eventNameField);
		inputPane.add(eventLocationLabel);
		inputPane.add(eventLocationField);
		inputPane.add(classNameLabel);
		inputPane.add(classNameField);
		inputPane.add(classAdjustmentLabel);
		inputPane.add(classAdjustmentField);
		inputPane.add(carWeightLabel);
		inputPane.add(carWeightField);
		inputPane.add(tireOptionLabel);
		inputPane.add(tireOptionField);
		inputPane.add(tireSizeLabel);
		inputPane.add(tireSizeField);
		okButtonPane.add(okButton);
		okButtonPane.add(cancelButton);
		
		add(inputPane);
		add(okButtonPane);
	}
}
