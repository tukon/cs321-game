// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * The pop-up “window” shown at the end of a game. It displays the name of the
 * winner, the number of shots fired by each player, and their accuracy. It also
 * has a button to restart the game, and a button to go back to the main menu.
 * @author adam
 */
public class GameOverScreen implements ButtonListener, Drawable
{
	/** Background panel for the game over screen. */
	private Rectangle goPanel;
	
	/** Text at the top of the game over screen (“Player 1 Wins!”). */
	private TextLabel goTitle;
	
	/** Line under the title text. */
	private Line goLine;
	
	/** Text label for player 1’s stats. */
	private TextLabel goP1Stats;
	
	/** Text label for player 2’s stats. */
	private TextLabel goP2Stats;
	
	/** Button to restart the game. */
	private Button goRematchBtn;
	
	/** Button to go back to the title screen. */
	private Button goQuitBtn;
	
	/**
	 * Reference to the Archer object, for restarting or going back to
	 * the main menu.
	 */
	Archer controller;
	
	/** ID of the “rematch” button on the game over screen. */
	public static final int BTN_REMATCH_ID = 2;
	
	/**
	 * ID of the button on the Game Over screen that takes you to the title
	 * screen.
	 */
	public static final int BTN_TITLESCR_ID = 3;
	
	/** Width of the Game Over window. */
	private final int WIDTH = 350;
	/** Height of the Game Over window. */
	private final int HEIGHT = 300;
	
	/**
	 * Amount of space between the sides of the window and the items inside
	 * it.
	 */
	private final int PADDING = 10;
	
	/** Button width. */
	private final int BTN_WIDTH = 120;
	/** Button height. */
	private final int BTN_HEIGHT = 30;
	
	/**
	 * Creates a new Game Over screen.
	 * @param controller Used to restart or go to the title screen
	 */
	public GameOverScreen(Archer controller)
	{
		this.controller = controller;
		
		final int x1 = GamePanel.WIDTH/2 - WIDTH/2;
		final int y1 = GamePanel.HEIGHT/2 - HEIGHT/2;
		final int x2 = GamePanel.WIDTH/2 + WIDTH/2;
		final int y2 = GamePanel.HEIGHT/2 + HEIGHT/2;

		goPanel = new Rectangle(x1, y1, WIDTH, HEIGHT,
			Color.WHITE, Color.BLACK, true, true);
		
		goTitle = new TextLabel("",
			new Point(GamePanel.WIDTH/2, y1 + 40));
		goTitle.setColor(Color.WHITE);
		goTitle.setStyle(Font.BOLD);
		goTitle.setSize(24);
		goTitle.setCenter(true);
		
		goLine = new Line(new Point(x1+PADDING, y1+55),
			new Point(x2-PADDING, y1+55),
			Color.WHITE, 3);
		
		goP1Stats = new TextLabel("", 
			new Point(x1+PADDING, y1+100));
		goP1Stats.setColor(Color.WHITE);
		goP1Stats.setSize(16);
		goP1Stats.setLineSpacing(2);
		
		goP2Stats = new TextLabel("", 
			new Point(GamePanel.WIDTH/2+PADDING, y1+100));
		goP2Stats.setColor(Color.WHITE);
		goP2Stats.setSize(16);
		goP2Stats.setLineSpacing(2);
		
		goRematchBtn = new Button(BTN_REMATCH_ID,
			x1+PADDING, y2-BTN_HEIGHT-PADDING,
			BTN_WIDTH, BTN_HEIGHT,"Rematch");
		goRematchBtn.setListener(this);
		
		goQuitBtn = new Button(BTN_TITLESCR_ID,
			x2-BTN_WIDTH-PADDING,
			y2-BTN_HEIGHT-PADDING,
			BTN_WIDTH, BTN_HEIGHT, "To Title Screen");
		goQuitBtn.setListener(this);
	}
	
	/**
	 * Sets all of the text in the Game Over window.
	 * @param winner Name of the winning player
	 * @param p1Name Name of player 1
	 * @param p1Shots Number of shots fired by player 1
	 * @param p1Accuracy Player 1’s accuracy (double, between 0.0 & 1.0)
	 * @param p2Name Name of player 2
	 * @param p2Shots Number of shots fired by player 2
	 * @param p2Accuracy Player 2’s accuracy (double, between 0.0 & 1.0)
	 */
	public void setStats(String winner, String p1Name, int p1Shots,
		double p1Accuracy, String p2Name, int p2Shots,
		double p2Accuracy)
	{
		goTitle.setText(winner + " wins!");
		
		int p1A = (int)(p1Accuracy * 100.0);
		goP1Stats.setText(p1Name + ":\n    Shots Fired: " +
			Integer.toString(p1Shots) + "\n    Accuracy: " +
			Integer.toString(p1A) + "%");
		
		int p2A = (int)(p2Accuracy * 100.0);
		goP2Stats.setText(p2Name + ":\n    Shots Fired: " +
			Integer.toString(p2Shots) + "\n    Accuracy: " +
			Integer.toString(p2A) + "%");
	}
	
	/**
	 * Called when a Button is clicked.
	 * @param id ID of the button that was clicked.
	 */
	@Override
	public void clicked(int id)
	{
		switch (id)
		{
		case BTN_REMATCH_ID:
			controller.restart();
			break;
		case BTN_TITLESCR_ID:
			controller.setUpMenu();
			break;
		}
	}
	
	/**
	 * Updates the status of the buttons based on mouse movement.
	 * @param e Describes the mouse event
	 */
	public void mouseMoved(MouseEvent e)
	{
		goRematchBtn.update(e);
		goQuitBtn.update(e);
	}
	
	/**
	 * Reacts to a mouse button being pressed down.
	 * @param e Describes the mouse event
	 */
	public void mousePressed(MouseEvent e)
	{
		goRematchBtn.press(e);
		goQuitBtn.press(e);
	}
	
	/**
	 * Reacts to a mouse button being released.
	 * @param e Describes the mouse event
	 */
	public void mouseReleased(MouseEvent e)
	{
		goRematchBtn.release(e);
		goQuitBtn.release(e);
	}
	
	/**
	 * Draws the Game Over window on the game window.
	 * @param g Graphics device to draw with
	 */
	@Override
	public void draw(Graphics g)
	{
		goPanel.draw(g);
		goTitle.draw(g);
		goLine.draw(g);
		goP1Stats.draw(g);
		goP2Stats.draw(g);
		goRematchBtn.draw(g);
		goQuitBtn.draw(g);
	}
}
// EOF