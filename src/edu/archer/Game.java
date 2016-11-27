// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Handles all of the in-game logic: managing players, projectiles, etc.
 * @author adam
 */
public class Game implements Drawable
{
	/** The players’ avatars; P1 on the left, and P2 on the right. */
	private Player player1, player2;
	
	/** The platforms under the players. */
	private Sprite platform1, platform2;
	
	/** These line segments trace the path of the last arrow fired. */
	private ArrayList<Line> traceSegments;
	
	/** The panel at the bottom of the screen, showing health, etc. */
	private Rectangle infoPanel;
	
	/** Text labels showing the players’ names, aim angles, etc. */
	private TextLabel player1Stats, player2Stats;
	
	/** The line down the middle of the info panel. */
	private Line panelDivider;
	
	/** The top border of the info panel. */
	private Line panelTop;
	
	/** Reference to the player whose turn it is. */
	private Player activePlayer;
	
	/** Reference to the player whose turn it isn’t. */
	private Player otherPlayer;
	
	/** Projectile over the head of the active player. */
	private Polygon marker;
	
	/** Player 1’s health bar. */
	private Rectangle p1Health, p1HealthOutline;
	private TextLabel p1HealthLabel;
	
	/** Player 2’s health bar. */
	private Rectangle p2Health, p2HealthOutline;
	private TextLabel p2HealthLabel;
	
	/** Maximum power a shot can be fired at. */
	private int maxPower = 100;
	
	/** The game background images. */
	private Sprite backdrop;

	/** Whether or not practice mode is enabled. */
	private boolean practiceMode;
	
	/** Reference to the settings menu. */
	private SettingsMenu settings;
	
	/** How many shots player 1 has made. */
	private int p1Shots;
	
	/** How many times player 1 has hit the other player. */
	private int p1Hits;
	
	/** How many shots player 2 has made. */
	private int p2Shots;
	
	/** How many times player 2 has hit the other player. */
	private int p2Hits;
	
	/**
	 * After switching players, we simulate a mouse update with this. This
	 * makes the newly-selected player aim at the cursor properly.
	 */
	private static Point lastMousePoint;
	
	/** Default constructor. */
	public Game()
	{
		settings = SettingsMenu.GetSettings();
	}
	
