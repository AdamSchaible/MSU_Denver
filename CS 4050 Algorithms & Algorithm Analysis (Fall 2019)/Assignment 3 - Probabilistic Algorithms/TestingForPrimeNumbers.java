//Name Adam Schaible
//CS 4050
//Programming assignment # 3
import java.util.Random;


public class TestingForPrimeNumbers
{
    public static void main(String[] args)
    {
        int[] evenCompositeNumbers = new int[40];
        int[] finalDigitIs5CompositeNumbers = new int[40];
        int[] divisibleBy3CompositeNumbers = new int[40];
        int[] productOfTwoPositiveIntegersBothGreaterThan1Composite = new int[40];
        int[] randomlyGeneratedIntegersDivisibleByKComposite = new int[40]; //k is greater than 1

        Random myRandomNumberGenerator = new Random();


        //making an array of random even composite integers --------------------------------------------
        for(int i = 0;i<40;i++)
        {
            int temp = -1; //default value
            boolean loop = true;
            while (loop)
            {
                temp = myRandomNumberGenerator.nextInt(2000);//select from range of integers from 0 to 2000
                if ((temp > 2) && (temp%2 == 0))
                {
                    loop = false;
                }
            }
            evenCompositeNumbers[i] = temp;
        }

        //making an array of random composite integers where the last number is 5 -------------------------------
        for(int i = 0;i<40;i++)
        {
            int temp = -1; //default value
            boolean loop = true;
            while (loop)
            {
                temp = myRandomNumberGenerator.nextInt(2000);//select from range of 0 to 2000
                int temp2 = temp * 1;
                //transform number into array, where numbers in array are in reverse order
                //arr[0] is the last number and arr[1] is the second to last number
                //code for this is derived from https://coderanch.com/t/493516/java/convert-number-Array
                int len = Integer.toString(temp2).length();
                int[] arr = new int[len];
                int i2 = 0;
                do {
                    arr[i2] = temp2 % 10;
                    temp2 /= 10;
                    i2++;
                } while (temp2 != 0);
                //end of transform number into array


                if (arr.length > 1)
                {
                    if ((arr[0] == 5))
                    loop = false;
                }
            }

            finalDigitIs5CompositeNumbers[i] = temp;
        }

        //Making an array of composite numbers divisible by 3 ---------------------------------------------------
        for(int i = 0;i<40;i++)
        {
            int temp = -1; //default value
            boolean loop = true;
            while (loop)
            {
                temp = myRandomNumberGenerator.nextInt(2000);//select from range of 0 to 2000
                if ((temp > 3) && (temp%3 == 0))
                {
                    loop = false;
                }
            }
            divisibleBy3CompositeNumbers[i] = temp;
        }

        //Composite is made from the product of two positive integers ---------------------------------------------------
        for(int i = 0;i<40;i++)
        {
            int number1 = -1; //default value
            int number2 = -1; //default value
            boolean loop = true;
            while (loop)
            {
                number1 = myRandomNumberGenerator.nextInt(44);//returns a integer whose maximum is the square root of 2000
                number2 = myRandomNumberGenerator.nextInt(44);//returns a integer whose maximum is the square root of 2000
                if ((number1 > 2) && (number2 > 2))
                {
                    loop = false;
                }
            }
            productOfTwoPositiveIntegersBothGreaterThan1Composite[i] = number1*number2;
        }

        //making composite integers using X = X - X%K where X is now divisible by K -------------------
        for(int i = 0;i<40;i++)
        {
            int x = -1; //default value
            int k = -1; //default value
            boolean loop = true;
            while (loop)
            {
                x = myRandomNumberGenerator.nextInt(2000);//select from range of 0 to 2000

                if (x > 3)
                {
                    boolean divisible = false;
                    int n = 2;
                    while ((n<=(x/2)) && !divisible)
                    {
                        divisible = (x%n == 0);
                        n++;
                    }

                    if (divisible)
                    {
                        loop = false;
                    }

                }
            }
            randomlyGeneratedIntegersDivisibleByKComposite[i] = x;
        }
        int [][] randomCollection = {evenCompositeNumbers,finalDigitIs5CompositeNumbers,divisibleBy3CompositeNumbers,productOfTwoPositiveIntegersBothGreaterThan1Composite,randomlyGeneratedIntegersDivisibleByKComposite};
        TestingForPrimeNumbers test = new TestingForPrimeNumbers();
        //for k = 10
        float failureRateKEquals10 = test.rejectionRate(10,randomCollection);
        System.out.println("The percent of Composite numbers detected for k=10 is: " + failureRateKEquals10 + "%\n");
        //for k = 100
        float failureRateKEquals100 = test.rejectionRate(100,randomCollection);
        System.out.println("The percent of Composite numbers detected for k=100 is: " + failureRateKEquals100 + "%\n");

        //for k = 1,000
        float failureRateKEquals1000 = test.rejectionRate(1000,randomCollection);
        System.out.println("The percent of Composite numbers detected for k=1,000 is: " + failureRateKEquals1000 + "%\n");

        //for k = 10,000
        float failureRateKEquals10000 = test.rejectionRate(10000,randomCollection);
        System.out.println("The percent of Composite numbers detected for k=10,000 is: " + failureRateKEquals10000 + "%\n");
    }


    public float rejectionRate(int kNumberRandomPicks, int[][] collection)
    {
        Random randomKGenerator = new Random();
        int rejectionFinds = 0;
        for (int i=0; i < collection.length; i++) {
            for (int j=0; j < collection[i].length; j++) {
                //console.log(arr[i][j]);
                int m = 1;
                boolean foundComposite = false;
                while (!foundComposite && m <= kNumberRandomPicks)
                {
                    int k = randomKGenerator.nextInt(collection[i][j] -1) + 1; //generates a random k between 2 up to the composite
                    foundComposite = (collection[i][j]%k == 0);
                    if (foundComposite)
                    {
                        rejectionFinds++;
                    }
                    m++;
                }


            }
        }
        float rejectionFindsTemp = rejectionFinds;
        float percentResult = (rejectionFindsTemp/200)*100; //200 is the total number of composite numbers tested
        return percentResult;
    }
}
