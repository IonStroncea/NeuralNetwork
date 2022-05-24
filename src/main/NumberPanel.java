package main;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.*;

import neuralNetWork.NeuralNetwork;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class NumberPanel extends JPanel
{
	Pixels px;
	JRadioButton jr1;
	JRadioButton jr2;
	ButtonGroup bgroup;
	Results rs;
	NeuralNetwork neuralNetwork = new NeuralNetwork();
	@Override
    protected void paintComponent(Graphics g) 
    {
    	super.paintComponent(g);
    	px.paintAll(g);
    	rs.drawResults(g);
    }
	
	public NumberPanel(Pixels px)throws Exception
	{
		neuralNetwork.readFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","numbers.ser");

		rs=new Results(360,40);
		rs.resultTitel.add("0:");
		rs.resultTitel.add("1:");
		rs.resultTitel.add("2:");
		rs.resultTitel.add("3:");
		rs.resultTitel.add("4:");
		rs.resultTitel.add("5:");
		rs.resultTitel.add("6:");
		rs.resultTitel.add("7:");
		rs.resultTitel.add("8:");
		rs.resultTitel.add("9:");
		rs.allOne();
		
		this.px=px;
		jr1=new JRadioButton("White",true);
		jr2=new JRadioButton("Black",false);
		bgroup=new ButtonGroup();
		bgroup.add(jr1);
        bgroup.add(jr2);
		add(jr1);
		add(jr2);
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int color=10;
				if(jr1.isSelected())
					color=10;
				else
					color=0;
				if(px.drawLine(e.getX(), e.getY(),color)) 
				{	
					double input[]=new double[28*28];
					for(int i=0;i<28*28;i++)
					{
						input[i]=px.pixels.get(i)/10.0d;
					}
					neuralNetwork.run(input);
					double result[]=neuralNetwork.getResult();
					for(int i=0;i<10;i++)
					{
						rs.resultValue.set(i, result[i]);
					}
					revalidate();
					repaint(0,0,500,500);
				}
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		  });
	}
}
