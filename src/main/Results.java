package main;

import java.awt.*;
import java.util.ArrayList;

public class Results {
	ArrayList<String> resultTitel;
	ArrayList<Double> resultValue;
	int x;
	int y;
	
	public Results(int x, int y)
	{
		resultTitel=new ArrayList<String>();
		resultValue= new ArrayList<Double>();
		this.x=x;
		this.y=y;
	}
	public void allOne()
	{
		for(String s: resultTitel)
		{
			resultValue.add(0.0d);
		}
	}
	public void drawResults(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawString("Results:",x,y);
		for(int i=0; i<resultTitel.size(); i++)
		{
			g2d.setColor(Color.BLACK);
			g2d.drawString(resultTitel.get(i),x,y+20+(i*30));
			g2d.setColor(Color.GREEN);
			g2d.fillRect(x,y+30+(i*30),(int)(50*resultValue.get(i)),10);
		}
	}

}
