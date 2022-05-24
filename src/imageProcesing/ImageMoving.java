package imageProcesing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import neuralNetWork.NeuralNetwork;

public class ImageMoving {
	public static void main(String args[])throws Exception
	{

		for(int dd5=0;dd5<5;dd5++)
		{
            for(int dd4=0;dd4<10;dd4++)
            {
				for(int dd3=0;dd3<10;dd3++)
				{
					System.out.println((dd5*100+dd4*10+dd3)+" out of "+(5*10*10));
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
							double new11[]=move(0,2,pixelList);
							double new12[]=move(2,0,pixelList);
							double new13[]=move(0,-2,pixelList);
							double new14[]=move(-2,0,pixelList);
							double new15[]=move(2,2,pixelList);
							double new16[]=move(-2,-2,pixelList);
							double new17[]=move(2,-2,pixelList);
							double new18[]=move(-2,2,pixelList);
							
							double new21[]=move(0,4,pixelList);
							double new22[]=move(4,0,pixelList);
							double new23[]=move(0,-4,pixelList);
							double new24[]=move(-4,0,pixelList);
							double new25[]=move(4,4,pixelList);
							double new26[]=move(-4,-4,pixelList);
							double new27[]=move(4,-4,pixelList);
							double new28[]=move(-4,4,pixelList);
							
							double new31[]=move(0,8,pixelList);
							double new32[]=move(8,0,pixelList);
							double new33[]=move(0,-8,pixelList);
							double new34[]=move(-8,0,pixelList);
							double new35[]=move(8,8,pixelList);
							double new36[]=move(-8,-8,pixelList);
							double new37[]=move(8,-8,pixelList);
							double new38[]=move(-8,8,pixelList);
							
							BufferedImage bufferedImage=new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
							
							String name;
							path="D:\\numberFotos\\11\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new11,bufferedImage);
							File file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\12\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new12,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\13\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new13,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\14\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new14,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\15\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new15,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\16\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new16,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\17\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new17,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\18\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new18,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\21\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new21,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\22\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new22,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\23\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new23,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\24\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new24,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\25\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new25,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\26\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new26,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\27\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new27,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\28\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new28,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\31\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new31,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\32\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new32,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\33\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new33,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\34\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new34,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\35\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new35,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\36\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new36,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);
					        
					        path="D:\\numberFotos\\37\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new37,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

					        
					        path="D:\\numberFotos\\38\\";
							name=path+"0"+dd5+""+dd4+""+dd3+""+dd2+""+dd1+"-num";
							toBuff(new38,bufferedImage);
							file = new File(name+i+extension);
					        ImageIO.write(bufferedImage, "png", file);

						}

					}	
				}						
            }
		}	
	}
	public static double[] move(int x,int y,double[] pixelList)
	{
		int xr=0;
		int yr=0;
		int xw=0;
		int yw=0;
		if(x>0)
			xr=x;
		else
			xw=-x;
		if(y>0)
			yr=y;
		else
			yw=-y;
		
		double new1[]=new double[28*28];
		for(int j=0;j<28-Math.abs(y);j++)
		{
			for(int k=0;k<28-Math.abs(x);k++)
			{
				//System.out.println(j+" "+k+" "+y+" "+x);
				new1[(j+yw)*28+k+xw]=pixelList[(j+yr)*28+k+xr];
			}
		}
		return new1;
	}
	public static void toBuff(double[] pixelList,BufferedImage bufferedImage)
	{
		for(int j=0;j<28;j++)
		{
			for(int k=0;k<28;k++)
			{
				int color=(int)pixelList[(j)*28+k]*255;
				int rgb = new Color(color,color,color).getRGB();
				bufferedImage.setRGB(k,j,rgb);
				//pixelList[(j)*28+k];
			}
		}
	}
}

