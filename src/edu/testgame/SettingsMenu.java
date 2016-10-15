// PROJECT: Test Game -- prototype for CS 321 project
package edu.testgame;

/**
 *
 * @author blasek0
 */
public class SettingsMenu {
    private static String s_SettingsBackgroundName = "backdrop1.png";
    private static double d_SettingsGravity = 9.82;
    private static boolean b_SettingsTraceShot = true;
    private static boolean b_SettingsPreviewShot = true;
    private static double i_SettingsTurnTime = 10;
    private static double i_SettingsGameTime = 600;
    
    //List of get & set functions for altering settings values
    public static String getBackground()
    {
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
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 1:
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 2:
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 3:
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 4:
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            case 5:
                s_SettingsBackgroundName = "backdrop" + (temp+1) + ".png";
                System.out.println(s_SettingsBackgroundName);
                break;
            default:
                s_SettingsBackgroundName = "backdrop1.png";
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
        d_SettingsGravity = temp;
        if (d_SettingsGravity == temp) return true;
        else return false;
    }
    public static boolean getTraceShotEnabled()
    {
        return b_SettingsTraceShot;
    }
    public static boolean setTraceShot(boolean temp)
    {
        b_SettingsTraceShot = temp;
        if (b_SettingsTraceShot == temp) return true;
        else return false;
    }
    public static boolean getPreviewShotEnabled()
    {
        return b_SettingsPreviewShot;
    }
    public static boolean setPreviewShot(boolean temp)
    {
        b_SettingsPreviewShot = temp;
        if (b_SettingsPreviewShot == temp) return true;
        else return false;        
    }
    public static double getMaxTurnTime()
    {
        return i_SettingsTurnTime;
    }
    public static boolean setMaxTurnTime(double temp)
    {
        i_SettingsTurnTime = temp;
        if (i_SettingsTurnTime == temp) return true;
        else return false;
    }
    public static double getMaxGameTime()
    {
        return i_SettingsGameTime;
    }
    public static boolean setMaxGameTime(double temp)
    {
        i_SettingsGameTime = temp;
        if (i_SettingsTurnTime == temp) return true;
        else return false;
    }
}