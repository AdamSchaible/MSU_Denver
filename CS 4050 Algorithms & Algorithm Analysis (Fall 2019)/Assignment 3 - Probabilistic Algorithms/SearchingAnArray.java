//Name Adam Schaible
//CS 4050
//Programming assignment # 3
import java.util.Random;

public class SearchingAnArray
{
    public static void main(String[] args)
    {
        int[] arrayToSearch = new int[1000];
        Random myRandomNumberGenerator = new Random();

        //generate an array of 1000 random integers --------------------------------------------
        for(int i = 0;i<1000;i++)
        {
            int temp = myRandomNumberGenerator.nextInt();
            arrayToSearch[i] = temp;
        }
        int totalNumberOfSearches = 0;
        SearchingAnArray theSearch = new SearchingAnArray();

        for (int numberOfSearchesDone = 1;numberOfSearchesDone <= 100; numberOfSearchesDone++) //100 searches is done
        {
            totalNumberOfSearches += theSearch.randomSearch(arrayToSearch);
        }
        float tempTotalNumberOfSearches = totalNumberOfSearches;
        float averageNumberOfComparisons = tempTotalNumberOfSearches/100; //100 is the number of searches done
        System.out.println("The average number of random guesses to find the array slot with a known value is : " + averageNumberOfComparisons);
    }


    public int randomSearch(int[] theArray)
    {
        Random randomGenerator = new Random();
        int slotToCheck = randomGenerator.nextInt(theArray.length-1);
        int lookingForValue = theArray[slotToCheck];
        int numberOfAttempts = 1;
        boolean foundTheValue = false;
        int numberOfMaximumGuesses = 5000;


        while((numberOfAttempts <= numberOfMaximumGuesses) && !foundTheValue)
        {
            int spotToGuess = randomGenerator.nextInt(theArray.length-1);
            if(theArray[spotToGuess] == lookingForValue)
            {
                foundTheValue = true;
            }
            if(!foundTheValue) {
                numberOfAttempts++;
            }
        }
        return numberOfAttempts;
    }
}
