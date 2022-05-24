package faceRecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import javax.imageio.ImageIO;

public class TestGrey {
	public static void main(String args[])throws Exception
	{
		File file=new File("C:\\Users\\strio\\Desktop\\0007.jpg");
		BufferedImage img = null;
		img=ImageIO.read(file);
		
		BufferedImage image=new BufferedImage(img.getWidth(), img.getHeight(),  BufferedImage.TYPE_INT_RGB);
		
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
		
		BufferedImage resizedImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(image, 0, 0, 128, 128, null);
	    graphics2D.dispose();
	    
		file = new File("C:\\Users\\strio\\Desktop\\new0007.jpg");
        ImageIO.write(resizedImage, "jpg", file);
	}
}
