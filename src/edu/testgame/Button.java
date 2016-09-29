// PROJECT: Test Game -- prototype for CS413 project
package edu.testgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam
 */
public class Button implements Drawable
{
	public enum State { UNFOCUSED, FOCUSED, PRESSED };
	
	protected State state;
	protected Point pos;
	protected Point size;
	protected String text;
	
	private int id;
	private Rectangle body;
	private TextLabel label;
	private ButtonListener handler;
	private static final Color OUTLINE = Color.WHITE;
	private static final Color FILL = new Color(0xC5C5C5);
	private static final Color TEXT = Color.BLACK;
	private static final Color FOCUS_OUTLINE = new Color(0x30BACC);
	private static final Color FOCUS_FILL = new Color(0xD5D5D5);
	private static final Color FOCUS_TEXT = TEXT;
	private static final Color PRESS_OUTLINE = new Color(0x2695A3);
	private static final Color PRESS_FILL = new Color(0xADADAD);
	private static final Color PRESS_TEXT = TEXT;
	
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
	
	public void setListener(ButtonListener listener)
	{
		handler = listener;
	}
	
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
			if (state == State.PRESSED && (e.getModifiersEx() &
				MouseEvent.MOUSE_RELEASED) == 0)
			{
				handler.clicked(id);
			}
			state = State.FOCUSED;
			body.setFillColor(FOCUS_FILL);
			body.setOutlineColor(FOCUS_OUTLINE);
			label.setColor(FOCUS_TEXT);
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