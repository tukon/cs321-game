// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

/**
 * A Drawable line, with customizable color and width.
 * @author adam
 */
public class Line implements Drawable
{
	/** The line’s end points. */
	private Point p1, p2;
	
	private Color color;
	
	/** The width of the line, in pixels. */
	private int width;
	
	/**
	 * Creates a new line.
	 * @param p1 Endpoint #1
	 * @param p2 Endpoint #2
	 * @param color The color of the line
	 * @param width The line’s width, in pixels.
	 */
	public Line(Point p1, Point p2, Color color, int width)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.width = width;
	}
	
	/**
	 * Gets endpoint #1 of the line.
	 * @return The first endpoint.
	 */
	public Point getStartPos() { return p1; }
	
	/**
	 * Gets endpoint #2 of the line.
	 * @return The second endpoint.
	 */
	public Point getEndPos() { return p2; }
	
	/**
	 * Draws the line on the game canvas.
	 * @param g Graphics device to draw with
	 */
	@Override
	public void draw(Graphics g)
	{
		if (width == 0)  return;
		
		g.setColor(color);
		
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		
		for (int ii = -(int)floor(width/2); ii <= ceil(width/2); ++ii)
		{
			g.drawLine(p1.x+ii, p1.y, p2.x+ii, p2.y);
			g.drawLine(p1.x-ii, p1.y, p2.x-ii, p2.y);
			g.drawLine(p1.x, p1.y+ii, p2.x, p2.y+ii);
			g.drawLine(p1.x, p1.y-ii, p2.x, p2.y-ii);
		}
	}
}
// EOF