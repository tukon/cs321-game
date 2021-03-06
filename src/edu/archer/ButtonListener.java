// PROJECT: Archer -- a game developed for CS 321
package edu.archer;

/**
 * Reacts when a Button is clicked.
 * @author adam
 */
public interface ButtonListener
{
	/**
	 * Called when a Button was clicked.
	 * @param id The ID of the button that was clicked.
	 */
	public void clicked(int id);
}
// EOF