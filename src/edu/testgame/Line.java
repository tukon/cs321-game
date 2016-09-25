// PROJECT: Test Game -- prototype for CS413 project
package edu.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

/**
 *
 * @author adam
 */
public class Line implements Drawable
{
	private Point p1, p2;
	private Color color;
	private int width;
	
	public Line(Point p1, Point p2, Color color, int width)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.width = width;
	}
	
	public Point getStartPos() { return p1; }
	
	public Point getEndPos() { return p2; }
	
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