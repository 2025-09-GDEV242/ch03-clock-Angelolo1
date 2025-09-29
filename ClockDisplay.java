
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
     * Create a new clock set at 12:00 AM
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);      // cap at 13 -> 0-12
        minutes = new NumberDisplay(60);
        hours.setValue(12);
        minutes.setValue(0);
        meridian = "AM";
        updateDisplay();
    }

    /**
     * Create a new clock set at the given hour and minute
     * (hour given in 24-hour form, 0–23).
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(13);
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

            // flips AM/PM when 12 hits
            if (hours.getValue() == 12) {
                meridian = meridian.equals("AM") ? "PM" : "AM";
            }
            // skips 0 and jumps right to 1
            else if (hours.getValue() == 0) {
                hours.setValue(1);

            }

        }
        updateDisplay();

    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        // determines AM/PM
        meridian = (hour < 12) ? "AM" : "PM";
        // converts the 24 hour to 12 hour
        int h12 = hour % 12;
        if (h12 == 0) h12 = 12;
        
        hours.setValue(h12);
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
        int h = hours.getValue();    
        if (h == 0) h = 12;         // never show 0

        displayString = h + ":" + minutes.getDisplayValue() + " " + meridian;
    }
}
