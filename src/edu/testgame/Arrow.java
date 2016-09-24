// PROJECT: Test Game -- prototype for CS413 project

package edu.testgame;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents an arrow, flying through the air…or stuck in the ground.
 * @author adam
 */
public class Arrow extends Sprite
{
	private boolean flying;
	private double velX, velY;
	private double vel;
	private boolean mirror;
	
	/**
	 * Creates a new arrow. Be sure to call setAngle() after this.
	 * @param mirror True if this is an enemy arrow; this affects its 
	 *              appearance.
	 * @param posX Horizontal position of the arrow.
	 * @param posY Vertical position of the arrow.
	 * @param vel Initial velocity of the angle, in pixels per second.
	 */
	public Arrow(boolean mirror, int posX, int posY, int vel)
	{
		super((mirror) ? "arrow_enemy.png" : "arrow_player.png", posX,
			posY);
		this.vel = vel;
		this.mirror = mirror;
	}
	
	/**
	 * Set the angle of the arrow, and make it start flying.
	 * @param ang Absolute angle, in radians.
	 */
	@Override
	public void setAngle(double ang)
	{
		super.setAngle(ang);
		double r = Math.toDegrees(ang);
		velX = Math.cos(ang) * vel;
		velY = Math.sin(ang) * vel;
		flying = true;	
	}
	
	/**
	 * Determines whether or not the arrow is still flying through the air.
	 * @return True if the arrow is flying; false if it has stuck somewhere.
	 */
	public boolean isFlying() { return flying; }
	
	/**
	 * Update the arrow’s position, velocity, and angle.
	 * @param Δt Elapsed time, in milliseconds, since the last update.
	 */
	public void update(int Δt)
	{
		if (!flying)  return;
		
		// Characters are 32px, or 1.7m, tall
		final double PIXELS_PER_METER = 32/1.7;
		final double GRAVITY = 9.81 * PIXELS_PER_METER;
		
		final int MILLISECONDS_PER_SECOND = 1000;
		
		velY += GRAVITY * Δt/MILLISECONDS_PER_SECOND;
		
		pos.x += velX;
		pos.y += velY;
		
		vel = Math.abs(Math.sqrt(velX*velX + velY*velY));
		
		ang = Math.atan(velY / velX);
		if (mirror)  super.setAngle(ang + Math.PI);
		else  super.setAngle(ang);
		
		// Find the position of the arrow’s tip
		int tipX = (int)(pos.x + 16*Math.cos(ang));
		int tipY = (int)(pos.y - 16*Math.sin(ang));
		// See if the arrow is stuck in the ground, or offscreen
		if (tipY > 500 || tipX < 0-32 || tipX > 800+32)
		{
			flying = false;
			int oldX = (int)(pos.x - velX);
			int oldY = (int)(pos.y - velY);
			
			double ii = (500 - oldY) / (pos.y - oldY);
			pos.x = (int)(oldX + (pos.x - oldX) * ii);
			pos.y = 500;
		}
	}
	
	/**
	 * Returns the position of the arrow’s tip. This can be used for
	 * collision detection.
	 * @return 
	 */
	public Point getTipPos()
	{
		return new Point((int)(pos.x + 16*Math.cos(ang)),
			(int)(pos.y + 16*Math.sin(ang)));
	}
	
	/**
	 * Draws the arrow on the game window. This is called by GamePanel.
	 * @param g 
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(imgTransformed, pos.x - img.getWidth(null)/2,
			pos.y - img.getHeight(null)/2, null);
	}
}
// EOF