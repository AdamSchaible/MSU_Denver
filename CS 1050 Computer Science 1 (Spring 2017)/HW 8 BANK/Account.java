import java.util.ArrayList;

/**
 * Account class will keep track of the accounts
 * 
 * @author Adam Schaible
 * @version 4/29/17
 */
public class Account
{
    private String bankName;
    private String typeOfAccount;
    private String accountNumber;
    private int initialBalance;
    private ArrayList<Transaction> listOfTransactions;
    
    public Account(String nameOfThebank, String accountType, String accountNumber, int initialBalance)
    {
        bankName = nameOfThebank;
        typeOfAccount = accountType;
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance;
        listOfTransactions = new ArrayList <Transaction>();
        
    }

    public String getBankName()
    {
        return bankName;
    }
    
    public String getTypeOfAccount()
    {
        return typeOfAccount;
    }
    
    public String getAccountNumber()
    {
        return accountNumber;
    }
    
    public int getInitialBalance()
    {
        return initialBalance;
    }
    
    public boolean addTransaction(Transaction name)
    {
        boolean result = false;
        result = listOfTransactions.add(name);
        return result;
    }
    
    public void clearTransactions()
    {
        listOfTransactions.clear();
    }
    
    public boolean removeTransaction(int indexNumber)
    {
        boolean result = false;
        int numberOfTransactionsBeforeRemoval = listOfTransactions.size(); 
        listOfTransactions.remove(indexNumber);
        int numberOfTransactionsAfterRemoval = listOfTransactions.size();
        
        if(numberOfTransactionsBeforeRemoval > numberOfTransactionsAfterRemoval)
        {
            result = true;
        }
        return result;            
    }
        
    public String getAllTransactions()
    {   
        String summary = "";
        for(int i = 0;listOfTransactions.size() > i;i++)
        {
            summary = summary + listOfTransactions.get(i).toString() + System.lineSeparator();
        }
        return summary;
    }
    
    public String toString()
    {
     String accountInfo = bankName + " " + accountNumber + " " + typeOfAccount; 
     return accountInfo;
    }  
    
    public int getCurrentBalance()
    {
        int currentBalance = initialBalance;
        
        for(int i = 0;listOfTransactions.size() > i;i++)
        {
            switch(listOfTransactions.get(i).getTypeOfTransaction())
            {
                case "deposit":
                currentBalance += listOfTransactions.get(i).getTheAmountOfTheTransaction();
                break;
                
                case "withdrawal":
                currentBalance -= listOfTransactions.get(i).getTheAmountOfTheTransaction();
                break;
                
                case "interest payment":
                currentBalance += listOfTransactions.get(i).getTheAmountOfTheTransaction();
                break;
                
                case "service charge":
                currentBalance -= listOfTransactions.get(i).getTheAmountOfTheTransaction();
                break;
                
                default:
                break;
            }
        }
        return currentBalance;
    }   
        
    
}
