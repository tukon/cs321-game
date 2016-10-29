// PROJECT: Test Game -- prototype for CS 321 project

package edu.testgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Main game class
 * @author adam
 */
public class TestGame implements ActionListener, MouseListener, 
	MouseWheelListener, MouseMotionListener, ButtonListener, KeyListener
{
	private enum GameState { INIT, TITLE_SCREEN, PLAYING, GAME_OVER };
	/**
	 * -1 during initalization, 0 on the tile screen, 2 when the game is 
	 * running.
	 * 
	 * It is an `int` to work arount a logic bug in the button/mouse
	 * listeners. The button listener sets it to 1, then the mouse listener
	 * sets it to 2 and returns -- without firing an arrow.
	 */
	private GameState state;
	
	/** The game window. */
	private JFrame frame;
	
	/** The canvas the game is drawn on. */
	private GamePanel panel;
	
	/** The primary timer that updates the arrows and repaints the canvas.*/
	private Timer timer;
	
	/** The game background images. */
	private Sprite backdrop;
	private Sprite grass;
	
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
	
	private int maxPower = 100;
	
	/** Background image of the main menu. */
	private Sprite menuBackdrop;
	
	/** Button to start the game. */
	private Button startBtn;
	
	/** Button to change settings. */
	private Button settingsBtn;
	
	/** The “Game Over” screen. */
	private GameOverScreen gameOver;
	
	private int p1Shots;
	private int p1Hits;
	
	private int p2Shots;
	private int p2Hits;
	
	/** ID of the “start game” button, used by the click handler. */
	public static final int BTN_START_ID = 0;
	
	/** ID of the “settings” button, used by the click handler. */
	public static final int BTN_SETTINGS_ID = 1;
	
	public static final int BTN_REMATCH_ID = 2;
	
	public static final int BTN_TITLESCR_ID = 3;
	
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
		state = GameState.INIT;
		frame = new JFrame("Archer Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
		panel.setOpaque(false);
		panel.addMouseWheelListener(this);
		panel.addMouseMotionListener(this);
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.setFocusTraversalKeysEnabled(false);
		frame.add(panel);
		frame.pack();  // Shrink to fit contents, i.e the panel.
		frame.setResizable(false);
		
		// Load and set frame icon
		ArrayList<BufferedImage> icons = new ArrayList<>();
		String name = "";
		// Load each size: 16, 32, 64, 128, 256
		// 1 << ii is 2^ii; 16 is 2^4; 256 is 2^8
		for (int ii = 4; ii <= 8; ++ii)
		{
			name = "/icons/" + Integer.toString(1 << ii)+ 
				".png";
			icons.add(ResourceLoader.loadImage(name));
		}
		if (!icons.isEmpty())  frame.setIconImages(icons);
		
		timer = new Timer(1000/60, this);
		timer.setRepeats(true);
	}
	
	/** Initialize and show the main menu screen. */
	private void setUpMenu()
	{
		menuBackdrop = new Sprite("/backdrops/title.png", 0, 0);
		panel.add(menuBackdrop);
		startBtn = new Button(BTN_START_ID,
			650, 270,
			200, 40, "Start");
		startBtn.setListener(this);
		panel.add(startBtn);
		settingsBtn = new Button(BTN_SETTINGS_ID,
			650, 270+40+10,
			200, 40, "Settings…");
		settingsBtn.setListener(this);
		panel.add(settingsBtn);
		
		state = GameState.TITLE_SCREEN;
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
	 */
	public void setUpGame(String p1Name, String p2Name,
		int p1Avatar, int p2Avatar,
		Projectile.Type weapon1, Projectile.Type weapon2)
	{
		panel.remove(settingsBtn);
		panel.remove(startBtn);
		panel.remove(menuBackdrop);
		panel.remove(gameOver);
		
		// Load backdrop
		backdrop = new Sprite(SettingsMenu.getBackground(), 0, 0);
		panel.add(backdrop);
		
		// Load players and their platforms
		player1 = new Player(false, 64, GamePanel.HEIGHT-150,
			p1Name, p1Avatar, weapon1);
		platform1 = new Sprite("/platform.png", 0, GamePanel.HEIGHT-150);
		
		player2 = new Player(true, GamePanel.WIDTH-64,
			GamePanel.HEIGHT-150, p2Name, p2Avatar, weapon2);
		platform2 = new Sprite("/platform.png", GamePanel.WIDTH-64-64,
			GamePanel.HEIGHT-150);
		
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
		
		// Add everything to the panel:
		panel.add(player1);
		panel.add(platform1);
		panel.add(player2);
		panel.add(platform2);

		panel.add(infoPanel);
		panel.add(panelDivider);
		panel.add(panelTop);
		
		panel.add(player1Stats);		
		panel.add(p1Health);
		panel.add(p1HealthOutline);
		panel.add(p1HealthLabel);
		
		panel.add(player2Stats);
		panel.add(p2Health);
		panel.add(p2HealthOutline);
		panel.add(p2HealthLabel);
		
		panel.add(marker);
		
		activePlayer = player1;
		otherPlayer = player2;
		
		// Delete the titlescreen buttons
		startBtn = null;
		settingsBtn = null;
		
		state = GameState.PLAYING;
	}
	
	public void showGameOverScreen()
	{
		gameOver = new GameOverScreen(activePlayer.getName(),
			player1.getName(), p1Shots, (double)p1Hits/p1Shots,
			player2.getName(), p2Shots, (double)p2Hits/p2Shots, this);
		panel.add(gameOver);
		state = GameState.GAME_OVER;
	}
	
	/**
	 * Starts the game at the main menu. This function returns immediately;
	 * it does not block until the game exits.
	 */
	public void run()
	{
                SettingsMenu.GetInitialSettings();
		frame.setVisible(true);
		timer.start();
		setUpMenu();
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
		if (state == GameState.INIT)  return;  // Nothing to do yet
		if (state != GameState.PLAYING)
		{
			// Just redraw the screen
			panel.repaint();
			return;
		}
		
		// Make the player’s character aim at the cursor
		Point pt = panel.getMousePosition();
		if (pt != null)  // null = not over the panel
		{
			activePlayer.aim(pt);
		}
		
		// Update the arrow’s position
		Projectile lastArrow = activePlayer.getLastArrow();
		if (lastArrow != null && lastArrow.isFlying())
		{
			updateArrow(lastArrow);
		}
		
		player1Stats.setText(player1.getStats());
		player2Stats.setText(player2.getStats());
		
		panel.repaint();  // Redraw the window contents
	}
	
	/** Updates the arrow’s position and checks if it hit the other player*/
	private void updateArrow(Projectile lastArrow)
	{
		lastArrow.update(1000/60);
		if (SettingsMenu.getTraceShotEnabled())
		{
			traceSegments.add(new Line(traceSegments.get(
				traceSegments.size()-1).getEndPos(),
				lastArrow.getTipPos(), Color.MAGENTA, 1));
			panel.add(traceSegments.get(traceSegments.size()-1));
		}
		
		if (!lastArrow.isFlying())
		{
			// Projectile has hit the ground or left the screen--
			// prepare to fire again
			swapPlayers();
		}
		else  // See if the arrow hit the enemy
		{
			if (otherPlayer.hitCheck(lastArrow))
			{
				lastArrow.setFlying(false);
				panel.remove(lastArrow);
				
				if (activePlayer == player1)  ++p1Hits;
				else ++p2Hits;
				
				if (otherPlayer.getHealth() > 0)
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
				else  showGameOverScreen();
			}
			
			// Update health bar sizes:
			p1Health.setWidth((int)(200.0 * player1.getHealth() /
				player1.getMaxHealth()));
			p2Health.setWidth((int)(200.0 * player2.getHealth() /
				player2.getMaxHealth()));
		}
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
		if (activePlayer == player1)
		{
			activePlayer = player2;
			otherPlayer = player1;
			player2Stats.setColor(Color.WHITE);
			p2HealthLabel.setColor(Color.WHITE);
			player1Stats.setColor(Color.GRAY);
			p1HealthLabel.setColor(Color.GRAY);
		}
		else
		{
			activePlayer = player1;
			otherPlayer = player2;
			player2Stats.setColor(Color.GRAY);
			p2HealthLabel.setColor(Color.GRAY);
			player1Stats.setColor(Color.WHITE);
			p1HealthLabel.setColor(Color.WHITE);
		}
		marker.setPos(activePlayer.getPos().x,
			activePlayer.getPos().y - 75);
		activePlayer.reload();
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
		case BTN_START_ID:
			new NewGame2(this).setVisible(true);
			break;
		case BTN_SETTINGS_ID:
			new SettingsFrame().setVisible(true);
			break;
		case BTN_REMATCH_ID:
			setUpGame(player1.getName(), player2.getName(), 1, 1,
				player1.getWeapon(), player2.getWeapon());
			break;
		case BTN_TITLESCR_ID:
			setUpMenu();
			break;
		} 
	}
	
	/**
	 * Changes the power of the shot when the scroll wheel is turned.
	 * @param e Indicates how many clicks the scroll wheel was turned, and
	 *          in what direction.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		activePlayer.changePower(-e.getWheelRotation());
	}
	
	/**
	 * Fires the player’s bow when the mouse is clicked.
	 * @param e Object describing what sort of mouse event happened.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (activePlayer == null)  return;
		if (state != GameState.PLAYING)  return;
		
		// DEBUG: Revive the player when the right mouse button
		// (Button3) is clicked
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			activePlayer.revive();
			return;
		}
		
		// Fire the player’s bow, if possible
		if (activePlayer.canFire())
		{
			/* Comment this out to prevent the traces from
			 * disappearing:
			 */
			for (Line l : traceSegments)  panel.remove(l);
			traceSegments.clear();
			
			traceSegments.add(new Line(activePlayer.getAimOrigin(),
				activePlayer.getAimOrigin(), Color.MAGENTA, 1));
			panel.add(traceSegments.get(traceSegments.size()-1));
			
			activePlayer.fire(maxPower);
			panel.add(activePlayer.getLastArrow());
			
			if (activePlayer == player1)  ++p1Shots;
			else  ++p2Shots;
		}
	}
	
	/**
	 * Reacts to the mouse being moved, with no buttons pressed.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.update(e);
		if (settingsBtn != null)  settingsBtn.update(e);
		if (state == GameState.GAME_OVER)
		{
			gameOver.updateButtons(e);
		}
	}
	
	/**
	 * Reacts to the mouse being moved while a button is held down.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.update(e);
		if (settingsBtn != null)  settingsBtn.update(e);
		if (state == GameState.GAME_OVER)
		{
			gameOver.updateButtons(e);
		}
	}
	
	/**
	 * Reacts to the mouse buttons being pressed.
	 * @param e Contains mouse state
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.press(e);
		if (settingsBtn != null)  settingsBtn.press(e);
		if (state == GameState.GAME_OVER)
		{
			gameOver.pressButtons(e);
		}
	}
	
	/**
	 * Reacts to the mouse buttons being released.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.release(e);
		if (settingsBtn != null)  settingsBtn.release(e);
		if (state == GameState.GAME_OVER)
		{
			gameOver.releaseButtons(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		if (ke.getKeyChar() == '')
		{
			setUpMenu();
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		
	}
	
	/**
	 * Reacts to the mouse moving onto the game panel.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseEntered(MouseEvent e) { }
	
	/**
	 * Reacts to the mouse moving off of the game panel.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseExited(MouseEvent e) { }
	
}
// EOF
