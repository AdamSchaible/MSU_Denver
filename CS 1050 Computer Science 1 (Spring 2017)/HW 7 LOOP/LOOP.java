import java.util.Scanner;
/**
 * CS1050 20170328 problem # 3
 * 
 * @author Adam Schaible
 * @version 04/02/17
 */
public class LOOP
{
    public static void main(String[] args)
    {
        String string1;
        char char1;
        int numberOfMatchesOfCharInString = 0;
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter a string: ");
        string1 = kb.nextLine();
        System.out.print("Enter a character: ");
        char1 = kb.next().charAt(0);
        
        for(int count = 0;count < string1.length();count++)
        {
            if(string1.charAt(count) == char1)
            {
               numberOfMatchesOfCharInString++;
            }
        }
        System.out.println("\nYour character " + char1 + " was found: " + 
                           numberOfMatchesOfCharInString + " times in the string: " + string1);
    }
    
}
