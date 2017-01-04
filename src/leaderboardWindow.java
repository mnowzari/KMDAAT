import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
	

public class leaderboardWindow extends JFrame implements Runnable{
	Event e;
	JLabel driver1;
	JLabel driver2;
	JLabel driver3;
	public leaderboardWindow(Event event){
		this.e = event;
		setLayout(null);
		setTitle("Leaderboard");
		setSize(400, 150);
		getContentPane().setBackground(Color.BLACK);
		setLocation(660, 200);
		setResizable(false);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		
		JPanel leaderboardPane = new JPanel();
		leaderboardPane.setSize(400, 110);
		leaderboardPane.setLocation(5, 5);
		leaderboardPane.setBackground(Color.BLACK);
		leaderboardPane.setLayout(new GridLayout(0, 1));
		
		JLabel colNames = new JLabel("Driver | Fastest Lap | Last Lap | Difference");
		colNames.setFont(new Font("Monospaced", Font.BOLD, 12));
		colNames.setForeground(Color.WHITE);
		driver1 = new JLabel("--");
		driver1.setFont(new Font("Monospaced", Font.BOLD, 12));
		driver1.setForeground(Color.WHITE);
		driver2 = new JLabel("--");
		driver2.setFont(new Font("Monospaced", Font.BOLD, 12));
		driver2.setForeground(Color.WHITE);
		driver3 = new JLabel("--");
		driver3.setFont(new Font("Monospaced", Font.BOLD, 12));
		driver3.setForeground(Color.WHITE);
		
		leaderboardPane.add(colNames);
		leaderboardPane.add(driver3);
		leaderboardPane.add(driver2);
		leaderboardPane.add(driver1);
		
		add(leaderboardPane);
	}
	
	private void updateLeaderboard(ArrayList<Driver> d){
		Driver[] drivers = new Driver[3];
		if (!e.drivers.isEmpty()){
			drivers[0] = e.drivers.get(0);	
			if (d.size() > 1){
				drivers[1] = e.drivers.get(1);
				if (d.size() == 3){
					drivers[2] = e.drivers.get(2);
				}
			}
			
			boolean swap = true;
			while (swap == true){
				swap = false;
				for (int i = 1; i < d.size(); i++){
					if (drivers[i - 1].getFastestLaptime() < drivers[i].getFastestLaptime()){
						Driver temp = drivers[i - 1];
						drivers[i - 1] = drivers[i];
						drivers[i] = temp;
						swap = true;
					}
				}
			}
			
			updateText(driver1, drivers[0]);
			if (d.size() > 1){
				updateText(driver2, drivers[1]);
				if (d.size() == 3){
					updateText(driver3, drivers[2]);
				}
			}
		}
	}
	
	private void updateText(JLabel driverLabel, Driver driver){
		if (driver.getFastestLaptime() == 10000.000){
			driverLabel.setText(driver.name + " | 0.000 | 0.000 | 0.000");
		}
		else {
			BigDecimal diff = new BigDecimal((driver.getLastLaptime() - driver.getPreviousLaptime()));
			diff = diff.setScale(3, RoundingMode.HALF_UP);
			String difference = "";
			if (diff.doubleValue() < 0){
				difference = String.format("%.3f", diff);
				driverLabel.setForeground(new Color(50, 200, 56));
			}
			else {
				difference = "+" + String.format("%.3f", diff);
				driverLabel.setForeground(Color.RED);
			}
			driverLabel.setText(driver.laps.size() + ": " + driver.name + " | " + driver.getFastestLaptime() + " | "+ driver.getLastLaptime() + " | " + difference);
		}
	}
	
	public void run() {
		while (true){
			updateLeaderboard(e.drivers);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
