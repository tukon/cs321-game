// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents an arbitrary 2D shape, defined as a closed loop of line segments.
 * @author adam
 */
public class Polygon implements Drawable
{
	/** The polygon’s position. */
	private int posX, posY;
	
	/** The vertices that define the polygon. */
	private ArrayList<Integer> pXs, pYs;
	
	/**
	 * The vertices’ actual position onscreen. This is “cached” in these
	 * arrays so we do not have to recompute these points every time this
	 * thing is drawn.
	 */
	private ArrayList<Integer> actualXs, actualYs;
	
	/** Outline color. */
	private Color outline;
	
	/** Fill color. */
	private Color fill;
	
	/** Whether or not the outline should be drawn. */
	private boolean drawOutline;
	
	/** Whether or not the shape should be filled in. */
	private boolean drawFill;
	
	/**
	 * Creates a new polygon.
	 * @param x Horizontal position
	 * @param y Vertical position
	 * @param outline Outline color
	 * @param fill Fill color
	 * @param drawOutline Draw the outline?
	 * @param drawFill Fill the polygon?
	 */
	public Polygon(int x, int y,Color outline, Color fill,
		boolean drawOutline, boolean drawFill)
	{
		posX = x;
		posY = y;
		this.outline = outline;
		this.fill = fill;
		this.drawOutline = drawOutline;
		this.drawFill = drawFill;
		
		pXs = new ArrayList<>();
		pYs = new ArrayList<>();
		actualXs = new ArrayList<>();
		actualYs = new ArrayList<>();
	}
	
	/**
	 * Add a new point to the polygon. The position of the points is
	 * relative to the whole polygon’s position
	 * @param x Horizontal position
	 * @param y Vertical position
	 */
	public void addPoint(int x, int y)
	{
		pXs.add(x);  pYs.add(y);
		actualXs.add(posX + x);
		actualYs.add(posY + y);
	}
	
	/**
	 * Move the polygon to a new position.
	 * @param x Horizontal position
	 * @param y Vertical position
	 */
	public void setPos(int x, int y)
	{
		posX = x;  posY = y;
		for (int ii = 0; ii < actualXs.size(); ++ii)
		{
			actualXs.set(ii, posX + pXs.get(ii));
			actualYs.set(ii, posY + pYs.get(ii));
		}
	}
	
	/**
	 * Get the polygon’s position.
	 * @return The absolute position of the polygon
	 */
	public Point getPos() { return new Point(posX, posY); }
	
	/**
	 * Called by GamePanel to draw the polygon.
	 * @param g Graphics device to draw with
	 */
	@Override
	public void draw(Graphics g)
	{
		int xs[] = new int[actualXs.size()];
		int ys[] = new int[actualYs.size()];
		for (int ii = 0; ii < actualXs.size(); ++ii)
		{
			xs[ii] = actualXs.get(ii);
			ys[ii] = actualYs.get(ii);
		}
		
		if (drawFill)
		{
			g.setColor(fill);
			g.fillPolygon(xs, ys, actualXs.size());
		}
		
		if (drawOutline)
		{
			g.setColor(outline);
			g.drawPolygon(xs, ys, actualXs.size());
		}
	}
}
// EOF