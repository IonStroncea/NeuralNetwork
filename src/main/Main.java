package main;

import java.awt.*;
import javax.swing.*;

import faceRecognition.RecogNetwork;

import java.awt.event.*;

public class Main {
	public static void main(String args[])throws Exception
	{
		RecogNetwork network = new RecogNetwork();
		network.width=128;
		network.height=128;
		Thread t1 = new Thread(() -> {
			Main.load(network);
		});
		t1.start();
		DrawWindow frame=new DrawWindow();
		
		Pixels px=new Pixels(50,50,280,28);
		
		JPanel panel=new NumberPanel(px);
		
		frame.add(panel,BorderLayout.CENTER);
		
		JPanel facePannel=new FacePannel(network);
		
		frame.add(facePannel,BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}
	public static void load(RecogNetwork network)
	{
		try
		{
			System.out.println("Reading...");
			network.readEasy("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","faceFile.ser");
			System.out.println("Done reading");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
