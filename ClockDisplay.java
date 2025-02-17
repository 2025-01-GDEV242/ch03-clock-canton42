
/**
 * 12 HOUR INTERNAL BRANCH
 * 
 * The ClockDisplay class implements a digital clock display for a
 * US-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 pm (midnight) to 11:59 pm (one minute before 
 * midnight).
 * 
 * This version requires the programmer to maintain an internal variable 
 * to indicate if it is ante-meridian or post-meridian (AM or PM).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Nolan Canto
 * @version 2025-02-10
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean isAM; //internal variable that tracks AM/PM
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        isAM = true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, boolean am)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, am);
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
            if (hours.getValue() == 11) {
                isAM = !isAM;
            }
            if (hours.getValue() == 12) {
                isAM = !isAM;
            }
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean am)
    {
        hours.setValue(hour == 12 ? 12 : hour % 12);
        minutes.setValue(minute);
        isAM = am;
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
     */
    private void updateDisplay()
    {
        int displayHour = hours.getValue();
        if (displayHour == 0) {
            displayHour = 12;
        }
        
        displayString = String.format("%02d:%02d %s", displayHour, minutes.getValue(), isAM ? "AM" : "PM");
    }
}
