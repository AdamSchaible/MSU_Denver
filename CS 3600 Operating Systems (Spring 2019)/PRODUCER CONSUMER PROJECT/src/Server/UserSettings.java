import java.io.*;

/**
 * The UserSettings class receives settings from the client - used locally.
 */
public class UserSettings implements Serializable{
    private int numCustomers;
    private int withdrawalAmount;
    private int vaultSize;
    private int numMiners;

    public int getNumCustomers() {
        return numCustomers;
    }
    public int getwithdrawalAmount() {
        return withdrawalAmount;
    }
    public int getvaultSize() {
        return vaultSize;
    }
    public int getNumMiners() {
        return numMiners;
    }
}
