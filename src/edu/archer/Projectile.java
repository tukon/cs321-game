// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

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
	
	/** If true, this arrow will not be rendered. */
	private boolean hide;
	
	/** How many hitpoints a player will lose if they are hit by this. */
	private int damage;
	
	/** Velocity modifer. */
	private double velMod;
	
	/** Gravity modifier. */
	private double gravMod;
	
	/** Location of the tip, on the arrow. */
	private Point tipOffset;
	
	/** Previous tip position, used in hit detection */
	private Point prevTipPos;
	
	/** Reference to the settings menu. */
	private SettingsMenu settings;
	
	/** Conversion constant:  Characters are 32px, or 170cm, tall */
	final double PIXELS_PER_METER = 32/1.7;

	/** Gravity, in m/s, with modifiers. */
	double gravity;

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
		BATTERANG,
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
			velMod = 1.5;
			gravMod = 0.9;
			break;
		case ROCK:
			name += "rock";
			damage = 40;
			tipOffset = new Point(0, 0);
			velMod = 0.7;
			gravMod = 1.0;
			break;
		case LASER:
			name += "laser";
			damage = 1;
			tipOffset = new Point(20, 0);
			velMod = 2.0;
			gravMod = 0.0;
			break;
		case TRIDENT:
			name += "trident";
			damage = 4;
			tipOffset = new Point(41, 0);
			velMod = 0.8;
			gravMod = 0.9;
			break;
		case BATTERANG:
			name += "batterang";
			damage = 4;
			tipOffset = new Point(0, 0);
			velMod = 0.8;
			gravMod = 0.6;
			break;
		default:
			System.out.println("FIXME:unrecognized projectile type");
		}
		
		img = ResourceLoader.loadImage(name +
			(mirror ? "_enemy.png" : "_player.png"));
		imgTransformed = img;
		this.vel = vel * velMod;
		this.mirror = mirror;
		
		settings = SettingsMenu.GetSettings();

		gravity = settings.getGravity() * PIXELS_PER_METER * gravMod;
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
	
	/**
	 * Sets whether or not this projectile will be rendered, or hidden.
	 * @param hidden True to not render this projectile
	 */
	public void setHidden(boolean hidden)
	{
		hide = hidden;
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
	
	/**
	 * Controls whether or not the projectile is flying through the air,
	 * or stopped.
	 * @param flying Setting this to `false` will make the projectile stop 
	 *               moving.
	 */
	public void setFlying(boolean flying) { this.flying = flying; }
	
	/**
	 * Update the projectile’s position, velocity, and angle.
	 * @param Δt Elapsed time, in milliseconds, since the last update.
	 */
	public void update(int Δt)
	{
		if (!flying)  return;
		
		final int MILLISECONDS_PER_SECOND = 1000;
		velY += gravity * Δt/MILLISECONDS_PER_SECOND;
		
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
	 * @return The position of the projectile’s tip, in global coordinates.
	 */
	public Point getTipPos()
	{
		return new Point((int)(pos.x + tipOffset.x*Math.cos(ang) - 
			tipOffset.y*Math.sin(ang)),
			(int)(pos.y + tipOffset.x*Math.sin(ang) + 
			tipOffset.y*Math.cos(ang)));
	}
	
	/**
	 * Gets the position of the projectile’s tip from the previous frame.
	 * @return Previous position, in global coordinates.
	 */
	public Point getPrevTipPos() { return prevTipPos; }
	
	/**
	 * Draws the projectile on the game window. This is called by GamePanel.
	 * @param g Graphics device to draw with.
	 */
	@Override
	public void draw(Graphics g)
	{
		if (!hide)
		{
			g.drawImage(imgTransformed,
				pos.x - img.getWidth(null)/2,
				pos.y - img.getHeight(null)/2, null);
		}
	}
}
// EOF
