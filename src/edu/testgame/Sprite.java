// PROJECT: Test Game -- prototype for CS 321 project

package edu.testgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a visible object with a position and rotation. This base class
 * can be extended to create objects composed of multiple images.
 * @author adam
 */
public class Sprite implements Drawable
{
	/** The primary image for this object. */
	protected BufferedImage img;
	
	/** Rotated version of the primary image; generated by `setAngle()`. */
	protected BufferedImage imgTransformed;
	protected Point pos;
	protected double ang;
	
	public Sprite(String imgFilename, int posX, int posY)
	{
		pos = new Point(posX, posY);
		ang = 0.0;
		img = loadImg(imgFilename);
		imgTransformed = img;
	}
	
	/**
	 * Rotates the sprite to the given angle.
	 * @param angle Absolute angle to rotate to, in radians.
	 */
	public void setAngle(double angle)
	{
		ang = angle;
		
		// Rotate the image and save the result in imgTransformed:
		double centerX = img.getWidth(null) / 2;
		double centerY = img.getHeight(null) / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(ang,
			centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, 
			AffineTransformOp.TYPE_BILINEAR);
		
		imgTransformed = op.filter(img, null);
	}
	
	/**
	 * Loads the specified image from a file. The image is assumed to be in
	 * the `res/` directory.
	 * 
	 * TODO: right now, `res/` probably has to be in the working directory.
	 *       Fix that.
	 * 
	 * @param filename Name of the file to load, relative to `res/`
	 * @return The loaded image, or null if it could not be loaded.
	 */
	protected final BufferedImage loadImg(String filename)
	{
		if (filename == null || filename.equals(""))  return null;
		
		BufferedImage image = null;
		File imgFile = new File("res/" + filename);
		try
		{
			image = ImageIO.read(imgFile);
		}
		catch (IOException e)
		{
			System.err.println("Error loading image res/" + filename
				+ "\nStack trace:");
			e.printStackTrace();
			System.exit(1);
		}
		return image;
	}
	
	/**
	 * Draws the sprite on the game window. This is called by GamePanel.
	 * @param g 
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(imgTransformed, pos.x, pos.y, null);
	}
}
// EOF