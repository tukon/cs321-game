// PROJECT: Test Game -- prototype for CS 321 project

package edu.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * A Drawable text object that can be displayed on a GamePanel.
 * @author adam
 */
public class TextLabel implements Drawable
{
	private String[] text;
	private Point pos;
	private Color color;
	
	/** Relative line spacing, e.g. `2.0f` for double spacing. */
	private float lineSpacing;
	
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
	 * Draws the text on the game window. This is called by GamePanel.
	 * @param g Graphics device to draw withs
	 */
	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		
		int posY = pos.y;
		
		final int height = (int)(g.getFontMetrics().getAscent() *
			lineSpacing);
		for (String t : text)
		{
			g.drawString(t, pos.x, posY);
			posY += height;
		}
	}
}
// EOF