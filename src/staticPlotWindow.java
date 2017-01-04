import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class staticPlotWindow extends JFrame{
	BufferedImage img;
	boolean mouseEntered;
	Point verticalstartPoint = new Point(0, 0);
	Point verticalendPoint = new Point(0, 0);
	Point horizontalstartPoint1 = new Point(0, 0);
	Point horizontalendPoint1 = new Point(0, 0);
	public staticPlotWindow(BufferedImage img, String windowName){
		setLayout(null);
		setTitle(windowName);
		setSize(1200, 360);
		getContentPane().setBackground(Color.GRAY);
		setLocation(10, 400);
		setResizable(true);
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//--------------------------------------------------
		this.img = img;
		mouseEntered = false;
		
		ImageIcon graph = new ImageIcon(img);
		JLabel label = new JLabel();
		label.setIcon(graph);
		
		final JLabel xyLabel = new JLabel("-");
		xyLabel.setSize(335, 13);
		xyLabel.setLocation(25, 300);
				
		final JPanel jpl = new JPanel();
		jpl.setSize(2400, 305);
		jpl.setLocation(25, -5);
		
		jpl.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				
			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {
				
			}

			public void mousePressed(MouseEvent arg0) {
				xyLabel.setText("X: " + jpl.getMousePosition().x + " Y: " + (305 - jpl.getMousePosition().y) );
				
				verticalstartPoint.setLocation(jpl.getMousePosition().x + 33, 0);
				verticalendPoint.setLocation(jpl.getMousePosition().x + 33, 325);
				horizontalstartPoint1.setLocation(33, jpl.getMousePosition().y + 26);
				horizontalendPoint1.setLocation(1799, jpl.getMousePosition().y + 26);
			}

			public void mouseReleased(MouseEvent arg0) {
				repaint();
			}
			
		});
		
		jpl.add(label);
		
		this.getContentPane().add(xyLabel);
		this.getContentPane().add(jpl);
	}
	
	public void paint(Graphics g){
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.ORANGE);
		g2.draw(new Line2D.Double(verticalstartPoint.x, verticalstartPoint.y, verticalendPoint.x, verticalendPoint.y));
		g2.draw(new Line2D.Double(horizontalstartPoint1.x, horizontalstartPoint1.y, horizontalendPoint1.x, horizontalendPoint1.y));
	}
}
