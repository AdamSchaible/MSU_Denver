import java.util.Scanner;
/**
 * Write a description of class SalaryCalculator here.
 * 
 * @Adam Schaible 
 * @06/29/20
 */
public class SalaryCalculator
{
    public static void main(String[] args)
    {
        int daysWorked;
        int dailySalary = 1; //in cents
        int totalPay = 0; // in cents
        
        Scanner kb = new Scanner(System.in);
        while(true)
        {
            String input = "";

            try
            {
                System.out.println("The program is capable of calculating salary from 1 to 31 days");
                System.out.print("Enter the number of days that you worked to get a salary calculation: ");
                input = kb.nextLine();

                if (input.contains("."))
                {
                    System.out.println("\nYou entered: '" + input + "', which is not a valid number of days");
                    System.out.println("Enter a whole number of 1 up to 31 for the number of days\n");
                }

                else
                {
                    daysWorked = Integer.parseInt(input);
                    if ((daysWorked < 1) || (daysWorked > 31))
                    {
                        throw new Exception();
                    }
                    break;
                }
            }
            catch (Exception e)
            {
                System.out.println("\nYou entered: '" + input + "', which is not a valid number of days");
                System.out.println("Enter a whole number of 1 up to 31 for the number of days\n");
            }
        }

        for(int count = 1;count <= daysWorked;count++)
        {
            totalPay = totalPay + dailySalary;
            int dailySalaryDollars = dailySalary / 100;
            int dailySalaryPennies = dailySalary % 100;
            int totalPaydollars = totalPay / 100;
            int totalPaycents = totalPay % 100;
            
            System.out.println("Day#" + count + "   Daily Salary: $" + dailySalaryDollars + "." + penniesToString(dailySalaryPennies) + 
                               "      Total Pay For The Period: $" + totalPaydollars + "." + penniesToString(totalPaycents));  
            System.out.println("");            
            dailySalary *= 2; 
        }
               
            
    }
    
    private static String penniesToString (int x)
        {   
            String cents;
            if(x < 10)
            {
                cents = "0" + x;
            }
            else
            {
                cents = "" + x;
            }
            return cents;
        }
}
