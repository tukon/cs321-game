// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

/**
 *
 * @author Jacob Smith
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsMenu {
    private static String s_SettingsBackgroundName = "/backdrops/1.png";
    private static String s_P1Character = "player.png";
    private static String s_P1Weapon = "weapon.png";
    private static String s_P2Character = "player.png";
    private static String s_P2Weapon = "weapon.png";
    private static double d_SettingsGravity = 9.82;
    private static boolean b_SettingsTraceShot = false;
    
    //List of get & set functions for altering settings values
    public static String getBackground()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Settings.txt"));
            String temp = in.readLine();
            temp = temp.substring(12);
            s_SettingsBackgroundName = temp;
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s_SettingsBackgroundName;
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
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_SettingsBackgroundName = "/backdrops/" + (temp+1) + ".png";
                //System.out.println(s_SettingsBackgroundName);
                break;
            default:
                s_SettingsBackgroundName = "/backdrops/1.png";
                System.out.println("Error");
        }
        return true;
    }
    
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
    
    
    
    
    public static double getGravity()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Settings.txt"));
            in.readLine();
            String temp = in.readLine();
            temp = temp.substring(8);
            d_SettingsGravity = Double.parseDouble(temp);
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d_SettingsGravity;
    }
    public static boolean setGravity(double temp)
    {
        d_SettingsGravity = temp;
        if (d_SettingsGravity == temp) return true;
        else return false;
    }
    public static boolean getTraceShotEnabled()
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Settings.txt"));
            in.readLine();
            in.readLine();
            String temp = in.readLine();
            temp = temp.substring(12);
            //System.out.println("Temp is: " + temp);
            if (temp == "false")
            {
                b_SettingsTraceShot = false;
            }
            else
                b_SettingsTraceShot = true;
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b_SettingsTraceShot;    
    }
    public static boolean setTraceShot(boolean temp)
    {
        b_SettingsTraceShot = temp;
        return b_SettingsTraceShot;
    }
    public static void revertToDefaultSettings()
    {
        b_SettingsTraceShot = false;
        d_SettingsGravity = 9.82;
        s_SettingsBackgroundName = "/backdrops/1.png";
        SettingsMenu.saveSettings();
    }
    public static void saveSettings()
    {
        try {
            PrintWriter out = new PrintWriter("Settings.txt");
            out.println("Background: " + s_SettingsBackgroundName);
            out.println("Gravity: " + d_SettingsGravity);
            out.println("Trace Shot: " + b_SettingsTraceShot);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingsMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}