	/**
	 * Hide the main menu items, initialize the game items, and start the
	 * game.
	 * @param p1Name  Player 1’s name
	 * @param p2Name  Player 2’s name
	 * @param p1Avatar Player 1’s character
	 * @param p2Avatar Player 2’s character
	 * @param weapon1 Player 1’s weapon
	 * @param weapon2 Player 2’s weapon
	 * @param practice True if this is practice mode
	 */
	public void setUpGame(String p1Name, String p2Name,
		int p1Avatar, int p2Avatar,
		Projectile.Type weapon1, Projectile.Type weapon2,
		boolean practice)
	{		
		// Load backdrop
		double MOON_GRAVITY = 1.64;
		if (settings.getGravity() == MOON_GRAVITY)
		{
                    backdrop = new Sprite(
                        "/backdrops/SecretMoonBackdrop.jpg", 0, 0);
                    player1 = new Player(false, 64, GamePanel.HEIGHT-150,
			p1Name, 9, weapon1);
                    platform1 = new Sprite("/platform.png", 0,GamePanel.HEIGHT-150);
		
                    player2 = new Player(true, GamePanel.WIDTH-64,
			GamePanel.HEIGHT-150, p2Name, 9, weapon2);
                    platform2 = new Sprite("/platform.png", GamePanel.WIDTH-64-64,
			GamePanel.HEIGHT-150);              
		}
                else if (p1Avatar == 8 && p2Avatar == 8)
                {
                    backdrop = new Sprite("/backdrops/8.jpg",
                        0, 0);
                        
                    player1 = new Player(false, 64, GamePanel.HEIGHT-150,
			p1Name, p1Avatar, weapon1);
                    platform1 = new Sprite("/platform.png", 0,GamePanel.HEIGHT-150);
		
                    player2 = new Player(true, GamePanel.WIDTH-64,
			GamePanel.HEIGHT-150, p2Name, p2Avatar, weapon2);
                    platform2 = new Sprite("/platform.png", GamePanel.WIDTH-64-64,
			GamePanel.HEIGHT-150);
                }
                else if ((p1Avatar == 4 && p2Avatar == 5) || (p1Avatar == 5 && p2Avatar == 4))
                {
                    backdrop = new Sprite("/backdrops/7.jpg",
                        0, 0);
                        
                    if (p1Avatar ==4)
                    {
                        player1 = new Player(false, 64, GamePanel.HEIGHT-150,
                            p1Name, p1Avatar, Projectile.Type.values()[4]);                        
                    }
                    else
                    {
                        player1 = new Player(false, 64, GamePanel.HEIGHT-150,
                            p1Name, p1Avatar, weapon1);
                    }
                    platform1 = new Sprite("/platform.png", 0,GamePanel.HEIGHT-150);
    
                    if (p2Avatar ==4)
                    {
                        player2 = new Player(true,GamePanel.WIDTH-64,
                            GamePanel.HEIGHT-150, p2Name, p2Avatar,
                            Projectile.Type.values()[4]);
                    }
                    else
                    {		
                        player2 = new Player(true, GamePanel.WIDTH-64,
                            GamePanel.HEIGHT-150, p2Name, p2Avatar, weapon2);
                    }
                    platform2 = new Sprite("/platform.png", GamePanel.WIDTH-64-64,
                        GamePanel.HEIGHT-150);
                    
                }
                else 
                {
                    backdrop = new Sprite(settings.getBackground(),
                        0, 0);
                        
                    if (p1Avatar ==4)
                    {
                        player1 = new Player(false, 64, GamePanel.HEIGHT-150,
                            p1Name, p1Avatar, Projectile.Type.values()[4]);                        
                    }
                    else
                    {
                        player1 = new Player(false, 64, GamePanel.HEIGHT-150,
                            p1Name, p1Avatar, weapon1);
                    }
                    platform1 = new Sprite("/platform.png", 0,GamePanel.HEIGHT-150);
    
                    if (p2Avatar ==4)
                    {
                        player2 = new Player(true,GamePanel.WIDTH-64,
                            GamePanel.HEIGHT-150, p2Name, p2Avatar,
                            Projectile.Type.values()[4]);                        
                    }
                    else
                    {		
                        player2 = new Player(true, GamePanel.WIDTH-64,
                            GamePanel.HEIGHT-150, p2Name, p2Avatar, weapon2);
                    }
                    platform2 = new Sprite("/platform.png", GamePanel.WIDTH-64-64,
                        GamePanel.HEIGHT-150);

                }
		
		// Create the bottom panel
		infoPanel = new Rectangle(0, GamePanel.HEIGHT-75,
			GamePanel.WIDTH, 75, null, Color.BLACK, false, true);
		panelDivider = new Line(
			new Point(GamePanel.WIDTH/2, GamePanel.HEIGHT - 65),
			new Point(GamePanel.WIDTH/2, GamePanel.HEIGHT - 10),
			Color.WHITE, 3);
		panelTop = new Line(
			new Point(0, GamePanel.HEIGHT - 75),
			new Point(GamePanel.WIDTH, GamePanel.HEIGHT - 75),
			Color.WHITE, 3);
		
		// Create player 1’s stats stuff
		player1Stats = new TextLabel("", new Point(32,
			GamePanel.HEIGHT-50));
		player1Stats.setColor(Color.WHITE);
		player1Stats.setLineSpacing(1.5f);
		
		p1Health = new Rectangle(200, GamePanel.HEIGHT-75/2-10, 200, 20,
			Color.WHITE, Color.RED, false, true);
		p1HealthOutline = new Rectangle(200, GamePanel.HEIGHT-75/2-10,
			200, 20, Color.WHITE, Color.BLACK, true, false);
		p1HealthLabel = new TextLabel("Health:", new Point(150,
			GamePanel.HEIGHT-75/2+5));
		p1HealthLabel.setColor(Color.WHITE);
		
		// …and player 2’s
		player2Stats = new TextLabel("",new Point(GamePanel.WIDTH/2+32,
			GamePanel.HEIGHT-50));
		player2Stats.setColor(Color.GRAY);
		player2Stats.setLineSpacing(1.5f);
		
		p2Health = new Rectangle(GamePanel.WIDTH/2+200,
			GamePanel.HEIGHT-75/2-10, 200, 20,
			Color.WHITE, Color.RED, false, true);
		p2HealthOutline = new Rectangle(GamePanel.WIDTH/2+200,
			GamePanel.HEIGHT-75/2-10,
			200, 20, Color.WHITE, Color.BLACK, true, false);
		p2HealthLabel = new TextLabel("Health:", new Point(
			GamePanel.WIDTH/2+150,
			GamePanel.HEIGHT-75/2+5));
		p2HealthLabel.setColor(Color.GRAY);
		
		// Create the current player marker
		marker = new Polygon(64, GamePanel.HEIGHT-150-75,
			new Color(0x30BACC), Color.CYAN, true, true);
		marker.addPoint(-10, -20);
		marker.addPoint(10, -20);
		marker.addPoint(0, 0);
		
		// Initialize trace points list
		traceSegments = new ArrayList<>();
		
		activePlayer = player1;
		otherPlayer = player2;
		
		movePlayer(player1, platform1);
		movePlayer(player2, platform2);
		
		marker.setPos(player1.getPos().x, player1.getPos().y-75);
		
		p1Shots = 0;
		p1Hits = 0;
		p2Shots = 0;
		p2Hits = 0;
		
		practiceMode = practice;
		
	}
	
