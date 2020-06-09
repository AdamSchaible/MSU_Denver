
/**
 * The Transaction class will be used to keep track of the transactions
 * 
 * @author Adam Schaible
 * @version 4/28/17
 */
public class Transaction
{
    private String dateOfTransaction;
    private String typeOfTransaction;
    private int amountOfTransaction;

    public Transaction(String transactionDate, String transactionType, int transactionAmount)
    {
        dateOfTransaction = transactionDate;
        typeOfTransaction = transactionType;
        amountOfTransaction = transactionAmount;
    }

    public String getDateOfTheTransaction()
    {
        return dateOfTransaction;
    }
    
    public String getTypeOfTransaction()
    {
        return typeOfTransaction;
    }
    
    public int getTheAmountOfTheTransaction()
    {
        return amountOfTransaction;
    }
    
    public String toString()
    {
        String transaction = dateOfTransaction + " " + typeOfTransaction + " " + amountOfTransaction;
        return transaction;
    }
}
