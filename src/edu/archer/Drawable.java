// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Graphics;

/**
 * Represents an object that can be drawn by GamePanel. TODO: merge with Sprite?
 * @author adam
 */
public interface Drawable
{
	/**
	 * Draw the object on the screen. This is called by GamePanel when
	 * refreshing the game window.
	 * @param g Graphics device to draw with
	 */
	public void draw(Graphics g);
}
// EOF