	/** 
	 * Paint all game objects onto the canvas.
	 * @param g Graphics device to draw with
	 */
	@Override
	public void draw(Graphics g)
	{
		backdrop.draw(g);
		player1.draw(g);
		platform1.draw(g);
		player2.draw(g);
		platform2.draw(g);

		infoPanel.draw(g);
		panelDivider.draw(g);
		panelTop.draw(g);
		
		player1Stats.draw(g);		
		p1Health.draw(g);
		p1HealthOutline.draw(g);
		p1HealthLabel.draw(g);
		
		player2Stats.draw(g);
		p2Health.draw(g);
		p2HealthOutline.draw(g);
		p2HealthLabel.draw(g);
		
		marker.draw(g);
		
		if (settings.getTraceShotEnabled())
		{
			traceSegments.forEach(line -> line.draw(g));
		}
	}
	
	/**
	 * Updates the position of the most recently fired arrow, and the text
	 * at the bottom of the screen.
	 */
	public boolean update()
	{		
		boolean status = true;
		// Update the arrow’s position
		Projectile lastArrow = activePlayer.getLastArrow();
		if (lastArrow != null && lastArrow.isFlying())
		{
			status = updateArrow(lastArrow);
		}
		
		player1Stats.setText(player1.getStats());
		player2Stats.setText(player2.getStats());

		return status;
	}
	
	/**
	 * Makes the current player aim at the given point.
	 * @param p Point to aim at
	 */
	public void aim(Point p)
	{
		activePlayer.aim(p);
		lastMousePoint = p;
	}
	
	/** Updates the arrow’s position and checks if it hit the other player*/
	private boolean updateArrow(Projectile lastArrow)
	{
		lastArrow.update(1000/60);
		if (settings.getTraceShotEnabled())
		{
			traceSegments.add(new Line(traceSegments.get(
				traceSegments.size()-1).getEndPos(),
				lastArrow.getTipPos(), Color.MAGENTA, 1));
		}
		
		if (!lastArrow.isFlying())
		{
			// Projectile has hit the ground or left the screen--
			// prepare to fire again
			activePlayer.reload();
			swapPlayers();
		}
		else  // See if the arrow hit the enemy
		{
			if (otherPlayer.hitCheck(lastArrow))   // hit
			{
				lastArrow.setFlying(false);
				lastArrow.setHidden(true);
				
				if (activePlayer == player1)  ++p1Hits;
				else ++p2Hits;
				
				if (otherPlayer.getHealth() > 0)  // kill
				{
					activePlayer.reload();
					
					Sprite platform;
					if (activePlayer == player1)
					{
						platform = platform2;
					}
					else
					{
						platform = platform1;
					}
					movePlayer(otherPlayer, platform);

					swapPlayers();
				}
				else  // still alive
				{
					return false;
				}
			}
			
			// Update health bar sizes:
			p1Health.setWidth((int)(200.0 * player1.getHealth() /
				player1.getMaxHealth()));
			p2Health.setWidth((int)(200.0 * player2.getHealth() /
				player2.getMaxHealth()));
		}
		return true;
	}
	
