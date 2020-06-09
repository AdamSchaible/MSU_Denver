/**
 * A program to change the temperature settings 
 * @Adam Schaible
 * @3/13/17
 */
public class Heater
{
    private int temperature;
    private int min;
    private int max;
    private int increment;
    
    /**
     * This constructor sets the minimum and maximum temperatures as well as it sets
     * the temperature increment and the current temperature.
     */
    public Heater(int minimumTemperature, int maximumTemperature)
    {
        temperature = 15;
        increment = 5;
        min = minimumTemperature;
        max = maximumTemperature;
        if(min >= max)
        {
            throw new IllegalArgumentException("Min value must be less than max value");
        }
                    
    }

    /**
     * The getTemperature method returns the temperature
     */
    public int getTemperature()
    {
        return temperature;
    }
    
    /**
     * setIncrement sets a new increment and if user inputs 0 or less the increment will be set
     * to 5
     */
    public void setIncrement(int incrementValue)
    {
        if(incrementValue > 0)
        {
            increment = incrementValue;
        }
        else
        {
            increment = 5;
        }
    }
    
    /**
     * cooler method makes the temperature cooler by an increment as long as the temperature does not 
     * fall below the minimum temperature (min) 
     */
    
    public void cooler()
    {
        if((temperature - increment) >= min)
        {
            temperature -= increment;
        }
    }
    
    /**
     * warmer method increases the temperature by an increment as long as the temperature does not 
     * exceed the maximum temperature (max)
     */
    public void warmer()
    {
        if((temperature + increment) <= max)
        {
            temperature += increment;
        }
    }
   
    /**
     * Equals checks to see if the fields of two heater objects are identical
     */
    public boolean equals(Heater other)
    {
        boolean result = false;
        result = (this.temperature == other.temperature);
        if(result == true)
        {
            result = (this.min == other.min);
            if(result == true)
            {
                result = (this.max == other.max);
                if(result == true)
                {
                    result = (this.increment == other.increment);
                }
            }
        }
        return result;      
    }
}
