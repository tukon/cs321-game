// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A Drawable text object that can be displayed on a GamePanel.
 * @author adam
 */
public class TextLabel implements Drawable
{
	/** The text to be displayed. Each array item is one line. */
	private String[] text;
	
	/** Position the text should start at. */
	private Point pos;
	
	/** Color of the text. */
	private Color color;
	
	/** Relative line spacing, e.g. `2.0f` for double spacing. */
	private float lineSpacing;
	
	/** Size of the text, in points. */
	private int size;
	
	/** Style of the text. */
	private int style;
	
	/** If true, text will be horizontally centered. */
	private boolean center;
	
	/**
	 * Create a new text label at the specified position. The text is
	 * left-aligned.
	 * @param text Text to display.
	 * @param pos Position of the text’s baseline onscreen.
	 */
	public TextLabel(String text, Point pos)
	{
		this.text = text.split("\n");
		this.pos = pos;
		color = Color.BLACK;
		lineSpacing = 1.2f;
		size = 12;
		center = false;
	}
	
	/**
	 * Sets the color of the text.
	 * @param color The text’s new color.
	 */
	public void setColor(Color color) { this.color = color; }
	
	/**
	 * Changes the line spacing of the text. This has no effect on one-line
	 * labels.
	 * @param spacing Line spacing, e.g. 2.0f for double-spacing.
	 */
	public void setLineSpacing(float spacing) { lineSpacing = spacing; }
	
	/**
	 * Changes the text to be displayed.
	 * @param text New text
	 */
	public void setText(String text) { this.text = text.split("\n"); }
	
	/**
	 * Sets the position of the text.
	 * @param pos The text’s new position.
	 */
	public void setPos(Point pos) { this.pos = pos; }
	
	/**
	 * Sets the text’s size.
	 * @param s Size in points.
	 */
	public void setSize(int s)
	{
		size = s;
	}
	
	/**
	 * Sets the style of the text.
	 * @param s Font style bitmask
	 * @see java.awt.Font
	 */
	public void setStyle(int s) { style = s; }
	
	/**
	 * Sets whether or not the text should be centered horizontally.
	 * @param center True to center; false to left-align.
	 */
	public void setCenter(boolean center) { this.center = center; }
	
	/**
	 * Draws the text on the game window. This is called by GamePanel.
	 * @param g Graphics device to draw withs
	 */
	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		
		int posY = pos.y;
		int posX = pos.x;
		
		final int height = (int)(g.getFontMetrics().getAscent() *
			lineSpacing);
		
		Font f = g.getFont();
		Font F = new Font(f.getName(), style, size);
		g.setFont(F);
		
		for (String t : text)
		{
			if (center)
			{
				posX = pos.x - 
					g.getFontMetrics().stringWidth(t)/2;
			}
			
			g.drawString(t, posX, posY);
			posY += height;
		}
		
		g.setFont(f);
	}
}
// EOF