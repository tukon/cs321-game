// PROJECT: Archer -- a game developed for CS 321
package edu.archer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A simple rectangle that can be displayed on a GamePanel.
 * @author adam
 */
public class Rectangle implements Drawable
{
	/** Position of the rectangle’s top left corner. */
	private Point pos;
	
	/** Size of the rectangle, in pixels. */
	private Point size;
	
	/** Color of the rectangle’s outline. */
	private Color outline;
	
	/** Color the rectangle should be filled in with. */
	private Color fill;
	
	/** Whether or not to draw the outline. */
	private boolean drawOutline;
	
	/** Whether or not to fill in the rectangle. */
	private boolean drawFill;
	
	/**
	 * Creates a new rectangle.
	 * @param x Horizontal positon (of the top left corner) on the canvas.
	 * @param y Vertical positon (of the top left corner) on the canvas.
	 * @param width Width of the rectangle
	 * @param height Height of the rectangle
	 * @param outline Outline color
	 * @param fill Fill color
	 * @param drawOutline Whether or not to draw the outline.
	 * @param drawFill Whether or not to fill the rectangle.
	 */
	public Rectangle(int x, int y, int width, int height, Color outline,
		Color fill, boolean drawOutline, boolean drawFill)
	{
		pos = new Point(x, y);
		size = new Point(width, height);
		this.outline = outline;
		this.fill = fill;
		this.drawOutline = drawOutline;
		this.drawFill = drawFill;
	}
	
	/**
	 * Sets the rectangle’s fill color.
	 * @param fill The new fill color.
	 */
	public void setFillColor(Color fill) { this.fill = fill; }
	
	/**
	 * Sets the rectangle’s outline color.
	 * @param outline The new outline color.
	 */
	public void setOutlineColor(Color outline) { this.outline = outline; }
	
	/**
	 * Sets whether or not the rectangle shall be filled.
	 * @param enable Set to `true` to fill the rectangle.
	 */
	public void toggleFill(boolean enable) { drawFill = enable; }
	
	/**
	 * Sets whether or not the outline should be drawn.
	 * @param enable Set to `true` to draw the outline.
	 */
	public void toggleOutline(boolean enable) { drawOutline = enable; }
	
	/** Set the rectangle’s width.
	 * @param width The new width, in pixels.
	 */
	public void setWidth(int width) { size.x = width; }
	
	/** Set the rectangle’s height.
	 * @param height The new height, in pixels.
	 */
	public void setHeight(int height) { size.y = height; }
	
	/**
	 * Sets the rectangle’s horizontal position.
	 * @param x The new horizontal position, on the canvas.
	 */
	public void setX(int x) { pos.x = x; }
	
	/**
	 * Sets the rectangle’s vertical position.
	 * @param y The new horizontal position, on the canvas.
	 */
	public void setY(int y) { pos.y = y; }
	
	/**
	 * Draws the rectangle on the canvas. This is called by GamePanel.
	 * @param g Graphics object to draw with.
	 */
	@Override
	public void draw(Graphics g)
	{
		if (drawFill)
		{
			g.setColor(fill);
			g.fillRect(pos.x, pos.y, size.x, size.y);
		}
		
		if (drawOutline)
		{
			g.setColor(outline);
			g.drawRect(pos.x, pos.y, size.x, size.y);
		}
	}
}
// EOF