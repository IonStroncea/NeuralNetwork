package imageProcesing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;

import javax.imageio.ImageIO;

import neuralNetWork.InternalNeuron;
import neuralNetWork.NeuralNetwork;


public class Main {
	public static void main(String args[])throws Exception 
	{
			NeuralNetwork neuralNetwork=new NeuralNetwork();
//			int [] neurons= {512,256,128,10};
//			neuralNetwork=new NeuralNetwork(28*28,4,neurons);
//			neuralNetwork.generateRand("Trying someting new");

			
			int cicles=10;
			for(int rep=0;rep<cicles;rep++) 
			{
//				FileInputStream file1 = new FileInputStream("C:\\Users\\strio\\eclipse-workspace\\DrawNumber\\network.ser");
//	            ObjectInputStream in = new ObjectInputStream(file1);
//				neuralNetwork=(NeuralNetwork)in.readObject();
//	            in.close();
//	            file1.close();
				System.out.println("Reading...");
				neuralNetwork.readFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","numbers.ser");
				System.out.println("Read");
				for(int dd5=0;dd5<6;dd5++)
				{
		            for(int dd4=0;dd4<10;dd4++)
		            {
						for(int dd3=0;dd3<10;dd3++)
						{
							System.out.println("10 Sets:"+(+dd5*100+dd4*10+dd3+1)+" out of:"+6*10*10+" cicle:"+(rep+1)+" out of "+cicles+"");
							for(int dd2=0;dd2<10;dd2++)
							{
								for(int dd1=0;dd1<10;dd1++)
								{
									String path="D:\\numberFotos\\train\\";
									String imagePath=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
									String extension=".png";
									int i=0;
									
									while(!(new File(imagePath+i+extension).exists()))
									{
										i++;
									}
									File imageFile=new File(imagePath+i+extension);
									BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
								
									image=ImageIO.read(imageFile);
									double pixelList[]=new double[28*28];
									for(int j=0;j<28;j++)
									{
										for(int k=0;k<28;k++)
										{
											int pixel=image.getRGB(k,j);
											Color color = new Color(pixel, true);
											double value=color.getRed()/254.0d;
											pixelList[j*28+k]=value;
										}
									}
									double expected[]=new double[10];
									expected[i]=1.0d;

									neuralNetwork.run(pixelList);
									neuralNetwork.learn(expected);							
									
								}
								
							}
							neuralNetwork.addModif(10);
							neuralNetwork.newLearn();
						}	
						System.out.println("Saving...");
						neuralNetwork.writeFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","numbers.ser");
						System.out.println("Saved");
		            }
		            
				}
//				FileOutputStream file = new FileOutputStream("C:\\Users\\strio\\eclipse-workspace\\DrawNumber\\network.ser");
//	            ObjectOutputStream out = new ObjectOutputStream(file);
//	            out.writeObject(neuralNetwork);
//	            out.close();
//	           	file.close();

			}
	}
}
