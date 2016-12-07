import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class livePlotter extends JFrame implements Runnable{
	dataLogger dl;
	dataPack buffer[];
	public livePlotter(int posX, int posY, int windowWidth, int windowHeight, Event e){
		int sizeX = windowWidth - 25;
		int sizeY = (windowHeight / 2) - 50;
		setLayout(null);
		setTitle("Live Data Plot");
		setSize(sizeX, sizeY);
		getContentPane().setBackground(Color.GRAY);
		setLocation(posX, posY);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		this.dl = e.dl;
		buffer = new dataPack[100];
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		final int counter = 0;

		JPanel plotPane = new JPanel(){
			public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.GRAY);
				g2.fillRect(0, 0, getWidth(), getHeight());
				drawVisual(dl, g2, counter);
			}
		};
		
		pane.add(plotPane);
		
		plotPane.setSize(sizeX, sizeY);
		plotPane.setLocation(0, 0);
		plotPane.setBackground(Color.GRAY);
		
		add(plotPane);
	}

	private void drawVisual(dataLogger dl, Graphics2D g2, int xPosCounter){
		g2.setColor(Color.BLUE);
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(xPosCounter, dl.sr.dataPack.velocity, 1, 1);
		g2.draw(rect);
		xPosCounter++;
	}
	
	public void run() {
		
	}
}
