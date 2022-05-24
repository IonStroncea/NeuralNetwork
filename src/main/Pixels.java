package main;

import java.util.ArrayList;
import java.awt.*;

public class Pixels {
	public ArrayList<Integer> pixels;
	public int x=0;
	public int y=0;
	public int size=1;
	public int pixelPerSide=1;
	public int pixelSize=size/pixelPerSide;
	
	public Pixels() 
	{
		pixels=new ArrayList<Integer>();
		pixels.add(0);
	}
	public Pixels(int size, int pixelPerSide) 
	{
		this.size=size;
		this.pixelPerSide=pixelPerSide;
		pixelSize=size/pixelPerSide;
		pixels=new ArrayList<Integer>();
		for(int i=0;i<pixelPerSide*pixelPerSide;i++)
		{
			pixels.add(0);
		}
	}
	public Pixels(int x, int y,int size, int pixelPerSide) 
	{
		this.x=x;
		this.y=y;
		this.size=size;
		this.pixelPerSide=pixelPerSide;
		pixelSize=size/pixelPerSide;
		pixels=new ArrayList<Integer>();
		for(int i=0;i<pixelPerSide*pixelPerSide;i++)
		{
			pixels.add(0);
		}
	}
	public void paintAll(Graphics g)
	{
		//System.out.println("paintAll");
		Graphics2D g2d = (Graphics2D) g;
		for(int i=0; i<pixelPerSide; i++)
		{
			for(int j=0; j<pixelPerSide; j++)
			{
				g2d.setColor(new Color(255/10*pixels.get(i*pixelPerSide+j),255/10*pixels.get(i*pixelPerSide+j),255/10*pixels.get(i*pixelPerSide+j)));
				g2d.fillRect(x+(j*pixelSize),y+(i*pixelSize),pixelSize,pixelSize);
				
			}
		}
		g2d.setColor(Color.WHITE);
		for(int i=0; i<pixelPerSide; i++)
		{
			for(int j=0; j<pixelPerSide; j++)
			{
				g2d.drawRect(x+(i*pixelSize),y+(j*pixelSize),pixelSize,pixelSize);				
			}
		}
	}
	public boolean drawLine(int x, int y, int color)
	{
		boolean change=false;
		if((x>this.x && (this.x+size)>x) && (y>this.y && (this.y+size)>y))
		{
			int posX=(int)Math.floor((x-this.x)*1.0f/(pixelSize*1.0f));
			int posY=(int)Math.floor((y-this.y)*1.0f/(pixelSize*1.0f));
			pixels.set(posY*pixelPerSide+posX,color);
			if(color==10)
			{
				int sides=5;
				if(posY-1>=0 && pixels.get((posY-1)*pixelPerSide+posX)<sides)
				{
					pixels.set((posY-1)*pixelPerSide+posX,sides);
				}
				if(posY+1<28 && pixels.get((posY+1)*pixelPerSide+posX)<sides)
				{
					pixels.set((posY+1)*pixelPerSide+posX,sides);
				}
				if(posX-1>=0 && pixels.get((posY)*pixelPerSide+posX-1)<sides)
				{
					pixels.set(posY*pixelPerSide+posX-1,sides);
				}
				if(posX+1<28 && pixels.get((posY)*pixelPerSide+posX+1)<sides)
				{
					pixels.set(posY*pixelPerSide+posX+1,sides);
				}
				
			}
			if(color==0)
			{
				int sides=0;
				if(posY-1>=0 && pixels.get((posY-1)*pixelPerSide+posX)>sides)
				{
					pixels.set((posY-1)*pixelPerSide+posX,sides);
				}
				if(posY+1<28 && pixels.get((posY+1)*pixelPerSide+posX)>sides)
				{
					pixels.set((posY+1)*pixelPerSide+posX,sides);
				}
				if(posX-1>=0 && pixels.get((posY)*pixelPerSide+posX-1)>sides)
				{
					pixels.set(posY*pixelPerSide+posX-1,sides);
				}
				if(posX+1<28 && pixels.get((posY)*pixelPerSide+posX+1)>sides)
				{
					pixels.set(posY*pixelPerSide+posX+1,sides);
				}
				
			}
			change=true;
		}
		return change;
	}
}
