package faceRecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class SortingPhotos {
	public static void main(String args[])throws Exception
	{
		Random r=new Random();
		
		RecogNetwork network=new RecogNetwork();
		network.width=128;
		network.height=128;
		System.out.println("Reading...");
		network.readEasy("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","faceFile.ser");
		System.out.println("Done reading");
		
		ArrayList <BufferedImage> faces  = new ArrayList<BufferedImage>();
		ArrayList <BufferedImage> objects  = new ArrayList<BufferedImage>();
		
		Learn.readFace(faces);
		System.out.println("Faces size: "+faces.size());
		
		Learn.readSet(objects,"airplane");
		System.out.println("Objects size: "+objects.size());
		
		Learn.readSet(objects,"automobile");
		System.out.println("Objects size: "+objects.size());
		
		Learn.readSet(objects,"bird");
		System.out.println("Objects size: "+objects.size());
		
		sort(network, 500, faces, objects,"D:\\faceSort\\faces","D:\\faceSort\\objects");
	}
	public static void sort(RecogNetwork network,int numberPhotos, ArrayList <BufferedImage> faces, 
			ArrayList <BufferedImage> objects, String facePath, String objPath)throws Exception
	{
		Random r = new Random();
		for(int i=0; i<numberPhotos; i++)
		{
			BufferedImage face = faces.get(r.nextInt(faces.size()));
			BufferedImage obj = objects.get(r.nextInt(objects.size()));
			double [] result = network.runForResult(face);
			if(result[0] >= 0.6d)
			{
				File file = new File(facePath + "\\face" + i + ".png");
				ImageIO.write(face, "png", file);
			}
			else
			{
				File file = new File(objPath + "\\face" + i + ".png");
				ImageIO.write(face, "png", file);
			}
			result = network.runForResult(obj);
			if(result[0] >= 0.6d)
			{
				File file = new File(facePath + "\\obj" + i + ".png");
				ImageIO.write(obj, "png", file);
			}
			else
			{
				File file = new File(objPath + "\\obj" + i + ".png");
				ImageIO.write(obj, "png", file);
			}
		}
	}
}
