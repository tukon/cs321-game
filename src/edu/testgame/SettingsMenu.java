// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

/**
 *
 * @author Jacob Smith
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsMenu {
    private static String s_P1Character = "player.png";
    private static String s_P1Weapon = "weapon.png";
    private static String s_P2Character = "player.png";
    private static String s_P2Weapon = "weapon.png";
    private static String s_SettingsBackgroundName;
    private static String s_SettingsBackgroundNameTemp = s_SettingsBackgroundName;
    private static double d_SettingsGravity;
    private static double d_SettingsGravityTemp = d_SettingsGravity;
    private static boolean b_SettingsTraceShot;
    private static boolean b_SettingsTraceShotTemp = b_SettingsTraceShot;
    
    //going to get and set the values for player1 and player 2
    ///////////////////////////////////////////////////////////////////////////
    public static String getP1Character()
    {
        return s_P1Character;
    }
    
    public static boolean setP1Character(int temp)
    {
        
        switch (temp) {
            case 0:
               s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_P1Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break; 
            default:
                s_P1Character = "player.png";
                System.out.println("Error");
       
        }     
        return true;
        
    }
    
    public static String getP1Weapon()
    {
        return s_P1Weapon;
    }
    
    public static boolean setP1Weapon(int temp)
    {
        switch (temp) {
            case 0:
               s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_P1Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break; 
            default:
                s_P1Character = "weapon.png";
                System.out.println("Error");
       
        }     
        return true;
    }
    
    public static String getP2Character()
    {
        return s_P2Character;
    }
    
    public static boolean setP2Character(int temp)
    {
        switch (temp) {
            case 0:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_P2Character = "player" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break; 
            default:
                s_P2Character = "player.png";
                System.out.println("Error");
       
        }     
        return true; 
    }
    
    
    public static String getP2Weapon()
    {
        return s_P2Weapon;
    }
    
    public static boolean setP2Weapon(int temp)
    {
        switch (temp) {
            case 0:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_P2Character = "weapon" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break; 
            default:
                s_P2Character = "weapon.png";
                System.out.println("Error");
       
        }     
        return true;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    //List of get & set functions for altering settings values
    public static void GetInitialSettings()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Settings.txt"));
            String temp = in.readLine();
            //System.out.println(temp);
            temp = temp.substring(12);
            s_SettingsBackgroundName = temp;
            s_SettingsBackgroundNameTemp = temp;
            temp = in.readLine();
            //System.out.println(temp);
            temp = temp.substring(9);
            //System.out.println(temp);
            d_SettingsGravity = Double.parseDouble(temp);
            d_SettingsGravityTemp = Double.parseDouble(temp);
            //System.out.println(d_SettingsGravity);
            temp = in.readLine();
            //System.out.println(temp);
            temp = temp.substring(12);
            //System.out.println(temp);
            if ("false".equals(temp))
            {
                b_SettingsTraceShot = false;
                b_SettingsTraceShotTemp = false;
                //System.out.println(b_SettingsTraceShot + " " + b_SettingsTraceShotTemp);
            }
            else
            {
                b_SettingsTraceShot = true;
                b_SettingsTraceShotTemp = true;
                //System.out.println(b_SettingsTraceShot + " " + b_SettingsTraceShotTemp);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
            SettingsMenu.revertToDefaultSettings();
            SettingsMenu.GetInitialSettings();
        } catch (IOException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
            SettingsMenu.revertToDefaultSettings();
            SettingsMenu.GetInitialSettings();
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
            SettingsMenu.revertToDefaultSettings();
            SettingsMenu.GetInitialSettings();
        }
    }
    
    public static String getBackground()
    {
        return s_SettingsBackgroundName;
    }
    public static int getBackgroundIndex()
    {
        String temp = s_SettingsBackgroundName.substring(11,12);
        //System.out.println(temp);
        return (Integer.parseInt(temp))-1;
    }
    public static boolean setBackground(int temp)
    {
/*        if (temp + ".png" == temp)
            s_SettingsBackgroundName = temp;
        else
            s_SettingsBackgroundName = temp + ".png";
        if (s_SettingsBackgroundName.equals(temp)) return true;
        else return false;
*/        
        switch (temp) {
            case 0:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_SettingsBackgroundNameTemp = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            default:
                s_SettingsBackgroundNameTemp = "/backdrops/1.png";
                System.out.println("Error");
        }
        return true;
    }
    
    public static double getGravity()
    {
        return d_SettingsGravity;
    }
    public static boolean setGravity(double temp)
    {
        d_SettingsGravityTemp = temp;
        //System.out.println("Gravity set to: " + d_SettingsGravityTemp);
        if (d_SettingsGravityTemp == temp) return true;
        else return false;
    }
    public static boolean getTraceShotEnabled()
    {
        return b_SettingsTraceShot;    
    }
    public static boolean setTraceShot(boolean temp)
    {
        b_SettingsTraceShotTemp = temp;
        if (b_SettingsTraceShotTemp == temp) return true;
        else return false;
    }
    public static void revertToDefaultSettings()
    {
        b_SettingsTraceShotTemp = false;
        d_SettingsGravityTemp = 9.82;
        s_SettingsBackgroundNameTemp = "/backdrops/1.png";
        SettingsMenu.saveSettings();
    }
    public static void saveSettings()
    {
        s_SettingsBackgroundName = s_SettingsBackgroundNameTemp;
        d_SettingsGravity = d_SettingsGravityTemp;
        b_SettingsTraceShot = b_SettingsTraceShotTemp;
        try {
            PrintWriter out = new PrintWriter("Settings.txt");
            out.println("Background: " + s_SettingsBackgroundName);
            //System.out.println("Background: " + s_SettingsBackgroundName);
            out.println("Gravity: " + d_SettingsGravity);
            //System.out.println("Gravity: " + d_SettingsGravity);
            out.println("Trace Shot: " + b_SettingsTraceShot);
            //System.out.println("Trace Shot: " + b_SettingsTraceShot);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}