// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * A Drawable, clickable button. An event handler is called when it is clicked.
 * @see ButtonListener
 * @author adam
 */
public class Button implements Drawable
{
	/** States a button can be in. */
	public enum State { UNFOCUSED, FOCUSED, PRESSED };
	
	/** The button’s current state. */
	protected State state;
	
	/** Position of the button’s top left corner. */
	protected Point pos;
	
	/** Size of the button. */
	protected Point size;
	
	/** Text displayed on the button. */
	protected String text;
	
	/** Used by the click handler to identify which button was clicked. */
	private int id;
	
	/** The main body of the button. */
	private Rectangle body;
	
	/** The text label shown on the button. */
	private TextLabel label;
	
	/** Reacts when the button is clicked. */
	private ButtonListener handler;
	
	private static final Color OUTLINE = new Color(0xA0A0A0);
	private static final Color FILL = new Color(0xC5C5C5);
	private static final Color TEXT = Color.BLACK;
	private static final Color FOCUS_OUTLINE = Color.WHITE;
	private static final Color FOCUS_FILL = new Color(0xD5D5D5);
	private static final Color FOCUS_TEXT = TEXT;
	private static final Color PRESS_OUTLINE = new Color(0xD0D0D0);
	private static final Color PRESS_FILL = new Color(0xADADAD);
	private static final Color PRESS_TEXT = TEXT;
	
	/**
	 * Creates a new button.
	 * @param id Used by the click listener to tell which button was clicked
	 * @param x Horizontal position of the top left corner
	 * @param y Vertical position of the top left corner
	 * @param w Width
	 * @param h Height
	 * @param text Text shown on the button
	 */
	public Button(int id, int x, int y, int w, int h, String text)
	{
		this.id = id;
		pos = new Point(x, y);
		size = new Point(w, h);
		this.text = text;
		
		body = new Rectangle(x, y, w, h, OUTLINE, FILL, true, true);
		label = new TextLabel(text, new Point(x, y));
		label.setColor(TEXT);
	}
	
	/**
	 * Set the listener that will react when the button is clicked.
	 * @param listener The thing that should react when the button is
	 *                 clicked.
	 */
	public void setListener(ButtonListener listener)
	{
		handler = listener;
	}
	
	/**
	 * Call this whenever the mouse button is pressed.
	 * @param e Event object describing the state of the mouse.
	 */
	public void press(MouseEvent e)
	{
		if (state == State.FOCUSED)
		{
			state = State.PRESSED;
			body.setFillColor(PRESS_FILL);
			body.setOutlineColor(PRESS_OUTLINE);
			label.setColor(PRESS_TEXT);	
		}
	}
	
	/**
	 * Call this whenever the mouse button is released.
	 * @param e Event object describing the state of the mouse.
	 */
	public void release(MouseEvent e)
	{
		if (state == State.PRESSED)  handler.clicked(id);
		update(e);
	}
	
	/**
	 * Call this whenever the mouse is moved.
	 * @param e Event object describing the state of the mouse.
	 */
	public void update(MouseEvent e)
	{
		Point curPos = e.getPoint();
		int left = pos.x;
		int right = pos.x + size.x;
		int top = pos.y;
		int bottom = pos.y + size.y;
		
		if (curPos.x > left && curPos.x < right &&
			curPos.y > top && curPos.y < bottom)
		{
			if (state != State.PRESSED)
			{
				state = State.FOCUSED;
				body.setFillColor(FOCUS_FILL);
				body.setOutlineColor(FOCUS_OUTLINE);
				label.setColor(FOCUS_TEXT);
			}
		}
		else
		{
			state = State.UNFOCUSED;
			body.setFillColor(FILL);
			body.setOutlineColor(OUTLINE);
			label.setColor(TEXT);
			return;
		}
		
		if (e.getModifiersEx()== MouseEvent.BUTTON1_DOWN_MASK)
		{
			state = State.PRESSED;
			body.setFillColor(PRESS_FILL);
			body.setOutlineColor(PRESS_OUTLINE);
			label.setColor(PRESS_TEXT);
		}
	}
	
	/**
	 * Draws the button on the game window. This is called by GamePanel.
	 * @param g Graphics device to draw with.
	 */
	@Override
	public void draw(Graphics g)
	{
		body.draw(g);
		
		int strW = (int)(g.getFontMetrics().getStringBounds(
			text, g).getWidth());
		int strH = (int)(g.getFontMetrics().getAscent());
		int dY = (state == State.PRESSED) ? 1 : 0;
		label.setPos(new Point(pos.x + size.x/2 - strW/2,
			pos.y + size.y/2 + strH/2 + dY));
		label.draw(g);
	}
}
// EOF