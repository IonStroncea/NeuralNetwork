package neuralNetWork;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Collections;

import javax.imageio.ImageIO;

public class TestRun {
	public static void main(String args[])
	{
		try
		{
			NeuralNetwork neuralNetwork=new NeuralNetwork();
			System.out.println("Reading...");
			neuralNetwork.readFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","numbers.ser");
			System.out.println("Read");
            
            int correct=0;
            int total=0;
            double error=0.0d;
            for(int dd4=0;dd4<10;dd4++)
            {
            	System.out.println((dd4+1)+" out of "+10);
				for(int dd3=0;dd3<10;dd3++)
				{
					for(int dd2=0;dd2<10;dd2++)
					{
						for(int dd1=0;dd1<10;dd1++)
						{
							String path="D:\\numberFotos\\test\\";
							String imagePath=path+"00"+dd4+""+dd3+""+dd2+""+dd1+"-num";
							String extension=".png";
							int i=0;
							//System.out.println("Reading:"+dd4+""+dd3+""+dd2+""+dd1);
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
								//System.out.println();
								for(int k=0;k<28;k++)
								{
									int pixel=image.getRGB(k,j);
									Color color = new Color(pixel, true);
									double value=color.getRed()/254.0d;
									pixelList[j*28+k]=value;
									//System.out.print(value==0.0?0.0d:1.1d);
								}
							}
							
							neuralNetwork.run(pixelList);
							double result[]=neuralNetwork.getResult();
							//System.out.print("Expected "+i);
//							for(int k=0;k<10;k++)
//							{
//								System.out.print(String.format(" %.3f",result[k]));
//							}
							int found=getIndexOfLargest(result);
							total++;
							if(found==i)
							{
								correct++;
							}
							for(int j=0;j<10;j++)
							{
								error+=result[j];
							}
							error-=result[found];
						}
					}
				}
            }
            System.out.println("Percentage of correction:"+((1.0d*correct)/(total*1.0d)*100)+" Average error= "+(error/total));
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public static int getIndexOfLargest( double[] array )
	{
	  if ( array == null || array.length == 0 ) return -1; // null or empty

	  int largest = 0;
	  for ( int i = 1; i < array.length; i++ )
	  {
	      if ( array[i] > array[largest] ) largest = i;
	  }
	  return largest; // position of the first largest found
	}
}
