
/**
 * The bird class is designed to simulate some of the things that most flying birds can do
 * 
 * @author Adam Schaible 
 * @version 2/13/17
 */
public class Bird
{
    //Characteristics of birds
    private String name;
    private String size;
    private int flyingSpeed;
    private double weight;
    private int currentElevation;

    /**
     * This sets the defaults for the characteristics of your flying bird. 
     * Enter your bird name in parenthesis 
     * Enter you birds size in parenthesis
     * Enter your birds flying speed as a positive whole number
     * Enter a positive weight for your bird as a number
     * Enter the birds elevation as a whole number
     * 
     */
    public Bird(String birdName, String birdSize, int birdFlyingSpeed, double birdWeight, int currentElevationOfBird)
    {
       name=birdName;
       size=birdSize;
       if(birdFlyingSpeed>0)
       {
           flyingSpeed=birdFlyingSpeed;
       }
       else
       {
           System.out.println("Since you did not enter a positive number for you birds flight speed");
           System.out.println("the default bird's flight speed has been set to zero");
           System.out.println("If you wish to change the flight speed use the setBirdsFlightSpeed method");
           
       }
       
       if(birdWeight>0)
       {
           weight=birdWeight;
       }
       else
       {
           System.out.println("Since you entered a bird weight of less than zero the bird you created is not real.");
           System.out.println("The bird will be assigned a default weight of zero"); 
           System.out.println("If you wish to change the birds weight you can do so by using the setBirdsWeight method");
       }
             
       currentElevation=currentElevationOfBird;
    }

    /**
     * This method is designed to feed the bird and increase its weight by 10 each time it eats
     */
    public void setBirdToEat()
    {
       weight=weight+10;
    }
    
    /**
     * This method is designed to make the bird fly up and as a result the birds elevation will
     * increase by 100 and its weight will drop by 3 each time 
     */
    public void setBirdToFlyUp()
    { 
      currentElevation=currentElevation+100;
      weight=weight-3;
    }
    
    /**
     * This method is designed to make the bird fly down and as a result the birds elevation will
     * decrease by 100 and its weight will drop by 1 each time 
     */
    public void setBirdToFlyDown()
    { 
      currentElevation=currentElevation-100;
      weight=weight-1;
    }
    
    /**
     * This method is designed to get the name of the bird
     */
    public String getBirdsName()
    {
       return name;
    }
    
    /**
     * This method is designed to get the size of the bird
     */
    public String getBirdsSize()
    {
       return size;
    }
    
    /**
     * This method is designed to get the flying speed of the bird
     */
    public int getBirdsFlyingSpeed()
    {
       return flyingSpeed;
    }
    
    /**
     * This method is designed to get the weight of the bird
     */
    public double getBirdsWeight()
    {
       return weight;
    }
    
    /**
     * This method is designed to get the current elevation of the bird
     */
    public int getBirdsElevation()
    {
       return currentElevation;
    }
    
    /**
     * This method is designed to rename the the bird
     * Place parenthesis around the name
     */
    public void setBirdsName(String newNameForTheBird)
    {
       name=newNameForTheBird;
    }
    
    /**
     * This method is designed to change the size of the bird
     * Place parenthesis around the size
     */
    public void setBirdsSize(String newSizeOfTheBird)
    {
       size=newSizeOfTheBird;
    }
    
    /**
     * This method is designed to change the flying speed of the bird 
     * Enter as a whole number
     */
    public void setBirdsFlightSpeed(int newFlyingSpeedOfTheBird)
    {
       flyingSpeed=newFlyingSpeedOfTheBird;
    }
    
    /**
     * This method is designed to change the weight of the bird 
     * Enter a number
     */
    public void setBirdsWeight(double newBirdsWeight)
    {
       weight=newBirdsWeight;
    }
    
    /**
     * This method is designed to change the bird's elevation 
     * Enter a whole number
     */
    public void setBirdsElevation(int newElevation)
    {
        currentElevation=newElevation;
    }
    
    public String toString()
    {
        return "Birds name: " + name + "Birds size: " + size + "Birds flying speed: " + flyingSpeed + "Bird's Weight: " + weight + "Bird's current elevation: " + currentElevation;
    }
}
