package faceRecognition;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import java.awt.*;

public class PhotoProcessing {
	
	public static void main(String args[]) throws Exception
	{
		RecogNetwork network=new RecogNetwork();
		network.width=128;
		network.height=128;
		System.out.println("Reading...");
		network.readEasy("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","faceFile.ser");
		System.out.println("Done reading");
		
		File imageFile=new File("C:\\Users\\strio\\Desktop\\white.jpg");
		BufferedImage image;
	
		image=ImageIO.read(imageFile);
		
		System.out.println("Searching:");
		search(network, 200, 100, 0.3f, 5, image,"white1",0.80d);
		System.out.println("Searching:");
		search(network, 200, 100, 0.3f, 5, image,"white2",0.85d);
		System.out.println("Searching:");
		search(network, 200, 100, 0.3f, 5, image,"white3",0.90d);
		System.out.println("Searching:");
		search(network, 200, 100, 0.3f, 5, image,"white4",0.95d);

		
		imageFile =new File("C:\\Users\\strio\\Desktop\\green.jpg");
		image=ImageIO.read(imageFile);
		
		System.out.println("Searching:");
		search(network, 310, 180, 0.3f, 5, image,"green1",0.80d);
		System.out.println("Searching:");
		search(network, 310, 180, 0.3f, 5, image,"green2",0.85d);
		System.out.println("Searching:");
		search(network, 310, 180, 0.3f, 5, image,"green3",0.90d);
		System.out.println("Searching:");
		search(network, 310, 1800, 0.3f, 5, image,"green4",0.95d);
		

		
		imageFile =new File("C:\\Users\\strio\\Desktop\\pink.jpg");
		image=ImageIO.read(imageFile);
		
		System.out.println("Searching:");
		search(network, 350, 170, 0.3f, 5, image,"pink1",0.80d);
		System.out.println("Searching:");
		search(network, 350, 170, 0.3f, 5, image,"pink2",0.85d);
		System.out.println("Searching:");
		search(network, 350, 170, 0.3f, 5, image,"pink3",0.90d);
		System.out.println("Searching:");
		search(network, 350, 170, 0.3f, 5, image,"pink4",0.95d);
		
		imageFile =new File("C:\\Users\\strio\\Desktop\\brown.jpg");
		image=ImageIO.read(imageFile);
		
		System.out.println("Searching:");
		search(network, 250, 200, 0.3f, 5, image,"brown1",0.80d);
		System.out.println("Searching:");
		search(network, 250, 200, 0.3f, 5, image,"brown2",0.85d);
		System.out.println("Searching:");
		search(network, 250, 200, 0.3f, 5, image,"brown3",0.90d);
		System.out.println("Searching:");
		search(network, 250, 200, 0.3f, 5, image,"brown4",0.95d);

		
	}

	public static void searchFaces(String path, RecogNetwork network)
	{
		File file = new File(path);
		
		try {
		
			if(file.exists())
			{
				
				BufferedImage image;
				
				image=ImageIO.read(file);
				
				int max=0;
				int min=0;
				
				if(image.getHeight() <= image.getWidth())
				{
					max = image.getHeight()/2;
				}
				else 
				{
					max = image.getWidth()/2;
				}
				min = max/2;
				
				int smallStep = min / 30;
				
				String [] pathArray = path.replaceAll(Pattern.quote("\\"), "/").split("/");
				
				String name = pathArray[pathArray.length - 1];
				name = name.replaceAll(Pattern.quote("."), ",").split(",")[0];
				System.out.println("Searching:");
				search(network, max, min, 0.3f, smallStep, image,name+"90%",0.90d);
				System.out.println("Searching:");
				search(network, max, min, 0.3f, smallStep, image,name+"92%",0.92d);
				
				
			}
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void drawRects(ArrayList<Rectangle> rects, BufferedImage image, int lineWidth)throws Exception
	{
		for(Rectangle rect : rects)
		{
			int x = rect.x;
			int y = rect.y;
			int width = rect.width; 
			int height = rect.height;
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j< lineWidth; j++)
				{
					if((y + j) < image.getHeight())
						image.setRGB(x + i, y + j, Color.RED.getRGB());
				}
				for(int j = 0; j< lineWidth; j++)
				{
					if((y + j + height) < image.getHeight())
						image.setRGB(x + i, y + j + height, Color.RED.getRGB());
				}
			}
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j< lineWidth; j++)
				{
					if((x + j) < image.getWidth())
						image.setRGB(x + j, y + i, Color.RED.getRGB());
				}
				for(int j = 0; j< lineWidth; j++)
				{
					if((x + j + + width) < image.getWidth())
						image.setRGB(x + j + width, y + i, Color.RED.getRGB());
				}
			}
		}
	}
	
	public static void search(RecogNetwork network, int sizeMax, int sizeMin, float step, int minimizeStep, BufferedImage image, String name, double approuval)throws Exception
	{
		int maxX = image.getWidth();
		int maxY = image.getHeight();
		int size = sizeMax;
		
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		
		while(size >= sizeMin)
		{
			System.out.println("Searching with size: "+size);
			int stepInt = (int)(size * step);
			for(int y = 0; y < (maxY-size); y += stepInt)
			{
				for(int x = 0; x < (maxX-size); x+= stepInt)
				{
					BufferedImage newImage=getPart(x, y, size, image);
					double result[] = network.runForResult(newImage);
					if(result[0] >= approuval)
					{
						rects.add(new Rectangle(x, y, size, size));
					}
				}
			}
			size = size - minimizeStep;
		}
		System.out.println(rects.size());
		BufferedImage newImage= new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i< maxY; i++)
		{
			for(int j=0; j< maxX; j++)
			{
				newImage.setRGB(j,i,image.getRGB(j,i));
			}
		}
		drawRects(rects,newImage,3);
		System.out.println("Saving new image");
		File file = new File("C:\\Users\\strio\\Desktop\\new"+name+".png");
        ImageIO.write(newImage, "jpg", file);
        System.out.println("Saved new image");
	}
	
	public static BufferedImage getPart(int x, int y, int size, BufferedImage image)
	{
		BufferedImage newImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				newImage.setRGB(j, i, image.getRGB(x + j, y + i));
			}
		}
		return newImage;
	}
}
