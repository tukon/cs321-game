// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author adam
 */
public class GameOverScreen implements Drawable
{
	/** Background panel for the game over screen */
	private Rectangle goPanel;
	
	/** Text at the top of the game over screen, e.g. “Player 1 Wins!” */
	private TextLabel goTitle;
	
	/** Line under the title text */
	private Line goLine;
	
	/** Text label for player 1’s stats */
	private TextLabel goP1Stats;
	
	/** Text label for player 2’s stats */
	private TextLabel goP2Stats;
	
	/** Button to restart the game */
	private Button goRematchBtn;
	
	/** Button to go back to the title screen */
	private Button goQuitBtn;
	
	private final int WIDTH = 350;
	private final int HEIGHT = 300;
	private final int PADDING = 10;
	
	private final int BTN_WIDTH = 120;
	private final int BTN_HEIGHT = 30;
	
	public GameOverScreen(String winner, String p1Name, int p1Shots,
		double p1Accuracy, String p2Name, int p2Shots,
		double p2Accuracy, ButtonListener clickHandler)
	{
		final int x1 = GamePanel.WIDTH/2 - WIDTH/2;
		final int y1 = GamePanel.HEIGHT/2 - HEIGHT/2;
		final int x2 = GamePanel.WIDTH/2 + WIDTH/2;
		final int y2 = GamePanel.HEIGHT/2 + HEIGHT/2;

		goPanel = new Rectangle(x1, y1, WIDTH, HEIGHT,
			Color.WHITE, Color.BLACK, true, true);
		
		goTitle = new TextLabel(winner + " Wins!",
			new Point(GamePanel.WIDTH/2, y1 + 40));
		goTitle.setColor(Color.WHITE);
		goTitle.setStyle(Font.BOLD);
		goTitle.setSize(24);
		goTitle.setCenter(true);
		
		goLine = new Line(new Point(x1+PADDING, y1+55),
			new Point(x2-PADDING, y1+55),
			Color.WHITE, 3);
		
		int p1A = (int)(p1Accuracy * 100.0);
		goP1Stats = new TextLabel(p1Name + ":\n    Shots Fired: " +
			Integer.toString(p1Shots) + "\n    Accuracy: " +
			Integer.toString(p1A) + "%", 
			new Point(x1+PADDING, y1+100));
		goP1Stats.setColor(Color.WHITE);
		goP1Stats.setSize(16);
		goP1Stats.setLineSpacing(2);
		
		int p2A = (int)(p2Accuracy * 100.0);
		goP2Stats = new TextLabel(p2Name + ":\n    Shots Fired: " +
			Integer.toString(p2Shots) + "\n    Accuracy: " +
			Integer.toString(p2A) + "%", 
			new Point(GamePanel.WIDTH/2+PADDING, y1+100));
		goP2Stats.setColor(Color.WHITE);
		goP2Stats.setSize(16);
		goP2Stats.setLineSpacing(2);
		
		goRematchBtn = new Button(TestGame.BTN_REMATCH_ID,
			x1+PADDING, y2-BTN_HEIGHT-PADDING,
			BTN_WIDTH, BTN_HEIGHT,"Rematch");
		goRematchBtn.setListener(clickHandler);
		
		goQuitBtn = new Button(TestGame.BTN_TITLESCR_ID,
			x2-BTN_WIDTH-PADDING,
			y2-BTN_HEIGHT-PADDING,
			BTN_WIDTH, BTN_HEIGHT, "To Title Screen");
		goQuitBtn.setListener(clickHandler);
	}
	
	public void updateButtons(MouseEvent e)
	{
		goRematchBtn.update(e);
		goQuitBtn.update(e);
	}
	
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