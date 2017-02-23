
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class tireTempDisplay extends JFrame implements Runnable{
	dataLogger dl;
	
	JPanel outerTire;
	JPanel middleTire;
	JPanel innerTire;
	
	JPanel outerTire1;
	JPanel middleTire1;
	JPanel innerTire1;
	
	JPanel outerTire2;
	JPanel middleTire2;
	JPanel innerTire2;
	
	JPanel outerTire3;
	JPanel middleTire3;
	JPanel innerTire3;
	
	JLabel outerText;
	JLabel middleText;
	JLabel innerText;
	
	JLabel outerText1;
	JLabel middleText1;
	JLabel innerText1;
	
	JLabel outerText2;
	JLabel middleText2;
	JLabel innerText2;
	
	JLabel outerText3;
	JLabel middleText3;
	JLabel innerText3;
	
	public tireTempDisplay(int xPos, int yPos, dataLogger dl){
		//Placeholder random tire temp generators until we can get actual sensor data. 
		//That will most likely be a separate thread handling the serial connection to Yoan's hardware
//		FL = new temperatureGen();
//		Thread t1 = new Thread(FL);
//		t1.start();
//		
//		FR = new temperatureGen();
//		Thread t2 = new Thread(FR);
//		t2.start();
//		
//		RR = new temperatureGen();
//		Thread t3 = new Thread(RR);
//		t3.start();
//		
//		RL = new temperatureGen();
//		Thread t4 = new Thread(RL);
//		t4.start();
		
		this.dl = dl;
		setLayout(null);
		setTitle("Tire Monitor");
		setSize(640, 480);
		setLocation(xPos, yPos);
		getContentPane().setBackground(Color.GRAY);
		setResizable(true);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		////////////////////////////////////////
		final JPanel flTire = new JPanel();
		flTire.setLayout(null);
		flTire.setLocation(10 ,10);
		flTire.setSize(195, 140);
		flTire.setBackground(Color.GRAY);
		
		outerText = new JLabel();
		outerText.setForeground(Color.WHITE);
		
		middleText = new JLabel();
		middleText.setForeground(Color.WHITE);
		
		innerText = new JLabel();
		innerText.setForeground(Color.WHITE);
		
		outerTire = new JPanel();
		outerTire.setLocation(25, 25);
		outerTire.setSize(50, 110);
		
		middleTire = new JPanel();
		middleTire.setLocation(75, 25);
		middleTire.setSize(50, 110);
		
		innerTire = new JPanel();
		innerTire.setLocation(125, 25);
		innerTire.setSize(50, 110);
		
		outerTire.add(outerText);
		middleTire.add(middleText);
		innerTire.add(innerText);
		
		flTire.add(outerTire);
		flTire.add(middleTire);
		flTire.add(innerTire);
		////////////////////////////////////////
		
		final JPanel frTire = new JPanel();
		frTire.setLayout(null);
		frTire.setLocation(405 ,10);
		frTire.setSize(195, 140);
		frTire.setBackground(Color.GRAY);
		
		outerText1 = new JLabel();
		outerText1.setForeground(Color.WHITE);
		
		middleText1 = new JLabel();
		middleText1.setForeground(Color.WHITE);
		
		innerText1 = new JLabel();
		innerText1.setForeground(Color.WHITE);
		
		outerTire1 = new JPanel();
		outerTire1.setLocation(125, 25);
		outerTire1.setSize(50, 110);
		
		middleTire1 = new JPanel();
		middleTire1.setLocation(75, 25);
		middleTire1.setSize(50, 110);
		
		innerTire1 = new JPanel();
		innerTire1.setLocation(25, 25);
		innerTire1.setSize(50, 110);
		
		innerTire1.add(innerText1);		
		middleTire1.add(middleText1);
		outerTire1.add(outerText1);

		frTire.add(outerTire1);
		frTire.add(innerTire1);
		frTire.add(middleTire1);
		////////////////////////////////////////
		
		final JPanel rrTire = new JPanel();
		rrTire.setLayout(null);
		rrTire.setLocation(405 ,250);
		rrTire.setSize(195, 140);
		rrTire.setBackground(Color.GRAY);
		
		outerText2 = new JLabel();
		outerText2.setForeground(Color.WHITE);
		
		middleText2 = new JLabel();
		middleText2.setForeground(Color.WHITE);
		
		innerText2 = new JLabel();
		innerText2.setForeground(Color.WHITE);
		
		outerTire2 = new JPanel();
		outerTire2.setLocation(125, 25);
		outerTire2.setSize(50, 110);
		
		middleTire2 = new JPanel();
		middleTire2.setLocation(75, 25);
		middleTire2.setSize(50, 110);
		
		innerTire2 = new JPanel();
		innerTire2.setLocation(25, 25);
		innerTire2.setSize(50, 110);
		
		innerTire2.add(innerText2);		
		middleTire2.add(middleText2);
		outerTire2.add(outerText2);

		rrTire.add(outerTire2);
		rrTire.add(innerTire2);
		rrTire.add(middleTire2);
		////////////////////////////////////////
		final JPanel rlTire = new JPanel();
		rlTire.setLayout(null);
		rlTire.setLocation(10 ,250);
		rlTire.setSize(195, 140);
		rlTire.setBackground(Color.GRAY);
		
		outerText3 = new JLabel();
		outerText3.setForeground(Color.WHITE);
		
		middleText3 = new JLabel();
		middleText3.setForeground(Color.WHITE);
		
		innerText3 = new JLabel();
		innerText3.setForeground(Color.WHITE);
		
		outerTire3 = new JPanel();
		outerTire3.setLocation(25, 25);
		outerTire3.setSize(50, 110);
		
		middleTire3 = new JPanel();
		middleTire3.setLocation(75, 25);
		middleTire3.setSize(50, 110);
		
		innerTire3 = new JPanel();
		innerTire3.setLocation(125, 25);
		innerTire3.setSize(50, 110);
		
		outerTire3.add(outerText3);
		middleTire3.add(middleText3);
		innerTire3.add(innerText3);
		
		rlTire.add(outerTire3);
		rlTire.add(middleTire3);
		rlTire.add(innerTire3);

		add(rlTire);
		add(rrTire);
		add(flTire);
		add(frTire);
	}
	
	public void updateTire(double[] tireTemps, JPanel tireSection, JLabel text, int index, String section){
		text.setText(section + ": " + String.valueOf((int)tireTemps[index]));
		
		if (tireTemps[index] > 222.0){	
			tireSection.setBackground(new Color(204, 9, 15));
		}
		else if (tireTemps[index] < 220.0 && tireTemps[index] >= 190.0){
			tireSection.setBackground(new Color(250, 110, 5));
		}
		else if (tireTemps[index] < 200.0 && tireTemps[index] >= 180.0){
			tireSection.setBackground(new Color(50, 200, 56));
		}
		else if (tireTemps[index] < 180){
			tireSection.setBackground(new Color(62, 104, 245));

		}
	}

	public void run() {
		while (true){
			updateTire(dl.sr.dataPack.flTemp, outerTire, outerText, 0, "O");
			updateTire(dl.sr.dataPack.flTemp, middleTire, middleText, 1, "M");
			updateTire(dl.sr.dataPack.flTemp, innerTire, innerText, 2, "I");
			
			updateTire(dl.sr.dataPack.frTemp, outerTire1, outerText1, 0, "O");
			updateTire(dl.sr.dataPack.frTemp, middleTire1, middleText1, 1, "M");
			updateTire(dl.sr.dataPack.frTemp, innerTire1, innerText1, 2, "I");
			
			updateTire(dl.sr.dataPack.rrTemp, outerTire2, outerText2, 0, "O");
			updateTire(dl.sr.dataPack.rrTemp, middleTire2, middleText2, 1, "M");
			updateTire(dl.sr.dataPack.rrTemp, innerTire2, innerText2, 2, "I");

			updateTire(dl.sr.dataPack.rlTemp, outerTire3, outerText3, 0, "O");
			updateTire(dl.sr.dataPack.rlTemp, middleTire3, middleText3, 1, "M");
			updateTire(dl.sr.dataPack.rlTemp, innerTire3, innerText3, 2, "I");
		}
	}
}
