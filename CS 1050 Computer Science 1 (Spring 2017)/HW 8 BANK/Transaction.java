
/**
 * The Transaction class will be used to keep track of the transactions
 * 
 * @author Adam Schaible
 * @version 6/29/20
 */
public class Transaction
{
    private String dateOfTransaction;
    private String typeOfTransaction;
    private long amountOfTransaction;

    public Transaction(String transactionDate, String transactionType, long transactionAmount)
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
    
    public long getTheAmountOfTheTransaction()
    {
        return amountOfTransaction;
    }
    
    public String toString()
    {
        String transaction = dateOfTransaction + " " + typeOfTransaction + " " + amountOfTransaction;
        return transaction;
    }
}
