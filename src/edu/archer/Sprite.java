// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

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
		if (imgFilename != null)
		{
			img = ResourceLoader.loadImage(imgFilename);
		}
		else
		{
			img = null;
		}
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
	 * Sets the sprite’s position.
	 * @param x Horizontal position, in global coordinates.
	 * @param y Vertical position, in global coordinates
	 */
	public void setPos(int x, int y)
	{
		pos = new Point(x, y);
	}
	
	/**
	 * Gets the sprite’s position.
	 * @return The position, in global coordinates.
	 */
	public Point getPos() { return pos; }
	
	/**
	 * Draws the sprite on the game window. This is called by GamePanel.
	 * @param g Graphics device to draw with.
	 */
	@Override
	public void draw(Graphics g)
	{
		if (imgTransformed != null)
		{
			g.drawImage(imgTransformed, pos.x, pos.y, null);
		}
	}
}
// EOF