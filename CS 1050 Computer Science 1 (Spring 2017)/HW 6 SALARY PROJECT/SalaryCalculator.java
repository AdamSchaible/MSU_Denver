import java.util.Scanner;
/**
 * Write a description of class SalaryCalculator here.
 * 
 * @Adam Schaible 
 * @04/02/17
 */
public class SalaryCalculator
{
    public static void main(String[] args)
    {
        int daysWorked;
        int dailySalary = 1; //in cents
        int totalPay = 0; // in cents
        
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter the number of days that you worked to get a salary calculation: ");
        daysWorked = kb.nextInt();
        
        while(daysWorked < 1)
        {
            System.out.println("");
            System.out.println("Error: You did not enter in a positive number of days");
            System.out.print("Please enter the number of days you worked here as a positive number: ");
            daysWorked = kb.nextInt();
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
