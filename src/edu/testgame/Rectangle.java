// PROJECT: Test Game -- prototype for CS413 project
package edu.testgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A simple rectangle that can be displayed on a GamePanel.
 * @author adam
 */
public class Rectangle implements Drawable
{
	private int x, y, width, height;
	private Color outline;
	private Color fill;
	private boolean drawOutline;
	private boolean drawFill;
	
	public Rectangle(int x, int y, int width, int height, Color outline,
		Color fill, boolean drawOutline, boolean drawFill)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.outline = outline;
		this.fill = fill;
		this.drawOutline = drawOutline;
		this.drawFill = drawFill;
	}
	
	public void setFillColor(Color fill) { this.fill = fill; }
	
	public void setOutlineColor(Color outline) { this.outline = outline; }
	
	@Override
	public void draw(Graphics g)
	{
		if (drawFill)
		{
			g.setColor(fill);
			g.fillRect(x, y, width, height);
		}
		
		if (drawOutline)
		{
			g.setColor(outline);
			g.drawRect(x, y, width, height);
		}
	}
}
