/**
 * The Bank class creates objects that are the monitor.
 * The synchronized methods and shared variables are in the Bank object.
 */
public class Bank {
    private int withdrawalAmount;      // Bounded buffer
    private int vaultSize;             // Max amount produced/consumed
    private int miners;                // Number of producer threads (MinerThreads)
    private int bankClerk;             // Number of BankClerk threads for each customer
    private int amountToWithdraw = 0;  // Initial number of items present in the bounded buffer

    /**
     * The constructor of the Bank
     * @param withdrawalAmount the limit of the bounded buffer
     * @param vaultSize maximum amount produced/consumed
     * @param miners number of miners
     * @param numCustomers number of customers
     */
    public Bank(int withdrawalAmount, int vaultSize, int miners, int numCustomers){
        this.withdrawalAmount = withdrawalAmount;
        this.vaultSize = vaultSize;
        this.miners = miners;
        this.bankClerk = numCustomers;
    }

    /**
     * Synchronized method for MinerThreads to take gold for a deposit.
     * @return TRUE if a miner is able to take gold, else return FALSE
     */
    public synchronized boolean takeGold(){
        if (vaultSize > 0) {
            vaultSize--;
            return true;

        } else {
            return false;
        }
    }

    /**
     * Synchronized method for MinerThreads to deposit gold in the vault.
     * A miner waits to deposit the gold if the vault is full.
     */
    public synchronized void depositGold(){
        try {
            while (amountToWithdraw >= withdrawalAmount) {
                wait();
            }

            amountToWithdraw++;

            notify();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Synchronized method for bankClerk to withdraw gold from vault to customers.
     * A bankClerk waits to take gold from the vault if the vault is empty.
     */
    public synchronized void takeGoldFromVault(){
        try {
            while (amountToWithdraw <= 0){
                wait();
            }

            amountToWithdraw--;

            notify();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Synchronized method for a miner to rest when completed work.
     * Decrements the number of miners in the bank to signal that they are resting.
     */
    public synchronized void minerEndShift(){
        miners--;
        System.out.println(miners + " MINERS REMAINING");
    }

    /**
     * Synchronized method for a bankClerk to clock out when they have completed their work.
     * Decrements the number of Bank Clerks in the bank to signal that they are leaving.
     */
    public synchronized void bankClerkEndShift(){
        bankClerk--;
        System.out.println(bankClerk + " BANK CLERKS REMAINING");

    }

    /**
     * Synchronized method for BankClerk to check if they can leave their work.
     * @return TRUE if gold present or if a miner is present, else return FALSE
     */
    public synchronized boolean closeBank(){
        if (miners == 0 && amountToWithdraw == 0){
            return true;

        } else {
            return false;
        }
    }
}
