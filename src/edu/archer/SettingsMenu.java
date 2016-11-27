// PROJECT: Archer -- a game developed for CS 321

package edu.archer;

/**
 * Manages all of the game’s settings.
 * @author Jacob Smith
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsMenu {
	// Declaration of variables, all variables set to private due to access
	// through get/set functions
	private String s_SettingsBackgroundName;
	private String s_SettingsBackgroundNameTemp = s_SettingsBackgroundName;
	private double d_SettingsGravity;
	private double d_SettingsGravityTemp = d_SettingsGravity;
	private boolean b_SettingsTraceShot;
	private boolean b_SettingsTraceShotTemp = b_SettingsTraceShot;
	public boolean ReadOnly = false;
	
	//Pointer is declared as static due to Singleton Implementation
	private static SettingsMenu Settings = null;
	
/////////////////////////////////////////////////////////////////////////
	
	//List of get & set functions for altering settings values
	
	public String getBackground()
	{ //Returns string value for s_SettingsBackground, , which is the file
	  //name for the background
		return s_SettingsBackgroundName;
	}
	public int getBackgroundIndex()
	{ //Returns the index value used in SettingsFrame's BackgroundComboBox
		//Pull the background number out of the string stored in
		//s_SettingsBackgroundName:
		String temp = s_SettingsBackgroundName.substring(11,12); 
		
		 // value is adjusted by -1 because ComboBox[0] = 
		 // background1.png, etc.
		return (Integer.parseInt(temp))-1;
	}
	public boolean setBackground(int temp)
	{ //Sets the appropriate background by use of a switch statement
		switch (temp) {
		case 0:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		case 1:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		case 2:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		case 3:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		case 4:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		case 5:
			s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1)
				+ ".png";
			break;
		default:
			s_SettingsBackgroundNameTemp = "/backdrops/1.png";
			System.out.println("Error");
		}
		return true;
	}
	
	public double getGravity()
	{ //Returns the value stored in d_SettingsGravity
		return d_SettingsGravity;
	}
	public boolean setGravity(double temp)
	{ //Sets the temp value for gravity to the value passed to it as an 
	  //argument
		d_SettingsGravityTemp = temp;
		//System.out.println("Gravity set to: " + d_SettingsGravityTemp);
		if (d_SettingsGravityTemp == temp) return true;
		else return false;
	}
	public boolean getTraceShotEnabled()
	{ //Returns whether shot tracing is enabled
		return b_SettingsTraceShot;	
	}
	public boolean setTraceShot(boolean temp)
	{ //Sets whether shot tracing is enabled based on the argument passed to
	  //it
		b_SettingsTraceShotTemp = temp;
		//System.out.println("Trace Shot set to: " + b_SettingsTraceShotTemp);
		if (b_SettingsTraceShotTemp == temp) return true;
		else return false;
	}

///////////////////////////////////////////////////////////////////////////
	
	//Functions for retrieval & storage of settings, constructor, singleton settings handler
	
	private boolean GetInitialSettings()
	{ //This function reads the settings file on program launch, and sets
	  //everything to the most recently used settings
		try {
			//Settings.txt is formatted as follows:
			//Background: <filename>
			//Gravity: <value>
			//Trace Shot: <true/false>
			//Our Settings manipulation funtions are designed with 
			//this in mind
			//In the event of a change to the settings file, it will 
			//forcibly revert the file back to the default settings
			
			BufferedReader in = new BufferedReader(new FileReader(
				"Settings.txt"));
			String temp = in.readLine();
			//System.out.println(temp);
			temp = temp.substring(12); //Starts a substring at the
			                           //end of "Background: " 
			// Set the background and backgroundTemp strings equal
			// to the substring:
			s_SettingsBackgroundName = temp;
			s_SettingsBackgroundNameTemp = temp;
			temp = in.readLine();
			
			temp = temp.substring(9); //Starts a substring at the
			                          //end of "Gravity: "
			
			//Parses temp to a double, sets the gravity and 
			//gravityTemp values equal to that value
			d_SettingsGravity = Double.parseDouble(temp);
			d_SettingsGravityTemp = Double.parseDouble(temp);
			
			temp = in.readLine();
			
			temp = temp.substring(12); //Starts a substring at the 
			                           //end of "Trace Shot: "
			
			if ("false".equals(temp))
			{ //If the substring is false, sets traceshot values to 
			  //false
				b_SettingsTraceShot = false;
				b_SettingsTraceShotTemp = false;
			}
			else
			{ //If the substring is false, sets traceshot values to
			  //true
				b_SettingsTraceShot = true;
				b_SettingsTraceShotTemp = true;
			}
			in.close();
		// All exceptions revert to default settings:
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SettingsMenu.class.getName()).log(
				Level.SEVERE, null, ex);
			return revertToDefaultSettings();
		} catch (IOException ex) { //Catches parsing errors
			Logger.getLogger(SettingsMenu.class.getName()).log(
				Level.SEVERE, null, ex);
			return revertToDefaultSettings();
		} catch (StringIndexOutOfBoundsException ex) { //formatting 
			                                       //errors
			Logger.getLogger(SettingsMenu.class.getName()).log(
				Level.SEVERE, null, ex);
			return revertToDefaultSettings();
		} catch (NumberFormatException ex) { //Other parsing errors
			Logger.getLogger(SettingsMenu.class.getName()).log(
				Level.SEVERE, null, ex);
			return revertToDefaultSettings();
                }
		return true;
	}
	public boolean revertToDefaultSettings()
	{ 
                //Sets temporary values to the default settings, calls saveSettings
		b_SettingsTraceShotTemp = false;
		d_SettingsGravityTemp = 9.82;
		s_SettingsBackgroundNameTemp = "/backdrops/1.png";
		System.out.println("ReadOnly = " + ReadOnly);
                File file = new File("Settings.txt");
		if (ReadOnly == false)
		{
			System.out.println("File is set to ReadOnly");
			file.delete();
		}
		return saveSettings();
	}
	public boolean saveSettings()
	{ 
                //Stores the temporary values into the publicly accessible permanent
                //values, writes changes to Settings.txt
		s_SettingsBackgroundName = s_SettingsBackgroundNameTemp;
		d_SettingsGravity = d_SettingsGravityTemp;
		b_SettingsTraceShot = b_SettingsTraceShotTemp;
		try {
			PrintWriter out = new PrintWriter("Settings.txt");
			out.println("Background: " + s_SettingsBackgroundName);
			
			out.println("Gravity: " + d_SettingsGravity);
			
			out.println("Trace Shot: " + b_SettingsTraceShot);
			
			out.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SettingsMenu.class.getName()).log(
				Level.SEVERE, null, ex);
			return revertToDefaultSettings();
		}
		return true;
	}
	public static SettingsMenu GetSettings()
	{
		//Returns SettingsMenu object
		if (Settings == null)
		{
			//Creates new SettingsMenu if null
			Settings = new SettingsMenu();
		}
		return Settings;
	}
	private SettingsMenu()
	{
		GetInitialSettings();
		File file = new File("Settings.txt");
		ReadOnly = !file.canWrite();
	}
}
// EOF
