// PROJECT: Test Game -- prototype for CS413 project

package edu.testgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Represents the player, or their opponent. It does not handle arrows, though.
 * 
 * The player’ body is `img`, inherited from Sprite. `ang` only affects the
 * player’s arms.
 * @author adam
 */
public class Player extends Sprite
{
	/**
	 * The image representing the player’s arms and bow. This is rotated by
	 * `ang`, which is inherited from Sprite.
	 */
	BufferedImage arms;
	
	/**
	 * If `true`, the character will be mirrored (this is how the enemy is
	 * displayed).
	 */
	boolean flip;
	
	/** Current state of the player (aiming, firing, or dead). */
	State state;
	
	/** Cached image. */
	BufferedImage body, bodyDead, armsReady, armsRelaxed;
	
	/**
	 * Creates a new Player object. The anchor point is the center of the
	 * bottom of the player.
	 * @param flip Whether or not to mirror the player’s body.
	 * @param x Horizontal position
	 * @param y Vertical position
	 */
	public Player(boolean flip, int x, int y)
	{
		super("player.png", x, y);
		this.flip = flip;
		ang = (flip) ? Math.PI : 0.0;
		
		body = img;
		bodyDead = loadImg("player_dead.png");
		armsReady = loadImg("bow_drawn.png");
		armsRelaxed = loadImg("bow_fired.png");
		
		if (flip)
		{
			body = flipImg(body);
			bodyDead = flipImg(bodyDead);
			armsReady = flipImg(armsReady);
			armsRelaxed = flipImg(armsRelaxed);
			img = body;
		}
		
		state = State.AIMING;
		arms = armsReady;
		imgTransformed = arms;
	}
	
	/**
	 * Mirrors the given image. TODO: move to Sprite?
	 * @param image The image to mirror.
	 * @return The mirroed image.
	 */
	private BufferedImage flipImg(BufferedImage image)
	{
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, 
			AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}
	
	/**
	 * Aim at the given point.
	 * @param aimPos the point to aim at.
	 */
	public void aim(Point aimPos)
	{
		final int ARMS_POS_X = pos.x;
		final int ARMS_POS_Y = pos.y - img.getHeight(null)/2;
		
		// If the cursor is horizontally aligned with the player, set
		// the angle to either the minimum or maximum (depending on the
		// vertical position) to avoid a divide-by-zero error.
		if (aimPos.x == ARMS_POS_X)
		{
			setAngle((aimPos.y < ARMS_POS_Y) ? Math.PI/2 :
				-Math.PI/2);
		}
		else
		{
			double a = Math.atan((double)(aimPos.y - ARMS_POS_Y) /
				(double)(aimPos.x - ARMS_POS_X));
			if (flip)  a = Math.PI - a;
			setAngle(a);
		}
	}
	
	/**
	 * Sets the player’s aim angle.
	 * @param angle Absolute aim angle, in radians. Will be clamped
	 *              automatically.
	 */
	@Override
	public void setAngle(double angle)
	{
		if (flip)
		{
			// Compensate for the fact that the image is mirrored
			angle -= Math.PI;
			angle *= -1;
		}
		
		// Clamp angle
		double MIN_ANG = -Math.PI/3;
		double MAX_ANG = Math.PI/3;
		if (angle < MIN_ANG)  ang = MIN_ANG;
		else if (angle > MAX_ANG) ang = MAX_ANG;
		else  ang = angle;
		
		// Transform the arms image & store the result in imgTransformed
		double centerX = arms.getWidth(null) / 2;
		double centerY = arms.getHeight(null) / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(ang,
			centerX, centerY);
		AffineTransformOp op = new AffineTransformOp(tx, 
			AffineTransformOp.TYPE_BILINEAR);
		
		imgTransformed = op.filter(arms, null);
	}
	
	/**
	 * Returns the angle that the player is holding their bow at.
	 * @return Aim angle, in radians.
	 */
	public double getAngle() { return ang; }
	
	/** 
	 * See if the player can fire, i.e. if they are alive and their bow is
	 * loaded.
	 * @return `true` if the player can fire, `false` if they cannot.
	 */
	public boolean canFire() { return (state == State.AIMING); }
	
	/** 
	 * Gets the player’s current state.
	 * @return Player’s state
	 * @see State
	 */
	public State getState() { return state; }
	
	/**
	 * Determines whether or not the given point intersects the player’s 
	 * body.
	 * @param p The point to check.
	 * @return `true` if the given point intersects the bounding box of the 
	 *         player’s body.
	 */
	public boolean intersects(Point p)
	{
		// Find the sides of the player’s bounding box.
		int minX = pos.x - img.getWidth(null) / 2;
		int maxX = pos.x + img.getWidth(null) / 2;
		int minY = pos.y - img.getHeight(null);
		int maxY = pos.y;
		
		return (p.x > minX && p.x < maxX && p.y > minY && p.y < maxY);
	}
	
	/**
	 * Returns the rotation point of the player’s arms/bow. This is used in
	 * aiming calculations, and is the starting point of arrows.
	 * @return Aim origin point
	 */
	public Point getAimOrigin()
	{
		return new Point(pos.x, pos.y - img.getHeight(null)/2);
	}
	
	/** Changes the player’s state to `FIRING`, if they are not dead. */
	public void fire()
	{
		if (state != State.DEAD)
		{
			state = State.FIRING;
			arms = armsRelaxed;
		}
	}
	
	/** Changes the player’s state to `AIMING`, if they are not dead. */
	public void reload()
	{
		if (state != State.DEAD)
		{
			state = State.AIMING;
			arms = armsReady;
		}
	}
	
	/** Changes the player’s state to `DEAD`. */
	public void kill()
	{
		state = State.DEAD;
		img = bodyDead;
	}
	
	/**
	 * Draws the player on the game window. This is called by GamePanel.
	 * @param g 
	 */
	@Override
	public void draw(Graphics g)
	{
		// Determine the coordinates to draw the arms at.
		final int ARMS_POS_X = pos.x - arms.getWidth(null)/2;
		final int ARMS_POS_Y = pos.y - img.getHeight(null)/2 - 
			arms.getHeight(null)/2;
		
		g.drawImage(img, pos.x - img.getWidth(null)/2,
			pos.y - img.getHeight(null), null);
		
		// Do not draw the arms if the player is dead.
		if (state != State.DEAD)
			g.drawImage(imgTransformed, ARMS_POS_X, ARMS_POS_Y, 
				null);
	}
	
	/** States the player can be in. */
	public enum State { AIMING, FIRING, DEAD };
}
// EOF