	/**
	 * Moves the given player up/down randomly.
	 * @param player The player to move
	 * @param platform The platform to move
	 */
	private void movePlayer(Player player, Sprite platform)
	{
		final int MIN_Y = 100;
		final int MAX_Y = GamePanel.HEIGHT - 150;
		final int RANGE = MAX_Y - MIN_Y;
		
		int newY = (int)((Math.random()) * RANGE + MIN_Y);
		
		platform.setPos(platform.getPos().x, newY);
		player.setPos(player.getPos().x, newY);		
	}
	
	/** Changes the active player from P1 to P2, or vise versa. */
	private void swapPlayers()
	{
		//if it is not practice mode than alteranate
		if (practiceMode)  return;
		
		if (activePlayer == player1)
		{
			activePlayer = player2;
			otherPlayer = player1;
			player2Stats.setColor(Color.WHITE);
			p2HealthLabel.setColor(Color.WHITE);
			player1Stats.setColor(Color.GRAY);
			p1HealthLabel.setColor(Color.GRAY);
			marker.setPos(GamePanel.WIDTH-64,
				marker.getPos().y);
		}
		else
		{
			activePlayer = player1;
			otherPlayer = player2;
			player2Stats.setColor(Color.GRAY);
			p2HealthLabel.setColor(Color.GRAY);
			player1Stats.setColor(Color.WHITE);
			p1HealthLabel.setColor(Color.WHITE);
			marker.setPos(64,
				marker.getPos().y);
		}
		marker.setPos(activePlayer.getPos().x,
			activePlayer.getPos().y - 75);
		activePlayer.reload();
		
		aim(lastMousePoint);
	}
	
	/**
	 * Adjusts shot power
	 * @param amount How many clicks the wheel was turned
	 */
	public void mouseWheelTurned(int amount)
	{
		activePlayer.changePower(-amount);
	}
	
	/** Fires arrows and stuff */
	public void mouseClicked()
	{
		if (activePlayer == null)  return;
		
		// Fire the player’s bow, if possible
		if (activePlayer.canFire())
		{
			// Comment this out to prevent the traces from
			// disappearing:
			traceSegments.clear();
			
			traceSegments.add(new Line(activePlayer.getAimOrigin(),
				activePlayer.getAimOrigin(), Color.MAGENTA, 1));
			
			activePlayer.fire(maxPower);
			
			if (activePlayer == player1)  ++p1Shots;
			else  ++p2Shots;
		}
	}
	
	/**
	 * Returns a reference to the active player.
	 * @return The active player
	 */
	public Player getActivePlayer() { return activePlayer; }
	
	/**
	 * Returns a reference to player 1.
	 * @return Player 1
	 */
	public Player getPlayer1() { return player1; }
	
	/**
	 * Returns a reference to player 2.
	 * @return Player 2
	 */
	public Player getPlayer2() { return player2; }
	
	/**
	 * Gets the number of shots fired by player 1.
	 * @return Number of shots
	 */
	public int getP1Shots() { return p1Shots; }
	
	/**
	 * Gets the number of shots fired by player 2.
	 * @return Number of shots
	 */
	public int getP2Shots() { return p2Shots; }
	
	/**
	 * Gets player 1’s accuracy.
	 * @return A double between 0.0 (all misses) and 1.0 (all hits).
	 */
	public double getP1Accuracy() { return (double)p1Hits / p1Shots; }
	
	/**
	 * Gets player 2’s accuracy.
	 * @return A double between 0.0 (all misses) and 1.0 (all hits).
	 */
	public double getP2Accuracy() { return (double)p2Hits / p1Shots; }
	
	/**
	 * Gets whether or not the game is in practice mode.
	 * @return True for practice mode, false for duel.
	 */
	public boolean isPractice() { return practiceMode; }
	
}
// EOF
