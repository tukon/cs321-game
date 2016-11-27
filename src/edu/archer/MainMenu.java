// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * The main menu screen, with “new game”, “practice”, and “settings” buttons.
 * @author adam
 */
public class MainMenu implements ButtonListener, Drawable
{
	/** Background image of the main menu. */
	private Sprite menuBackdrop;
	
	/** Button to start the game. */
	private Button startBtn;
	
	/** Button to change settings. */
	private Button settingsBtn;

	/** Button to start the Practice mode. */
	private Button practiceBtn;
	
	/**
	 * Reference to the Archer object; used by the new game/practice
	 * windows to start the game.
	 */	
	private Archer game;
	
	/** ID of the “start game” button, used by the click handler. */
	private static final int BTN_START_ID = 0;
	
	/** ID of the “settings” button, used by the click handler. */
	private static final int BTN_SETTINGS_ID = 1;

	/** ID of the “practice” button, used by the click handler. */
	private static final int BTN_PRACTICE_ID = 2;
	
	/**
	 * Creates a new main menu screen.
	 * @param game Reference to the Archer object.
	 */
	public MainMenu(Archer game)
	{
		
		menuBackdrop = new Sprite("/backdrops/title.png", 0, 0);
		//start button
		startBtn = new Button(BTN_START_ID,
			650, 270,
			200, 40, "Start");
		startBtn.setListener(this);
		
		//settings button
		settingsBtn = new Button(BTN_SETTINGS_ID,
			650, 270+50+70,
			200, 40, "Setting");
		settingsBtn.setListener(this);
		
		if (SettingsMenu.GetSettings().ReadOnly)
		{
			settingsBtn.setEnabled(false);
		}
		
		//practice button
		practiceBtn = new Button(BTN_PRACTICE_ID,
			650, 270+50+10,
			200, 40, "Practice");
		practiceBtn.setListener(this);
		
		this.game = game;
	}
	
	/**
	 * Re-enables the “new game”/“practice”/“settings” buttons. This is
	 * (indirectly) called by the windows after they close.
	 */
	public void enableButtons()
	{
		startBtn.setEnabled(true);
		practiceBtn.setEnabled(true);
		settingsBtn.setEnabled(true);
	}
	
	/** 
	 * Disable the “new game”/“practice”/“settings” buttons. This is done
	 * whenever a window is opened.
	 */
	public void disableButtons()
	{
		startBtn.setEnabled(false);
		practiceBtn.setEnabled(false);
		settingsBtn.setEnabled(false);
	}
	
	/**
	 * Handles to “new game”/“practice”/“settings” button presses.
	 * @param id Which button was clicked.
	 */
	@Override
	public void clicked(int id)
	{
		disableButtons();
		switch (id)
		{
		case BTN_START_ID:
			new NewGame(game).setVisible(true);
			break;
		case BTN_SETTINGS_ID:
			new SettingsFrame(game).setVisible(true);
			break;
		case BTN_PRACTICE_ID:
			new PracticeMenu(game).setVisible(true);
			break;

		}
	}
	
	/**
	 * Reacts to mouse buttons being pressed down
	 * @param e Describes which button(s) are presssed
	 */
	public void mousePressed(MouseEvent e)
	{
		startBtn.press(e);
		practiceBtn.press(e);
		settingsBtn.press(e);
	}
	
	/**
	 * Reacts to mouse buttons being released
	 * @param e Describes which button(s) are presssed
	 */
	public void mouseReleased(MouseEvent e)
	{
		startBtn.release(e);
		practiceBtn.release(e);
		settingsBtn.release(e);
	}
	
	/**
	 * Reacts to the mouse moving
	 * @param e Describes where the cursor now is
	 */
	public void mouseMoved(MouseEvent e)
	{
		startBtn.update(e);
		practiceBtn.update(e);
		settingsBtn.update(e);
	}
	
	/**
	 * Draws the main menu screen on the game window.
	 * @param g Graphics device to draw with
	 */
	@Override
	public void draw(Graphics g)
	{
		menuBackdrop.draw(g);
		startBtn.draw(g);
		practiceBtn.draw(g);
		settingsBtn.draw(g);
	}
}
// EOF