package test.resources.com.sirion.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
 

public class FullScreenCapture extends Database_Password_Change_Query 
{public static String format = "jpg";public static String fileName=null;
	
	public void screenshot(String Username) 
		{
        	try 
        	{
        		Robot robot = new Robot();
        		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        		fileName = System.getProperty("user.dir")+"\\screenshots\\"+"Newly Created User"+" "+ Username +"."+format;
        		ImageIO.write(screenFullImage, format, new File(fileName));
        		APP_LOGS.debug("A full screenshot saved!");
        	} catch (AWTException | IOException ex) 
        		{
        			System.err.println(ex);
        		}
		}
}