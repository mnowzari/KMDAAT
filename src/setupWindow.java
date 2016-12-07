import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class setupWindow extends JFrame{
	public setupWindow(final Event e){
	    
		setLayout(null);
		setTitle("Suspension Setup");
		setSize(375, 400);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel inputPane = new JPanel();
		inputPane.setBackground(Color.GRAY);
		inputPane.setSize(325, 350);
		inputPane.setLocation(20, 10);
		inputPane.setLayout(new GridLayout(0, 2));
		
		JPanel FL = new JPanel();
		FL.setSize(100, 75);
		FL.setBackground(Color.GRAY);

		JLabel flCamberLabel = new JLabel("FL Camber");
		final JTextField flCamberField = new JTextField(12);
		flCamberField.setBackground(Color.LIGHT_GRAY);

		JLabel flToeLabel = new JLabel("FL Toe");
		final JTextField flToeField = new JTextField(12);
		flToeField.setBackground(Color.LIGHT_GRAY);
		
		flCamberField.setText(String.valueOf(e.flSetup[0]));
		flToeField.setText(String.valueOf(e.flSetup[1]));
		
		FL.add(flCamberLabel);
		FL.add(flCamberField);
		FL.add(flToeLabel);
		FL.add(flToeField);
		//------------------------------------------------
		JPanel FR = new JPanel();
		FR.setSize(100, 75);
		FR.setBackground(Color.GRAY);
		
		JLabel frCamberLabel = new JLabel("FR Camber");
		final JTextField frCamberField = new JTextField(12);
		frCamberField.setBackground(Color.LIGHT_GRAY);
		
		JLabel frToeLabel = new JLabel("FR Toe");
		final JTextField frToeField = new JTextField(12);
		frToeField.setBackground(Color.LIGHT_GRAY);
		
		frCamberField.setText(String.valueOf(e.frSetup[0]));
		frToeField.setText(String.valueOf(e.frSetup[1]));
		
		FR.add(frCamberLabel);
		FR.add(frCamberField);
		FR.add(frToeLabel);
		FR.add(frToeField);
		//------------------------------------------------
		JPanel RR = new JPanel();
		RR.setSize(100, 75);
		RR.setBackground(Color.GRAY);
		
		JLabel rrCamberLabel = new JLabel("RR Camber");
		final JTextField rrCamberField = new JTextField(12);
		rrCamberField.setBackground(Color.LIGHT_GRAY);
		
		JLabel rrToeLabel = new JLabel("RR Toe");
		final JTextField rrToeField = new JTextField(12);
		rrToeField.setBackground(Color.LIGHT_GRAY);
		
		rrCamberField.setText(String.valueOf(e.rrSetup[0]));
		rrToeField.setText(String.valueOf(e.rrSetup[1]));
		
		RR.add(rrCamberLabel);
		RR.add(rrCamberField);
		RR.add(rrToeLabel);
		RR.add(rrToeField);		
		//------------------------------------------------
		JPanel RL = new JPanel();
		RL.setSize(100, 75);
		RL.setBackground(Color.GRAY);
		
		JLabel rlCamberLabel = new JLabel("RL Camber");
		final JTextField rlCamberField = new JTextField(12);
		rlCamberField.setBackground(Color.LIGHT_GRAY);
		
		JLabel rlToeLabel = new JLabel("RR Toe");
		final JTextField rlToeField = new JTextField(12);
		rlToeField.setBackground(Color.LIGHT_GRAY);
		
		rlCamberField.setText(String.valueOf(e.rlSetup[0]));
		rlToeField.setText(String.valueOf(e.rlSetup[1]));
		
		RL.add(rlCamberLabel);
		RL.add(rlCamberField);
		RL.add(rlToeLabel);
		RL.add(rlToeField);	
		//-------------------
		
		JPanel okButtonPane = new JPanel();
		okButtonPane.setSize(175, 35);
		okButtonPane.setBackground(Color.GRAY);
		
		JPanel cancelButtonPane = new JPanel();
		cancelButtonPane.setSize(175, 35);
		cancelButtonPane.setBackground(Color.GRAY);
		
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
				e.flSetup[0] = Double.valueOf(flCamberField.getText());
				e.flSetup[1] = Double.valueOf(flToeField.getText());
				
				e.frSetup[0] = Double.valueOf(frCamberField.getText());
				e.frSetup[1] = Double.valueOf(frToeField.getText());
				
				e.rlSetup[0] = Double.valueOf(rlCamberField.getText());
				e.rlSetup[1] = Double.valueOf(rlToeField.getText());
				
				e.rrSetup[0] = Double.valueOf(rrCamberField.getText());
				e.rrSetup[1] = Double.valueOf(rrToeField.getText());

				setVisible(false);
				dispose();
			}
		});
		
		inputPane.add(FL);
		inputPane.add(FR);
		inputPane.add(RL);
		inputPane.add(RR);
		
		cancelButtonPane.add(cancelButton);
		okButtonPane.add(okButton);
		inputPane.add(okButtonPane);
		inputPane.add(cancelButtonPane);
		
		add(inputPane);
	}
}
