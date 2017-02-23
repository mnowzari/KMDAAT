import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class mainWindow extends JFrame{
	Event e;
	public mainWindow(Event e){
		this.e = e;
		
		setLayout(null);
		setTitle("Kinetechnik Motorsports Data Acquisition and Analysis Tool");
		
	    Toolkit tk = Toolkit.getDefaultToolkit();  
	    int monitorXSize = ((int) tk.getScreenSize().getWidth());  
	    int monitorYSize = ((int) tk.getScreenSize().getHeight());  
	    setSize(monitorXSize,monitorYSize - 25);
        
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//-----------------------------------------------------------
		//sometimes the displays initialize before the serial port is ready, causing a null pointer exception 
		//a 300 millisecond pause before the GUI threads initialize gives the SerialReader thread enough time to get going
		mainMenu mainMenu = new mainMenu((monitorXSize * 3) / 4, 40, e);
		
		try { 
			Thread.sleep(300);
		} catch (InterruptedException arg0) {
			arg0.printStackTrace();
		}
		
//		tireTempDisplay ttd = new tireTempDisplay(10, 40, e.dl);
//		Thread ttdThread = new Thread(ttd);
//		ttdThread.start();
		
//		livePlotter livePlot = new livePlotter(10, monitorYSize / 2, monitorXSize, monitorYSize, e);
//		Thread plotThread = new Thread(livePlot);
//		plotThread.start();
		
	}
}
