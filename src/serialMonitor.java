import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class serialMonitor extends JFrame implements Runnable{
	final JTextArea l1;
	Event event;
	public serialMonitor(Event e){
	    event = e;
		setLayout(null);
		setTitle("Serial Monitor");
		setSize(640, 340);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setResizable(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

		//--------------------------------------------------
		
		l1 = new JTextArea();
		l1.setSize(600, 200);
		l1.setBackground(Color.BLACK);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		JScrollPane textPanel = new JScrollPane(l1);
		textPanel.setSize(600, 200);
		textPanel.setLocation(10, 10);
		textPanel.setBackground(Color.LIGHT_GRAY);
		
		JPanel label = new JPanel();
		label.setSize(155, 35);
		label.setLocation(10, 225);
		label.setBackground(Color.LIGHT_GRAY);
		
		JLabel baudRate = new JLabel(e.dl.currentComPort + ", Baud Rate: " + e.dl.baudRate);
		
		label.add(baudRate);
		
		add(textPanel, BorderLayout.CENTER);
		add(label);
	}

	public void run() {
		int counter = 0;
		while (true){
			l1.append(event.dl.sr.dataPack.rawData + "\n");
			l1.setCaretPosition(l1.getText().length() - 1);
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
	}
}
