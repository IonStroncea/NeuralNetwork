package faceRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.imageio.ImageIO;

public class Learn {
	public static void main(String args[])throws Exception
	{
		
//		int [] neurons= {1000,500,100,25,5,1};
//		String generate="new";
//		RecogNetwork network=new RecogNetwork(generate,128,128,128*128,6,neurons);
		RecogNetwork network=new RecogNetwork();
		network.width=128;
		network.height=128;
		System.out.println("Reading...");
		network.readEasy("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","faceFile.ser");
		System.out.println("Done reading");
		
		ArrayList <BufferedImage> faces  = new ArrayList<BufferedImage>();
		ArrayList <BufferedImage> objects  = new ArrayList<BufferedImage>();
		double [] resultFace= {1.0d};
		double [] resultObj= {0.0d};
		
		Random r=new Random();
		
		readFace(faces);
		System.out.println("Faces size: "+faces.size());
		
		//addRandomImages(objects);
		//System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"airplane");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"automobile");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"bird");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"cat");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"deer");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"dog");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"truck");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"ship");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"horse");
		System.out.println("Objects size: "+objects.size());
		
		readSet(objects,"frog");
		System.out.println("Objects size: "+objects.size());
		
		for(int i=0; i<100; i++)
		{
			System.out.println("i = "+i+" out of "+ 100);
			for(int m=0; m<10; m++)
			{
				System.out.println("m = "+m+" out of "+ 10);
				for(int j=0; j<10; j++)
				{
					System.out.println("j = "+j+" out of "+ 10);
					for(int k=0; k<5; k++)
					{
						int random=r.nextInt(faces.size());
						network.runForLearn(faces.get(random), resultFace);
						
						random=r.nextInt(objects.size());
						network.runForLearn(objects.get(random), resultObj);
					}
				}
				network.applyLearn(100);		
			}
			System.out.println("Faces detection " + efficiency(i, 1000, network, faces, resultFace));
			System.out.println("Object detection " + efficiency(i, 1000, network, objects, resultObj));
			System.out.println("Saving...");
			network.writeEasy("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","faceFile.ser");
			System.out.println("Done saving");
		}
	}
	
	public static void readFace(ArrayList <BufferedImage> faces)throws Exception
	{
		String facesFoler="D:\\facePhotos\\";
		for(int i=0; i<10; i++)
		{
			String folder="0"+i+"000\\";
			for(int j1=0; j1<10; j1++)
			{
				for(int j2=0; j2<10; j2++)
				{
					for(int j3=0; j3<10; j3++)
					{
						//System.out.println(facesFoler+folder+"0"+i+""+j1+""+j2+""+j3+".png");
						File imageFile=new File(facesFoler+folder+"0"+i+""+j1+""+j2+""+j3+".png");
						BufferedImage image;
					
						image=ImageIO.read(imageFile);
						
						faces.add(image);
					}
				}
			}
		}
	}
	
	public static void readSet(ArrayList <BufferedImage> objects, String object)throws Exception
	{
		String folder="D:\\objectPhotos\\"+object+"\\";
		for(int j1=0; j1<1; j1++)
		{
			for(int j2=0; j2<10; j2++)
			{
				for(int j3=0; j3<10; j3++)
				{
					for(int j4=0; j4<10; j4++)
					{
						File imageFile=new File(folder+""+j1+""+j2+""+j3+""+j4+".jpg");
						BufferedImage image;
					
						image=ImageIO.read(imageFile);
						objects.add(image);
					}
				}
			}
		}
	}

	public static String efficiency(int seed, int verifications, RecogNetwork network, ArrayList <BufferedImage> obj, double ... expected)
	{
		int total=0;
		int correct=0;
		Random r=new Random(verifications);
		for(int i=0; i<verifications; i++)
		{
			BufferedImage image=obj.get(r.nextInt(obj.size()));
			double [] result = network.runForResult(image);
			if((expected[0] > 0.5d) && (result[0] >= 0.5d))
			{
				correct++;
			}
			if((expected[0] < 0.5d) && (result[0] < 0.5d))
			{
				correct++;
			}
			total++;
			
		}
		String result="Eficiency: "+(((correct*1.0d)/(total*1.0d))*100.0d)+"% correct "+correct+" out of "+ total;
		return result;
	}

	public static void addRandomImages(ArrayList <BufferedImage> objects)
	{
		Random r=new Random();
		for(int i=0; i<2000; i++)
		{
			BufferedImage image=new BufferedImage(128,128,BufferedImage.TYPE_INT_RGB);
			for(int y=0; y<128; y++)
			{
				for(int x=0; x<128; x++)
				{
					int red = r.nextInt(256);
					int green = r.nextInt(256);
					int blue = r.nextInt(256);
					
					image.setRGB(x, y, new Color(red, green, blue).getRGB());
				}
			}
			objects.add(image);
		}
	}
}
