package code;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui {
	private JFrame f;
	private Pendulum p;
	private JSlider mass1Slider, mass2Slider, arm1Slider, arm2Slider, gSlider;
	private JRadioButton trace;  
	
	/*
	 * private int length1; private int length2; private double g; private double
	 * m1; private double m2;
	 */
	private JLabel arm1L, arm2L, m1L, m2L, gL;
	private JPanel controller;

	public Gui() {
		f = new JFrame("Double Pendulum Simulation");
		f.setSize(1600, 800);
		f.setResizable(false);

		controller = new JPanel();
		controller.setLayout(new GridLayout(10, 1));
		arm1L = new JLabel("length of the first arm is(m): ");
		arm2L = new JLabel("length of the second arm is(m):");
		m1L = new JLabel("mass of the first ball is(kg);");
		m2L = new JLabel("mass of the second ball is(kg):");
		gL = new JLabel("gravitational acceleration(m/s^2)");
		trace = new JRadioButton("show trail"); 

//		mass1Slider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
//		mass2Slider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
		arm1Slider = new JSlider(JSlider.HORIZONTAL, 50, 200, 125);
		arm2Slider = new JSlider(JSlider.HORIZONTAL, 50, 200, 125);
		gSlider = new JSlider(JSlider.HORIZONTAL, -1, 12, 0);
		controller.add(arm1L);
		controller.add(arm1Slider);
		controller.add(arm2L);
		controller.add(arm2Slider);
		controller.add(gL);
		controller.add(gSlider);
		controller.add(trace);
		
//		controller.add(m1L);
//		controller.add(mass1Slider);
//		controller.add(m2L);
//		controller.add(mass2Slider);
		setSlider();
		setButton();
		p = new Pendulum();
		f.add(p, BorderLayout.WEST);
		f.add(controller, BorderLayout.EAST);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		new Thread(p).start();

	}

	//set the radio button(add action) 
	public void setButton() {
		trace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				// here add trails
				
				p.switchPaintTrail();

			}
		});
	}

	public void setSlider() {
/*		mass1Slider.setMajorTickSpacing(10);
		mass1Slider.setMinorTickSpacing(1);
		mass1Slider.setBounds(300, 100, 100, 30);
		mass1Slider.setPaintLabels(true);
		mass1Slider.setPaintTicks(true);
		mass1Slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				p.setMass1(source.getValue() / 1000);
			}

		});

		mass2Slider.setMajorTickSpacing(10);
		mass2Slider.setMinorTickSpacing(1);
		mass2Slider.setPaintLabels(true);
		mass2Slider.setPaintTicks(true);
		mass2Slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				p.setMass2(source.getValue() / 1000);
			}

		});
	*/	
		
		arm1Slider.setMajorTickSpacing(50);
		arm1Slider.setMinorTickSpacing(10);
		arm1Slider.setPaintLabels(true);
		arm1Slider.setPaintTicks(true);
		arm1Slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				p.setLength1(source.getValue());
			}

		});

		arm2Slider.setMajorTickSpacing(50);
		arm2Slider.setMinorTickSpacing(10);
		arm2Slider.setPaintLabels(true);
		arm2Slider.setPaintTicks(true);
		arm2Slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				p.setLength2(source.getValue());
			}

		});

		gSlider.setMajorTickSpacing(1);
		gSlider.setPaintLabels(true);
		gSlider.setPaintTicks(true);
		gSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				p.setg(source.getValue());
			}

		});
	}

}
