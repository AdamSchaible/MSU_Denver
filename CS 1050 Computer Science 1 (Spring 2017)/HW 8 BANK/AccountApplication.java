import java.util.Scanner;
/**
 * The main menu
 * 
 * @author Adam Schaible
 * @version 4/29/17
 */
public class AccountApplication
{
    private static Scanner kb = new Scanner(System.in).useDelimiter("\\n");
    private static Account account1;
    
    
    public static void main(String[] args)
    {
        boolean selectInput = true;
        String bankName = null;
        String typeOfAccount = null;
        String accountNumber = null;
        int initialBalance = -1;
                
        System.out.print("Enter the name of the bank: ");
        bankName = kb.next();
        
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
        
        System.out.print("\nPlease enter your account number: ");
        accountNumber = kb.next();
        
        while(initialBalance < 0)
        {
         System.out.print("\nEnter an initial balance for the account that is greater than 0: ");
         int amount = kb.nextInt();
         
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
        int amountOfTransaction = 0;
        
        System.out.print("\nEnter in the date of the transaction in the format of MM-DD-YYYY: ");
        String transactionDate = kb.next();
                
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
            System.out.print("Please Enter the amount of the transaction as a positive number: ");
            int transactionAmount = kb.nextInt();
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
        
        if(transactionAdded == false)
        {
            System.out.println("ERROR - transaction was not able to be added to the list of transactions");
        }
        
    }
    
    private static void remove()
    {
    System.out.print("Enter the number of the transaction number that you want to remove (1 or greater): ");
    int removeNumber = kb.nextInt() - 1;
    boolean remove = account1.removeTransaction(removeNumber);
    if(remove == false)
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
