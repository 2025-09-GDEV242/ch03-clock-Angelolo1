
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-Style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 AM - 11:59 AM and 12:00 PM - 11:59 PM.
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String meridian;         // for "AM" and "PM" display
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        hours.setValue(0);
        minutes.setValue(0);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     * Converts the internal 24-hour value to 12-hour with AM/PM.
     */
    private void updateDisplay()
    {
        int h24 = hours.getValue();       // holds 0...24 range
        int h12 = h24 % 12;               // % 12 takes 13-24 back to 1-12
        if (h12 == 0) {
            h12 = 12;                     // makes sure 0 appears as 12 on clock
        }
        
        // Used to display AM/PM from the internal 24-hour value
        meridian = (h24 < 12) ? "AM" : "PM";
        
        displayString = h12 + ":" + minutes.getDisplayValue() + " " + meridian;
    }
}
