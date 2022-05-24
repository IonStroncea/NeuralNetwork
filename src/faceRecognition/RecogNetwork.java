package faceRecognition;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import neuralNetWork.NeuralNetwork;
import java.awt.image.BufferedImage;
import java.awt.*;

public class RecogNetwork{
	
	private NeuralNetwork neuralNetwork;
	public int width;
	public int height;
	public boolean ready=false;
	
	public RecogNetwork(String generate, int width, int height, int entryNeurons, int internalLayersNum, int ... neurons) throws Exception
	{
		neuralNetwork=new NeuralNetwork(entryNeurons, internalLayersNum, neurons);
		this.width=width;
		this.height=height;
		neuralNetwork.generateRand(generate);
	}
	
	public RecogNetwork()
	{
		neuralNetwork=new NeuralNetwork();
	}
	
	public void read(String path)throws Exception
	{
		FileInputStream file1 = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(file1);
        neuralNetwork=(NeuralNetwork)in.readObject();
        in.close();
        file1.close();
	}
	
	public void write(String path)throws Exception
	{
		FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(neuralNetwork);
        out.close();
       	file.close();
	}
	
	public void writeEasy(String path, String name)throws Exception
	{
		neuralNetwork.writeFile(path, name);
	}
	
	public void readEasy(String path, String name)throws Exception
	{
		neuralNetwork.readFile(path, name);
		ready = true;
	}
	
	public double[] runForResult(BufferedImage image)
	{
		BufferedImage workImage=makeGrey(resize(image));
		
		double pixelList[]=new double[width*height];
		for(int j=0;j<height;j++)
		{
			for(int k=0;k<width;k++)
			{
				int pixel=workImage.getRGB(k,j);
				Color color = new Color(pixel, true);
				double value=color.getRed()/254.0d;
				pixelList[j*width+k]=value;
			}
		}
		
		neuralNetwork.run(pixelList);
		
		
		return neuralNetwork.getResult();
	}
	
	public void runForLearn(BufferedImage image, double ... expected)
	{
		BufferedImage workImage=resize(makeGrey(image));
		double pixelList[]=new double[width*height];
		for(int j=0;j<height;j++)
		{
			for(int k=0;k<width;k++)
			{
				int pixel=workImage.getRGB(k,j);
				Color color = new Color(pixel, true);
				double value=color.getRed()/254.0d;
				pixelList[j*width+k]=value;
			}
		}
		
		neuralNetwork.run(pixelList);
		neuralNetwork.learn(expected);
	}
	
	public void applyLearn(int setsize)
	{
		neuralNetwork.addModif(setsize);
	}
	
	private BufferedImage makeGrey(BufferedImage img)
	{
		BufferedImage image=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int i=0; i<img.getHeight(); i++)
		{
			for (int j=0; j<img.getWidth(); j++)
			{
				float r = new Color(img.getRGB(j, i)).getRed();
				float g = new Color(img.getRGB(j, i)).getGreen();
				float b = new Color(img.getRGB(j, i)).getBlue();
				int grayScaled = (int)(r+g+b)/3;
				image.setRGB(j, i, new Color(grayScaled, grayScaled, grayScaled).getRGB());
			}
		}
		return image;
	}
	
	private BufferedImage resize(BufferedImage img) 
	{ 
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(img, 0, 0, width, height, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
}
