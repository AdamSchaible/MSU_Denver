/**
 * The AccountApplication simulates a bank, which allows you to do transactions and get records.
 *
 * @author Adam Schaible
 * @version 6/29/20
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AccountApplication
{
    private static Scanner kb = new Scanner(System.in).useDelimiter("\\n");
    private static Account account1;
    
    
    public static void main(String[] args)
    {
        boolean selectInput = true;
        String bankName;
        String typeOfAccount = null;
        String accountNumber;
        long initialBalance = -1;

        while(true)
        {
            System.out.print("Enter the name of the bank: ");
            bankName = kb.next();
            if (bankName.length() > 0)
            {
                break;
            }
            else
            {
                System.out.println("You entered nothing in for the name of the bank");
            }
        }
        
        do
        {
            System.out.println("\nSelect from the following account types");
            System.out.println("1 - for checking");
            System.out.println("2 - for saving");
            System.out.println("3 - for money market");
            System.out.println("4 - for certificate of deposit\n");
            System.out.print("Enter your selection number: "); 
            int selection = kb.nextInt();
            
            switch(selection)
            {
                case 1:
                typeOfAccount = "checking";
                break;
                
                case 2:
                typeOfAccount = "saving";
                break;
                
                case 3:
                typeOfAccount = "money market";
                break;
                
                case 4:
                typeOfAccount = "certificate of deposit";
                break;
                
                default:
                System.out.println("\nYou entered: " + selection + "\nPlease Enter a valid selection between 1-4.");
                break;
            }
        }while(typeOfAccount == null);

        while(true)
        {
            System.out.println("Please enter your account number, digits only, no spaces: ");
            accountNumber = kb.next();
            String regex = "[0-9]+";

            if (accountNumber.length() > 0 && accountNumber.matches(regex))
            {
                break;
            }
            else
            {
                System.out.println("You entered: '" + accountNumber + "' for your account number, which is not in the correct format");
            }
        }

        while(initialBalance < 0)
        {
            long amount;
            while(true)
            {
                String input = "";

                try
                {
                    System.out.print("\nEnter an initial balance for the account that is a whole number that is greater than zero: ");
                    input = kb.next();
                    if (input.contains("."))
                    {
                        throw new Exception();
                    }
                    amount = Long.parseLong(input);;
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("\nYou entered: '" + input + "', which is not a whole number");
                }
            }
         
         if(amount > 0)
         {
             initialBalance = amount;
         }
         else
         {
             System.out.println("You did not enter in a positive amount");
         }
             
        }
        
        account1 = new Account(bankName, typeOfAccount, accountNumber, initialBalance);
        
        do
        {
            System.out.println("\nMENU");
            System.out.println("====================================================");
            System.out.println("1 - Add a new transaction");
            System.out.println("2 - Remove a specified transaction");
            System.out.println("3 - Remove all transactions");
            System.out.println("4 - Display account information");
            System.out.println("5 - List all transactions");
            System.out.println("6 - Get current balance");
            System.out.println("7 - Quit");
            System.out.print("\nEnter a selection between 1-7: ");
            int selection = kb.nextInt();
            
            switch(selection)
            {
                case 1:
                add();
                break;
                
                case 2:
                remove();
                break;
                
                case 3:
                removeAllTransactions();
                break;
                
                case 4:
                displayAccountInformation();
                break;
                
                case 5:
                listAllTransactions();
                break;
                
                case 6:
                getBalance();
                break;
                
                case 7:
                selectInput = false;
                break;
                
                default:
                System.out.println("\nInvalid selection. Please enter a selection between 1-7");
                break;
                
            }
                                    
        }while(selectInput);
        
    }
    
    private static void add()
    {
        String transactionType = null;
        long amountOfTransaction = 0;
        
        String transactionDate;

        while(true)
        {
            System.out.print("\nEnter in the date of the transaction in the format of MM-DD-YYYY: ");
            transactionDate = kb.next();

            if (validateJavaDate(transactionDate))
            {
                break;
            }
            else
            {
                System.out.println("You Entered: '" + transactionDate +
                        "' and this date is not in the correct format of  MM-DD-YYYY");
            }
        }

        do
        {
            System.out.println("\nSelect from the following transaction types \n");
            System.out.println("1 - For a deposit");
            System.out.println("2 - For a withdrawal");
            System.out.println("3 - For a interest payment");
            System.out.println("4 - For a service charge");
            System.out.print("Enter a selection between 1-4: ");
            int selection = kb.nextInt();
            
            switch(selection)
            {
                case 1:
                transactionType = "deposit";
                break;
                
                case 2:
                transactionType = "withdrawal";
                break;
                
                case 3:
                transactionType = "interest payment";
                break;
                
                case 4:
                transactionType = "service charge";
                break;
                
                default:
                System.out.println("\nPlease Enter a selection that is between 1-4");
            }
            
        }while(transactionType == null);
        
        while(amountOfTransaction == 0)
        {
            long transactionAmount;
            while(true)
            {
                String input = "";
                try
                {
                    System.out.print("Please Enter the amount of the transaction as a positive whole number: ");
                    input = kb.next();
                    if (input.contains("."))
                    {
                        throw new Exception();
                    }
                    transactionAmount = Long.parseLong(input);;
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("\nYou entered: '" + input + "', which is not a whole number");
                }
            }

            if(transactionAmount > 0)
            {
                amountOfTransaction = transactionAmount;
            }
            else
            {
                System.out.println("\n You did not enter in a positive number, please enter a positive number");
            }                
        }
        
        Transaction transaction1 = new Transaction(transactionDate, transactionType, amountOfTransaction);
        boolean transactionAdded = account1.addTransaction(transaction1);
        
        if(!transactionAdded)
        {
            System.out.println("ERROR - transaction was not able to be added to the list of transactions");
        }
    }

    //method validateJavaDate derived from https://beginnersbook.com/2013/05/java-date-format-validation/
    private static boolean validateJavaDate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return true;
        }
        /* Date is not 'null' */
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM-dd-yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    private static void remove()
    {
        System.out.print("Enter the number of the transaction number that you want to remove (1 or greater): ");
        int removeNumber = kb.nextInt() - 1;
        boolean remove = account1.removeTransaction(removeNumber);
        if(!remove)
        {
            System.out.println("Transaction #" + (removeNumber + 1) + "was not able to be removed from your account");
        }
    }

    private static void removeAllTransactions()
    {
        account1.clearTransactions();
    }
    
    private static void displayAccountInformation()
    {
        System.out.println("\n======================================================================================");
        System.out.println("Below is your account information\n");
        System.out.println(account1.toString()); 
        System.out.println("\n======================================================================================\n");
    }
    
    private static void listAllTransactions()
    {
        System.out.println("\n=======================================================================================");
        System.out.println("Below is a list of all of your transactions\n");
        System.out.println(account1.getAllTransactions()); 
        System.out.println("\n======================================================================================\n");
    }
    
    private static void getBalance()
    {
        System.out.println("\nYour current balance is: " + account1.getCurrentBalance());
    }
}
