// PROJECT: Test Game -- prototype for CS413 project
package edu.testgame;

/**
 *
 * @author blasek0
 */
public class SettingsMenu {
    private String s_SettingsBackgroundName = "backdrop.png";
    private double d_SettingsGravity = 9.82;
    private boolean b_SettingsTraceShot = true;
    private boolean b_SettingsPreviewShot = true;
    private int i_SettingsTurnTime = 10;
    private int i_SettingsGameTime = 600;
    
    //List of get & set functions for altering settings values
    public String getBackground()
    {
        return s_SettingsBackgroundName;
    }
    public boolean setBackground(String temp)
    {
        //Add controls for background field including .png vs not including .png
        s_SettingsBackgroundName = temp + ".png";
        if (s_SettingsBackgroundName.equals(temp)) return true;
        else return false;
    }
    public double getGravity()
    {
        return d_SettingsGravity;
    }
    public boolean setGravity(double temp)
    {
        d_SettingsGravity = temp;
        if (d_SettingsGravity == temp) return true;
        else return false;
    }
    public boolean getTraceShotEnabled()
    {
        return b_SettingsTraceShot;
    }
    public boolean setTraceShot(boolean temp)
    {
        b_SettingsTraceShot = temp;
        if (b_SettingsTraceShot == temp) return true;
        else return false;
    }
    public boolean getPreviewShotEnabled()
    {
        return b_SettingsPreviewShot;
    }
    public boolean setPreviewShot(boolean temp)
    {
        b_SettingsPreviewShot = temp;
        if (b_SettingsPreviewShot == temp) return true;
        else return false;        
    }
    public int getMaxTurnTime()
    {
        return i_SettingsTurnTime;
    }
    public boolean setMaxTurnTime(int temp)
    {
        i_SettingsTurnTime = temp;
        if (i_SettingsTurnTime == temp) return true;
        else return false;
    }
    public int getMaxGameTime()
    {
        return i_SettingsGameTime;
    }
    public boolean setMaxGameTime(int temp)
    {
        i_SettingsGameTime = temp;
        if (i_SettingsTurnTime == temp) return true;
        else return false;
    }
}
