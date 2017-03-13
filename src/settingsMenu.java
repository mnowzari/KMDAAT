import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class settingsMenu extends JFrame{
	public settingsMenu(final Event e){
	    
		setLayout(null);
		setTitle("Application Settings");
		setSize(640, 400);
		getContentPane().setBackground(Color.GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		final JPanel opPane = new JPanel();
		opPane.setLocation(10, 10);
		opPane.setSize(150, 330);
		opPane.setBackground(Color.LIGHT_GRAY);
		opPane.setLayout(new GridLayout(0, 1));
		
		JPanel setPane = new JPanel();
		setPane.setLocation(170, 10);
		setPane.setSize(435, 330);
		setPane.setBackground(Color.LIGHT_GRAY);
		
		JButton option1 = new JButton("Serial Monitor");
		option1.setBackground(Color.LIGHT_GRAY);
		option1.setForeground(Color.DARK_GRAY);
		
		option1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				serialMonitor sm = new serialMonitor(e);
				Thread smt = new Thread(sm);
				smt.start();
			}
		});
		
		JButton option2 = new JButton("Serial Settings");
		option2.setBackground(Color.LIGHT_GRAY);
		option2.setForeground(Color.DARK_GRAY);
		
		option2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JButton option3 = new JButton("Option 3");
		option3.setBackground(Color.LIGHT_GRAY);
		option3.setForeground(Color.DARK_GRAY);
		
		JButton option4 = new JButton("Option 4");
		option4.setBackground(Color.LIGHT_GRAY);
		option4.setForeground(Color.DARK_GRAY);
		
		JButton option5 = new JButton("Option 5");
		option5.setBackground(Color.LIGHT_GRAY);
		option5.setForeground(Color.DARK_GRAY);
		
		opPane.add(option1);
		opPane.add(option2);
		opPane.add(option3);
		opPane.add(option4);
		opPane.add(option5);
		
		add(opPane);
		add(setPane);
	}
}
