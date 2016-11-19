// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

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
 * The “master” class, which ties all of the game’s parts together. It contains
 * main(), and is responsible for managing the game window and handling timer
 * and mouse events.
 * 
 * The main menu is handled by an instance of MainMenu, in-game logic is handled
 * by an instance of Game, and the Game Over screen is handled by an instance of
 * GameOverScreen. This class manages switching between the three, and updates
 * them when a timer or mouse event occurs.
 * @author adam
 */
public class Archer implements ActionListener, MouseListener, 
	MouseWheelListener, MouseMotionListener, KeyListener
{
	/** The different states the application can be in. */
	enum State { MAIN_MENU,  PLAYING,  GAME_OVER }
	
	/** Current state of the application. */
	private State state;
	
	/** The game window. */
	private JFrame frame;
	
	/** The canvas the game is drawn on. */
	private GamePanel panel;
	
	/** The Game. */
	private Game game;
	
	/** The main menu. */
	private MainMenu menu;
	
	/**The primary timer that updates the arrows and repaints the canvas. */
	private Timer timer;
	
	/** The Game Over screen. */
	private GameOverScreen gameOver;
	
	/** Reference to the settings menu */
	private SettingsMenu settings;
	
	/**
	 * The program’s main entry point.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args)
	{
		Archer game = new Archer(args);

		game.run();
	}
	
	/**
	 * The constructor, where game objects are initialized.
	 * @param args The command line arguments.
	 */
	public Archer(String[] args)
	{
		// Set up the game window
		frame = new JFrame("Archer Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
		panel.setOpaque(false);
		panel.addMouseListener(this);
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
		
		settings = SettingsMenu.GetSettings();
		
		menu = new MainMenu(this);
		game = new Game();
		gameOver = new GameOverScreen(this);
	}
	
	/** Initialize and show the main menu screen. */
	public void setUpMenu()
	{
		state = State.MAIN_MENU;
		panel.remove(game);
		panel.remove(gameOver);
		panel.add(menu);
	}
	
	/**
	 * Set up the game, and start it.
	 * @param p1Name Player 1’s name
	 * @param p2Name Player 2’s name
	 * @param p1Avatar Index of player 1’s avatar
	 * @param p2Avatar Index of player 2’s avatar
	 * @param weapon1 Player 1’s choice of weapon
	 * @param weapon2 Player 2’s choice of weapon
	 * @param practice True if this is practice mode, false if it is a duel.
	 */
	public void setUpGame(String p1Name, String p2Name,
		int p1Avatar, int p2Avatar,
		Projectile.Type weapon1, Projectile.Type weapon2,
		boolean practice)
	{
		state = State.PLAYING;
		game.setUpGame(p1Name, p2Name, p1Avatar, p2Avatar, weapon1,
			weapon2, practice);
		panel.remove(menu);
		panel.add(game);
		
	}
	
	/** Restarts the game, without changing any settings. */
	public void restart()
	{
		setUpGame(game.getPlayer1().getName(),
			game.getPlayer2().getName(),
			game.getPlayer1().getAvatar(),
			game.getPlayer2().getAvatar(),
			game.getPlayer1().getWeapon(),
			game.getPlayer2().getWeapon(),
			game.isPractice());
	}
	
	/** Shows the “game over” screen. */
	public void showGameOverScreen()
	{
		state = State.GAME_OVER;
		gameOver.setStats(game.getActivePlayer().getName(),
			game.getPlayer1().getName(), game.getP1Shots(),
			game.getP1Accuracy(),
			game.getPlayer2().getName(), game.getP2Shots(),
			game.getP2Accuracy());
		panel.add(gameOver);
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
	 * @param e Not used.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (state == State.PLAYING)
		{
			if (!game.update())  showGameOverScreen();
		}
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
		if (state == State.PLAYING)
		{
			game.mouseWheelTurned(e.getWheelRotation());
		}
	}
		
	/**
	 * Reacts to the mouse being moved, with no buttons pressed.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
		switch (state)
		{
		case MAIN_MENU:
			menu.mouseMoved(e);
			break;
		case PLAYING:
			Point p = e.getPoint();
			if (p != null)  game.aim(p);
			break;
		case GAME_OVER:
			gameOver.mouseMoved(e);
			break;
		}
	}
	
	/**
	 * Reacts to the mouse being moved while a button is held down.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		// We do not really care that a button is held down; we are just
		// interested in the mouse movement
		mouseMoved(e);
	}
	
	/**
	 * Reacts to the mouse buttons being pressed.
	 * @param e Contains mouse state
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		switch(state)
		{
		case MAIN_MENU:
			menu.mousePressed(e);
			break;
		case GAME_OVER:
			gameOver.mousePressed(e);
			break;
		}
	}
	
	/**
	 * Reacts to the mouse buttons being released.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		switch(state)
		{
		case MAIN_MENU:
			menu.mouseReleased(e);
			break;
		case PLAYING:
			game.mouseClicked();
			break;
		case GAME_OVER:
			gameOver.mouseReleased(e);
			break;
		}
	}

	/**
	 * Called when a keyboard key is pressed, then released.
	 * @param ke Describes this key event.
	 */
	@Override
	public void keyTyped(KeyEvent ke)
	{
		// TODO: do this without using a literal escape character
		char ESC = '';
		if (ke.getKeyChar() == ESC)
		{
			setUpMenu();
		}
	}
	
	/**
	 * Required to implement MouseListener, but unused.
	 * @param e Object describing what sort of mouse event happened.
	 */
	@Override
	public void mouseClicked(MouseEvent e) { }

	/**
	 * Called when a keyboard key is pressed down. Required to implement
	 * KeyListener, but not used.
	 * @param ke Describes this key event.
	 */
	@Override
	public void keyPressed(KeyEvent ke) { }

	/**
	 * Called when a keyboard key is released.
	 * @param ke Describes this key event.
	 */
	@Override
	public void keyReleased(KeyEvent ke) { }
	
	/**
	 * Reacts to the mouse moving onto the game panel. Required to
	 * implement MouseListener, but not used.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseEntered(MouseEvent e) { }
	
	/**
	 * Reacts to the mouse moving off of the game panel. Required to
	 * implement MouseListener, but not used.
	 * @param e Contains mouse state
	 */
	@Override
	public void mouseExited(MouseEvent e) { }
	
}
// EOF
