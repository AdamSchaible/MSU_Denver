import java.io.Serializable;

/**
 * UserSettings class sets number of consumer and producer threads,
 * the size of the bounded buffer, and the maximum amount of items that will be produced
 * and consumed before the program finishes
 */
public class UserSettings implements Serializable{
    private int numCustomers;
    private int withdrawalAmount;       // Bounded buffer
    private int vaultSize;              // Max amount of items that will be produced/consumed
    private int numMiners;              // Number of MinerThreads

    /**
     * The constructor for UserSettings organizes a dialog with the user who enters the parameters for the simulation
     */
    public UserSettings(){

            // Number of customers (consumers)
            this.numCustomers = 5;

            // Amount for withdrawal (bounded buffer)
            this.withdrawalAmount = 1;

            // Size of a vault (amount produced/consumed before termination)
            this.vaultSize = 100;

            // Number of miners (producers)
            this.numMiners = 5;
    }

    /**
     * Returns the number of customers (consumer threads)
     * @return number of customers
     */
    public int getNumCustomers() {
        return numCustomers;
    }

    /**
     * Returns the amount of the withdrawal (bounded buffer)
     * @return the tray capacity
     */
    public int getwithdrawalAmount() {
        return withdrawalAmount;
    }

    /**
     * Returns the Gold available (maximum amount produced/consumed)
     * @return Gold available
     */
    public int getvaultSize() {
        return vaultSize;
    }

    /**
     * Returns the number of miners (producer threads)
     * @return the number of miners
     */
    public int getNumMiners() {
        return numMiners;
    }
}
