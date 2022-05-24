package faceRecognition;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class SeeResult {
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
		
		int total=0;
		int corectFace=0;
		int corectObj=0;
		
		for(int i=0; i<1000; i++)
		{
			total++;
			
			double[] result=network.runForResult(faces.get(r.nextInt(faces.size())));
			if(result[0] >= 0.5d)
			{
				corectFace++;
			}
			
			result=network.runForResult(objects.get(r.nextInt(objects.size())));
			if(result[0] < 0.5d)
			{
				corectObj++;
			}
		}
		System.out.println("Faces correct: "+corectFace+" out of: "+total+" percentage: "+(((corectFace*1.0d)/(total*1.0d))*100.0d)+"%");
		System.out.println("Objects correct: "+corectObj+" out of: "+total+" percentage: "+(((corectObj*1.0d)/(total*1.0d))*100.0d)+"%");
	}
}
