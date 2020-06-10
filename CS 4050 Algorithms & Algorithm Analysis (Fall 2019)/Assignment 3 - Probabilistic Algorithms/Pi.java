//Name Adam Schaible
//CS 4050
//Programming assignment # 3
import java.util.Random;

public class Pi
{
    //Sides of square = 2000 units and square is centered at the origin
    //Circle is inside the square and is also centered at the origin and has a radius of 1000 units
    public static void main(String[] args)
    {
        Pi pie = new Pi();
        float darts1000 = pie.getPiValue(1000);
        System.out.println("The estimated pi value for 1,000 dart throws is: " + darts1000 + "\n");

        float darts10000 = pie.getPiValue(10000);
        System.out.println("The estimated pi value for 10,000 dart throws is: " + darts10000 + "\n");

        float darts100000 = pie.getPiValue(100000);
        System.out.println("The estimated pi value for 100,000 dart throws is: " + darts100000 + "\n");

        float darts1000000 = pie.getPiValue(1000000);
        System.out.println("The estimated pi value for 1,000,000 dart throws is: " + darts1000000 + "\n");

        float darts100000000 = pie.getPiValue(100000000);
        System.out.println("The estimated pi value for 100,000,000 dart throws is: " + darts100000000 + "\n");

    }


    public float getPiValue(int numberOfDots)
    {
        int x, y; //coordinates
        double r; //radius
        float piValue = 0; //the estimated value of pi
        int m = 0; //the number of dots that land inside the circle

        Random num = new Random();

        for (int i = 0; i < numberOfDots; i++) {
            x = num.nextInt(1000 + 1000) - 1000; //generates random integer values between -1000 to 1000 for x coordinate
            y = num.nextInt(1000 + 1000) - 1000; //generates random integer values between -1000 to 1000 for y coordinate

            if ((x==0) && (y==0)) //at the origin
            {
                r = 0;
            }
            else //not at the origin
            {
                r = java.lang.Math.sqrt(x*x + y*y);
            }

            if (r <= 1000) //then x and y are within the circle
            {
                m++; //tally the dots in the circle
            }
        }
        float m2 = m; //m2 temporarily holder of m
        float numberOfDots2 = numberOfDots; //numberOfDots2 temporarily holder of numberOfDots

        piValue = 4*(m2/numberOfDots2);
        return  piValue;
    }
}
