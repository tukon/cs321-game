// PROJECT: Test Game -- prototype for CS413 project
package edu.testgame;

/**
 *
 * @author blasek0
 */
public class Settings {
    private String s_SettingsBackgroundName = "backdrop.png";
    private double d_SettingsGravity = 9.82;
    private boolean b_SettingsTrace = true;
    private int i_SettingsTurnTime = 10;
    private int i_SettingsGameTime = 600;
    
    public String getBackground()
    {
        return s_SettingsBackgroundName;
    }
    public double getGravity()
    {
        return d_SettingsGravity;
    }
    public boolean getTraceEnabled()
    {
        return b_SettingsTrace;
    }
    public int getMaxTurnTime()
    {
        return i_SettingsTurnTime;
    }
    public int getMaxGameTime()
    {
        return i_SettingsGameTime;
    }
}
