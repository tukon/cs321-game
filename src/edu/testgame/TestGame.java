/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testgame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Main game class
 * @author adam
 */
public class TestGame implements ActionListener, MouseListener, 
	MouseWheelListener
{

	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	
	private Sprite backdrop;
	private Sprite grass;
	
	private Player player, enemy;
	private ArrayList<Arrow> playerArrows, enemyArrows;
	
	private int power;
	
	/**
	 * The program’s main entry point.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args)
	{
		TestGame game = new TestGame(args);
		game.run();
	}
	
	/**
	 * The constructor, where game objects are initialized.
	 * @param args The command line arguments.
	 */
	public TestGame(String[] args)
	{
		frame = new JFrame("Test Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new GamePanel();
		panel.setOpaque(false);
		panel.addMouseListener(this);
		panel.addMouseWheelListener(this);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		
		timer = new Timer(1000/60, this);
		timer.setRepeats(true);
		
		backdrop = new Sprite("backdrop.png", 0, 0);
		panel.add(backdrop);
		
		grass = new Sprite("grass.png", 0, 0);
		panel.add(grass);
		
		player = new Player(false, 64, 500);
		panel.add(player);
		enemy = new Player(true, 800-64, 500);
		panel.add(enemy);
		
		playerArrows = new ArrayList<>();
		enemyArrows = new ArrayList<>();
		
		power = 50;
	}
	
	/**
	 * Starts running the game. This function returns immediately; it does
	 * not block until the game exits.
	 */
	public void run()
	{
		frame.setVisible(true);
		timer.start();
		return;
	}
	
	/**
	 * The main update method; called 60 times per second by a timer. This
	 * is what refreshes the window and updates the positions of the arrows.
	 * 
	 * TODO: should mouse movement be handled by a separate function, like
	 *       clicking?
	 * @param e Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Point pt = panel.getMousePosition();
		if (pt != null)
		{
			player.aim(pt);
			enemy.aim(pt);
		}
		
		Arrow lastArrow = null;
		if (playerArrows.size() > 0)
		{
			lastArrow = playerArrows.get(
			playerArrows.size() - 1);
		}
		if (lastArrow != null && lastArrow.isFlying())
		{
			lastArrow.update(1000/60);
			if (!lastArrow.isFlying())
			{
				player.reload();
			}
			else
			{
				if (enemy.intersects(lastArrow.getTipPos()))
				{
					enemy.kill();
				}
			}
		}
		
		panel.setDebugText("Power: " + Integer.toString(power) +
			"  Angle: " + -(int)Math.toDegrees(player.getAngle()));
		
		panel.repaint();
	}
	
	/**
	 * Changes the power of the shot when the scroll wheel is turned.
	 * @param e Indicates how many clicks the scroll wheel was turned, and
	 *          in what direction.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		power -= e.getWheelRotation();
		
		if (power < 0)  power = 0;
		if (power > 50) power = 50;
	}
	
	/**
	 * Fires the player’s bow when the mouse is clicked.
	 * @param e 
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (player.canFire())
		{
			player.fire();
			Point p = player.getAimOrigin();
			playerArrows.add(new Arrow(false, p.x, p.y,
				player.getAngle(), power));
			panel.add(playerArrows.get(playerArrows.size()-1));
		}
	}
	
	/**
	 * Required, but unused
	 * @param e 
	 */
	@Override
	public void mousePressed(MouseEvent e) { }
	
	/**
	 * Required, but unused
	 * @param e 
	 */
	@Override
	public void mouseReleased(MouseEvent e) { }
	
	/**
	 * Required, but unused
	 * @param e 
	 */
	@Override
	public void mouseEntered(MouseEvent e) { }
	
	/**
	 * Required, but unused
	 * @param e 
	 */
	@Override
	public void mouseExited(MouseEvent e) { }
	
}
// EOF