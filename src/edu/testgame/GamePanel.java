/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * The “canvas” that displays all of the game objects. Objects must be added to
 * it with `add()`.
 * @author adam
 */
public class GamePanel extends JPanel
{
	/** Array of objects to display */
	private ArrayList<Drawable> mSprites;
	
	/**
	 * Text shown in the upper left corner. TODO: rename this
	 */
	private String debugText;
	
	public GamePanel()
	{
		super();
		mSprites = new ArrayList<>();
		// TODO: create constants for the size
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
	}
	
	/**
	 * Add a new object to be displayed.
	 * @param sprite The object to add.
	 */
	public void add(Drawable sprite)
	{
		mSprites.add(sprite);
	}
	
	/**
	 * Remove an object, so it will not be drawn.
	 * @param sprite The object to remove.
	 */
	public void remove(Drawable sprite)
	{
		mSprites.remove(sprite);
	}
	
	/**
	 * Set the text to be displayed in the upper left corner.
	 * 
	 * TODO: rename this
	 * @param text 
	 */
	public void setDebugText(String text)
	{
		debugText = text;
	}
	
	/**
	 * Draws all game objects. Should not be called directly -- use
	 * `repaint()` instead.
	 * @param g The graphics object to draw with.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		mSprites.forEach(sprite -> sprite.draw(g));
		
		if (debugText != null)  g.drawString(debugText, 16, 16);
	}
}
// EOF