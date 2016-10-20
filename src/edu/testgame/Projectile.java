// PROJECT: Test Game -- prototype for CS 321 project

package edu.testgame;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Represents an projectile, flying through the air…or stuck in the ground.
 * @author adam
 */
public class Projectile extends Sprite
{	
	/**
	 * `true` when the projectile is soaring through the air; `false` when
	 * it is stuck in the ground.
	 */
	private boolean flying;
	
	/** Velocity components. */
	private double velX, velY;
	
	/** Velocity mangnitude. */
	private double vel;
	
	/** `true` to flip the projectile horizontally. */
	private boolean mirror;
	
	/** How many hitpoints a player will lose if they are hit by this. */
	private int damage;
	
	/** Location of the tip, on the arrow. */
	private Point tipOffset;
	
	/** Previous tip position, used in hit detection */
	private Point prevTipPos;
	
	/** 
	 * List of types of projectiles. Properties are defined in the
	 * constructor.
	 */
	public enum Type
	{
		ARROW,
		ROCK,
		LASER,
		TRIDENT,
	}
	
	/**
	 * Creates a new projectile. Be sure to call setAngle() after this.
	 * @param type Type of projectile
	 * @param mirror True if this is an enemy projectile; this affects its 
	 *               appearance.
	 * @param posX Horizontal position of the projectile.
	 * @param posY Vertical position of the projectile.
	 * @param vel Initial velocity of the angle, in pixels per second.
	 */
	public Projectile(Type type, boolean mirror, int posX, int posY,
		int vel)
	{
		super(null, posX, posY);
		String name = "/projectiles/";
		switch (type)
		{
		case ARROW:
			name += "arrow";
			damage = 3;
			tipOffset = new Point(13, 0);
			break;
		case ROCK:
			name += "rock";
			damage = 40;
			tipOffset = new Point(0, 0);
			break;
		case LASER:
			name += "laser";
			damage = 5;
			tipOffset = new Point(20, 0);
			break;
		case TRIDENT:
			name += "trident";
			damage = 4;
			tipOffset = new Point(41, 0);
			break;
		default:
			System.out.println("FIXME:unrecognized projectile type");
		}
		
		img = ResourceLoader.loadImage(name +
			(mirror ? "_enemy.png" : "_player.png"));
		imgTransformed = img;
		this.vel = vel;
		this.mirror = mirror;
	}
	
	/**
	 * Set the angle of the projectile, and make it start flying.
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
	
	/** Gets how much damage this projectile does.
	 * @return How many hitpoints a player will lose if they are hit by this
	 *         projectile.
	 */
	public int getDamage() { return damage; }
	
	/**
	 * Determines whether or not the projectile is still flying through the
	 * air.
	 * @return True if the projectile is flying; false if it has stuck
	 *         somewhere.
	 */
	public boolean isFlying() { return flying; }
	
	/** Setting this to `false` will make the projectile stop moving. */
	public void setFlying(boolean flying) { this.flying = flying; }
	
	/**
	 * Update the projectile’s position, velocity, and angle.
	 * @param Δt Elapsed time, in milliseconds, since the last update.
	 */
	public void update(int Δt)
	{
		if (!flying)  return;
		
		// Characters are 32px, or 1.7m, tall
		final double PIXELS_PER_METER = 32/1.7;
		final double GRAVITY = SettingsMenu.getGravity() * 
			PIXELS_PER_METER;
		final int MILLISECONDS_PER_SECOND = 1000;
		velY += GRAVITY * Δt/MILLISECONDS_PER_SECOND;
		
		int oldX = pos.x;
		int oldY = pos.y;
		pos.x += velX;
		pos.y += velY;
		
		vel = Math.abs(Math.sqrt(velX*velX + velY*velY));
		
		double oldAng = ang;
		ang = Math.atan(velY / velX);
		if (mirror)  super.setAngle(ang + Math.PI);
		else  super.setAngle(ang);
		
		// Find the position of the projectile’s tip
		int tipX = (int)(pos.x + tipOffset.x*Math.cos(ang) - 
			tipOffset.y*Math.sin(ang));
		int tipY = (int)(pos.y + tipOffset.x*Math.sin(ang) + 
			tipOffset.y*Math.cos(ang));
		// See if the projectile is stuck in the ground, or offscreen
		if (tipX < 0-32 || tipX > GamePanel.WIDTH+32)
		{
			flying = false;
		}
		else if (tipY > GamePanel.HEIGHT-100)
		{
			flying = false;
			
			double ii = (GamePanel.HEIGHT-100.0 - oldY) /
				(pos.y - oldY);
			pos.x = (int)(oldX + (pos.x - oldX) * ii);
			pos.y = GamePanel.HEIGHT-100;
		}
		prevTipPos = new Point((int)(oldX + 16*Math.cos(oldAng)),
			(int)(oldY + 16*Math.sin(oldAng)));
	}
	
	/**
	 * Returns the position of the projectile’s tip. This can be used for
	 * collision detection.
	 * @return 
	 */
	public Point getTipPos()
	{
		return new Point((int)(pos.x + tipOffset.x*Math.cos(ang) - 
			tipOffset.y*Math.sin(ang)),
			(int)(pos.y + tipOffset.x*Math.sin(ang) + 
			tipOffset.y*Math.cos(ang)));
	}
	
	public Point getPrevTipPos() { return prevTipPos; }
	
	/**
	 * Draws the projectile on the game window. This is called by GamePanel.
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