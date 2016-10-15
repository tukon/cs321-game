// PROJECT: Test Game -- prototype for CS 321 project

package edu.testgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	MouseWheelListener, MouseMotionListener, ButtonListener
{
	private enum GameState { INIT, TITLE_SCREEN, PLAYING };
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
	
	/**
	 * Clicking the start button also fires an arrow; this is used to
	 * ignore the first click
	 */
	private boolean btnClicked = false;
	
	/** ID of the “start game” button, used by the click handler. */
	public static final int BTN_START_ID = 0;
	
	/** ID of the “settings” button, used by the click handler. */
	public static final int BTN_SETTINGS_ID = 1;
	
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
		frame = new JFrame("Test Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
		panel.setOpaque(false);
		panel.addMouseListener(this);
		panel.addMouseWheelListener(this);
		panel.addMouseMotionListener(this);
		frame.add(panel);
		frame.pack();  // Shrink to fit contents, i.e the panel.
		frame.setResizable(false);
		
		timer = new Timer(1000/60, this);
		timer.setRepeats(true);
	}
	
	/** Initialize and show the main menu screen. */
	private void setUpMenu()
	{
		menuBackdrop = new Sprite("menu_bg.png", 0, 0);
		panel.add(menuBackdrop);
		startBtn = new Button(BTN_START_ID,
			GamePanel.WIDTH/2-100,
			GamePanel.HEIGHT/2, 200, 40, "Start");
		startBtn.setListener(this);
		panel.add(startBtn);
		settingsBtn = new Button(BTN_SETTINGS_ID,
			GamePanel.WIDTH/2-100,
			GamePanel.HEIGHT/2+40+10, 200, 40, "Settings…");
		settingsBtn.setListener(this);
		panel.add(settingsBtn);
		
		state = GameState.TITLE_SCREEN;
	}
	
	/**
	 * Hide the main menu items, initialize the game items, and start the
	 * game.
	 */
	public void setUpGame(String p1Name)
	{
		panel.remove(settingsBtn);
		panel.remove(startBtn);
		panel.remove(menuBackdrop);
		
		// Load backdrop
		backdrop = new Sprite(SettingsMenu.getBackground(), 0, 0);
		panel.add(backdrop);
		
		// Load players and their platforms
		player1 = new Player(false, 64, GamePanel.HEIGHT-150,
			p1Name);
		platform1 = new Sprite("platform.png", 0, GamePanel.HEIGHT-150);
		
		player2 = new Player(true, GamePanel.WIDTH-64,
			GamePanel.HEIGHT-150, "Player 2");
		platform2 = new Sprite("platform.png", GamePanel.WIDTH-64-64,
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
	
	/**
	 * Starts the game at the main menu. This function returns immediately;
	 * it does not block until the game exits.
	 */
	public void run()
	{
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
		if (state == GameState.TITLE_SCREEN)
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
			activePlayer.reload();
			swapPlayers();
		}
		else  // See if the arrow hit the enemy
		{
			if (otherPlayer.hitCheck(lastArrow))
			{
				lastArrow.setFlying(false);
				panel.remove(lastArrow);
				activePlayer.reload();
				swapPlayers();
			}
			
			// Update health bar sizes:
			p1Health.setWidth((int)(200.0 * player1.getHealth() /
				player1.getMaxHealth()));
			p2Health.setWidth((int)(200.0 * player2.getHealth() /
				player2.getMaxHealth()));
		}
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
			marker.setPos(64, marker.getPos().y);
		}
	}
	
	/**
	 * Called when a Button is clicked.
	 * @param id ID of the button that was clicked.
	 */
	@Override
	public void clicked(int id)
	{
		if (id == BTN_START_ID)  new NewGame(this).setVisible(true);
		else if (id == BTN_SETTINGS_ID)  new SettingsFrame().setVisible(
			true);
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
	 * @param e 
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!btnClicked)
		{
			btnClicked = (state == GameState.PLAYING);
			return;
		}
		
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
	}
	
	/**
	 * Reacts to the mouse buttons being pressed.
	 * @param e Contains mouse state
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.update(e);
		if (settingsBtn != null)  settingsBtn.update(e);
	}
	
	/**
	 * Reacts to the mouse buttons being released.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{ 
		if (startBtn != null)  startBtn.update(e);
		if (settingsBtn != null)  settingsBtn.update(e);
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
