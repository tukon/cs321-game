/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testgame;

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
