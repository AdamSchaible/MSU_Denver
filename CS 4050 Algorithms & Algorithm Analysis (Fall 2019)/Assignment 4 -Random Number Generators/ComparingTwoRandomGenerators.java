//Name Adam Schaible
//CS4050
//Programming Assignment # 4
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class ComparingTwoRandomGenerators
{
    public static void main(String[] args) throws IOException {
        int[] tally_Of_GeneratedNumbers_0_to_9_By_Java_Random = new int[10]; //slot 0 = number zero....slot 9 = number nine
        Arrays.fill(tally_Of_GeneratedNumbers_0_to_9_By_Java_Random, 0);

        int[] tally_Of_GeneratedSequences_00_to_99_ByJava_Random = new int[100]; //slot 0 = sequence 00 .... slot 99 = sequence 99
        Arrays.fill(tally_Of_GeneratedSequences_00_to_99_ByJava_Random, 0);

        int[] tally_Of_GeneratedNumbers_0_to_9_Home_Brewed = new int[10]; //slot 0 = number zero....slot 9 = number nine
        Arrays.fill(tally_Of_GeneratedNumbers_0_to_9_Home_Brewed, 0);

        int[] tallyOfGeneratedSequences_00_to_99_Home_Brewed = new int[100]; //slot 0 = sequence 00 .... slot 99 = sequence 99
        Arrays.fill(tallyOfGeneratedSequences_00_to_99_Home_Brewed, 0);

        Random generator = new Random();
        String previousNumber = "";

        for (int i = 0; i < 4000000; i++)
        {
            String sequence = "";

            int randomNumber = generator.nextInt(10);
            tally_Of_GeneratedNumbers_0_to_9_By_Java_Random[randomNumber] = tally_Of_GeneratedNumbers_0_to_9_By_Java_Random[randomNumber] + 1;
            if (!previousNumber.equals("") && !previousNumber.equals("0"))
            {
                sequence = sequence + previousNumber + randomNumber;
                int sequenceAsInt = Integer.parseInt(sequence);
                tally_Of_GeneratedSequences_00_to_99_ByJava_Random[sequenceAsInt] = tally_Of_GeneratedSequences_00_to_99_ByJava_Random[sequenceAsInt] + 1;
            }

            else
            {
                sequence = sequence + randomNumber;
                int sequenceAsInt = Integer.parseInt(sequence);
                tally_Of_GeneratedSequences_00_to_99_ByJava_Random[sequenceAsInt] = tally_Of_GeneratedSequences_00_to_99_ByJava_Random[sequenceAsInt] + 1;
            }
            previousNumber = "" + randomNumber;
        }
        System.out.println("RESULTS FOR JAVA'S BUILT IN RANDOM NUMBER GENERATOR **************");
        System.out.println("The 0-9 digit tally output For Java's random generator");
        System.out.println("digit | Tally");
        int counter = 0;
        for (int i : tally_Of_GeneratedNumbers_0_to_9_By_Java_Random)
        {
            System.out.println(counter + "\t" + i);
            counter = counter + 1;
        }


        System.out.println("The 00 to 99 pattern digit tally output For Java's random generator");
        System.out.println("digit | Tally");
        counter = 0;
        for (int i : tally_Of_GeneratedSequences_00_to_99_ByJava_Random)
        {
            if (i==0)
            {
                System.out.println("0" + counter + "\t" + i);
            }
            else {
                System.out.println(counter + "\t" + i);
            }
            counter = counter + 1;
        }

        //The home brewed generator below ***********************************************************************************************

        //code below to convert file into byte array derived from https://www.mkyong.com/java/how-to-convert-file-into-an-array-of-bytes/
        File wav = new File("Record Static-SoundBible.com-306727640.wav");
        byte[] bytesArray = new byte[(int) wav.length()];
        FileInputStream fis = new FileInputStream(wav);
        fis.read(bytesArray);
        fis.close();



        previousNumber = ""; //to reset
        for (int i = 0; i < 4000000; i++)
        {

            String sequence = "";
            int random = bytesArray[i];
            int randomNumber = Math.abs(random % 10);
            tally_Of_GeneratedNumbers_0_to_9_Home_Brewed[randomNumber] = tally_Of_GeneratedNumbers_0_to_9_Home_Brewed[randomNumber] + 1;
            if (!previousNumber.equals("") && !previousNumber.equals("0"))
            {
                sequence = sequence + previousNumber + randomNumber;
                int sequenceAsInt = Integer.parseInt(sequence);
                tallyOfGeneratedSequences_00_to_99_Home_Brewed[sequenceAsInt] = tallyOfGeneratedSequences_00_to_99_Home_Brewed[sequenceAsInt] + 1;
            }

            else
            {
                sequence = sequence + randomNumber;
                int sequenceAsInt = Integer.parseInt(sequence);
                tallyOfGeneratedSequences_00_to_99_Home_Brewed[sequenceAsInt] = tallyOfGeneratedSequences_00_to_99_Home_Brewed[sequenceAsInt] + 1;
            }
            previousNumber = "" + randomNumber;
        }

        System.out.println("RESULTS FOR MY CUSTOM MADE RANDOM GENERATOR **************");
        System.out.println("The 0-9 digit tally output For my CUSTOM MADE RANDOM GENERATOR");
        System.out.println("digit | Tally");
        counter = 0;
        for (int i : tally_Of_GeneratedNumbers_0_to_9_Home_Brewed)
        {
            System.out.println(counter + "\t" + i);
            counter = counter + 1;
        }

        System.out.println("The 00 to 99 pattern digit tally output for my own custom made generator");
        System.out.println("digit | Tally");
        counter = 0;
        for (int i : tallyOfGeneratedSequences_00_to_99_Home_Brewed)
        {
            if (i==0)
            {
                System.out.println("0" + counter + "\t" + i);
            }
            else {
                System.out.println(counter + "\t" + i);
            }
            counter = counter + 1;
        }

    }
}
