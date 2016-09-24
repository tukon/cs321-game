// PROJECT: Test Game -- prototype for CS413 project

package edu.testgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static java.lang.Math.floor;
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
	
	private Player player1, player2;
	private ArrayList<Point> debugPoints;
	
	private Rectangle infoPanel;
	private TextLabel playerStats;
	
	private int power;
	
	private Player activePlayer, otherPlayer;
	
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
		frame.pack();  // Shrink to fit contents, i.e the panel.
		frame.setResizable(false);
		
		timer = new Timer(1000/60, this);
		timer.setRepeats(true);
		
		backdrop = new Sprite("backdrop.png", 0, 0);
		panel.add(backdrop);
		
		grass = new Sprite("grass.png", 0, 0);
		panel.add(grass);
		
		player1 = new Player(false, 64, GamePanel.HEIGHT-100);
		panel.add(player1);
		player2 = new Player(true, GamePanel.WIDTH-64,
			GamePanel.HEIGHT-100);
		panel.add(player2);
		
		infoPanel = new Rectangle(0, GamePanel.HEIGHT-75,
			GamePanel.WIDTH, 75, null, Color.BLACK, false, true);
		panel.add(infoPanel);
		
		playerStats = new TextLabel("", new Point(32,
			GamePanel.HEIGHT-50));
		playerStats.setColor(Color.WHITE);
		playerStats.setLineSpacing(1.5f);
		panel.add(playerStats);
		
		debugPoints = new ArrayList<>();
		panel.addDebugPoints(debugPoints);
		
		power = 50;
		
		activePlayer = player1;
		otherPlayer = player2;
	}
	
	/**
	 * Starts running the game. This function returns immediately; it does
	 * not block until the game exits.
	 */
	public void run()
	{
		frame.setVisible(true);
		timer.start();
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
		// Make the player’s character aim at the cursor
		Point pt = panel.getMousePosition();
		if (pt != null)  // null = not over the panel
		{
			activePlayer.aim(pt);
		}
		
		// Update the arrow’s position
		Arrow lastArrow = activePlayer.getLastArrow();
		if (lastArrow != null && lastArrow.isFlying())
		{
			lastArrow.update(1000/60);
			debugPoints.add(lastArrow.getTipPos());
			if (!lastArrow.isFlying())
			{
				// Arrow has hit the ground or left the screen--
				// prepare to fire again
				activePlayer.reload();
				
				if (activePlayer == player1)
				{
					activePlayer = player2;
					otherPlayer = player1;
				}
				else
				{
					activePlayer = player1;
					otherPlayer = player2;
				}
			}
			else  // See if the arrow hit the enemy
			{
				if (otherPlayer.intersects(lastArrow.getTipPos()))
				{
					otherPlayer.kill();
				}
			}
		}
		
		playerStats.setText("Player 1\n" +
			"    Power: " + Integer.toString(power) +
			"%\n    Angle: " + -(int)Math.toDegrees(
			player1.getAngle()) + "°");
		
		panel.repaint();  // Redraw the window contents
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
		
		// Clamp power between 0 and 50
		if (power < 0)  power = 0;
		if (power > 100) power = 100;
	}
	
	/**
	 * Fires the player’s bow when the mouse is clicked.
	 * @param e 
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Button3 is the right button
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			otherPlayer.revive();
			return;
		}
		if (activePlayer.canFire())
		{
			debugPoints.clear();
			int maxPower = 50;
			activePlayer.fire(((int)floor((((double)power/100.0)) *
				(double)maxPower)));
			panel.add(activePlayer.getLastArrow());
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