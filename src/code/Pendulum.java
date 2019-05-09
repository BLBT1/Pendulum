package code;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Pendulum extends JPanel implements Runnable {
	private Point ball1, ball2;
	private double angle1 = Math.PI / 2;
	private double angle2 = Math.PI / 2;
	private int length1;
	private int length2;
	private double g;
	private double m1;
	private double m2;
	private double ball1Size;
	private double ball2Size;
	private int sizeToMassRatio = 1000;
	private ArrayList<Point> pointsBall1 = new ArrayList<Point>();	
	private ArrayList<Point> pointsBall2 = new ArrayList<Point>();	
	private boolean paintTrail; 


	public Pendulum() {
		this.length1 = 150;
		this.length2 = 150;
		this.g = 9;
		this.m1 = 0.05;
		this.m2 = 0.05;
		paintTrail=false;
		ball1= new Point(0,0);
		ball2= new Point(0,0);
		/*
		 * ball1Size = m1 * sizeToMassRatio; ball2Size = m2 * sizeToMassRatio;
		 */
		setDoubleBuffered(true);
	}

	@Override
	public void paintComponent(Graphics g) { // paintComponent(Graphics g) or paint()
	super.paintComponent(g);
		ball1Size = m1 * sizeToMassRatio;
		ball2Size = m2 * sizeToMassRatio;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		int anchorX = getWidth() / 2, anchorY = getHeight() / 3;
		int ball1X = anchorX + (int) (Math.sin(angle1) * length1);
		int ball1Y = anchorY + (int) (Math.cos(angle1) * length1);
		int ball2X = ball1X + (int) (Math.sin(angle2) * length2);
		int ball2Y = ball1Y + (int) (Math.cos(angle2) * length2);
		
		ball1.x=ball1X;
		ball1.y=ball1Y;
		ball2.x=ball2X;
		ball2.y=ball2Y;
		pointsBall1.add(new Point(ball1.x,ball1.y));
		pointsBall2.add(new Point(ball2.x,ball2.y));		
		if(paintTrail) {
//			for(Point p: pointsBall1) {
//		g.fillOval((int)p.getX(),(int) p.getY(), 3, 3);							
//			}
//			for(Point p: pointsBall2) {
//				g.fillOval((int)p.getX(),(int) p.getY(), 3, 3);								
//					}
			for(int i=1; i< pointsBall2.size();i++) {
				g.drawLine((int)pointsBall2.get(i).getX(),(int)pointsBall2.get(i).getY() ,(int)pointsBall2.get(i-1).getX() ,(int)pointsBall2.get(i-1).getY());
			}
			
		}
		
		g.drawLine(anchorX, anchorY, ball1X, ball1Y);
		g.drawLine(ball1X, ball1Y, ball2X, ball2Y);
		g.fillOval(anchorX - 3, anchorY - 4, 7, 7);// draw anchor
		g.fillOval((int) (ball1X - ball1Size / 2), (int) (ball1Y - ball1Size / 2), (int) ball1Size, (int) ball1Size);// draw
																														// ball1
		g.fillOval((int) (ball2X - ball2Size / 2), (int) (ball2Y - ball2Size / 2), (int) ball2Size, (int) ball2Size);// draw
																														// ball2

	}

	public void run() {
		double angle1Accel = 0, angle1Velocity = 0, angle2Accel = 0, angle2Velocity = 0;
		while (true) {
			// the formula of the first angle's acceleration
			// from http://scienceworld.wolfram.com/physics/DoublePendulum.html
			double num1_1 = -g * (2 * m1 + m2) * Math.sin(angle1);
			double num1_2 = -m2 * g * Math.sin(angle1 - 2 * angle2);
			double num1_3 = -2 * Math.sin(angle1 - angle2) * m2;
			double num1_4 = angle2Velocity * angle2Velocity * length2
					+ angle1Velocity * angle1Velocity * length1 * Math.cos(angle1 - angle2);
			double den1 = length1 * (2 * m1 + m2 - m2 * Math.cos(2 * angle1 - 2 * angle2));
			angle1Accel = (num1_1 + num1_2 + num1_3 * num1_4) / den1;

			// the formula of the second angle's acceleration
			// from http://scienceworld.wolfram.com/physics/DoublePendulum.html
			double num2_1 = 2 * Math.sin(angle1 - angle2);
			double num2_2 = angle1Velocity * angle1Velocity * length1 * (m1 + m2);
			double num2_3 = g * (m1 + m2) * Math.cos(angle1);
			double num2_4 = angle2Velocity * angle2Velocity * length2 * m2 * Math.cos(angle1 - angle2);
			double den2 = length2 * (2 * m1 + m2 - m2 * Math.cos(2 * angle1 - 2 * angle2));
			angle2Accel = (num2_1 * (num2_2 + num2_3 + num2_4)) / den2;
			// running

			angle1 += angle1Velocity;
			angle2 += angle2Velocity;
			angle1Velocity += angle1Accel;
			angle2Velocity += angle2Accel;
			/*
			 * ball1Size = m1 * sizeToMassRatio; ball2Size = m2 * sizeToMassRatio;
			 */
			
			// collect new Points 
			

			repaint();
			try {
				Thread.sleep(150);
			} catch (InterruptedException ex) {
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public void setLength1(int length1) {
		this.length1 = length1;
	}

	public void setLength2(int length2) {
		this.length2 = length2;
	}

	public void setMass1(double m1) {
		this.m1 = m1;
	}

	public void setMass2(double m2) {
		this.m2 = m2;
	}

	public void setg(double g) {
		this.g = g;
	}
	public void switchPaintTrail() {
	paintTrail = ! paintTrail; 
	}

}
