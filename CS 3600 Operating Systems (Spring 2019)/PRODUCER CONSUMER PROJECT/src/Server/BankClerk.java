import java.io.*;
import java.net.Socket;

/**
 * The BankClerk class creates thread that attends client requests
 */
public class BankClerk extends Thread{
    private Socket customer;        // A BankClerk socket to communicate with their customer
    private Bank bank;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * The constructor for BankClerk
     * @param customer is the socket assigned to a specific client
     * @param bank represents the current bank the miner is working in
     */
    public BankClerk(Socket customer, Bank bank){
        this.customer = customer;
        this.bank = bank;

        try {
            in = new BufferedReader(new InputStreamReader(customer.getInputStream()));
            out = new PrintWriter(customer.getOutputStream(), true);

        } catch (IOException IOEX){
            IOEX.printStackTrace();
        }
    }

    /**
     * BankClerk waits for a customer requests, takes gold from the vault, and reply to the client to "attend"
     * the customer. If there is no gold in the vault and there is no more miners, it tells the client that there
     * is nothing more to attend and closes the socket.
     */
    @Override
    public void run(){
        String customerName = "";

        try {
            do {
                customerName = in.readLine();     // Waits for message and then reads the next message from the client.
                                                  // The client sends their own name to make requests

                System.out.println("Withdrawal by " + customerName);

                if (bank.closeBank()){           // Checks if there are no more items in the bounded buffer
                    break;                       // and no more producers to produce more items,
                }                                //if no more then completes execution

                bank.takeGoldFromVault();        // Removes an item from the bounded buffer

                out.println("ATTENDED " + customerName);  // Sends a message to the client to simulate
                                                          // serving the customer

            } while (true);

            out.println("NO MORE GOLD");        // Informs client that there is no more to withdraw

            System.out.println("BANK-CLERK ATTENDING " + customerName + " ENDING SHIFT.");

            bank.bankClerkEndShift();        // Decrements the value representing the number
                                             // of BankClerks threads present in the Bank object
        } catch (IOException IOEX) {
            IOEX.printStackTrace();

        } finally {     // Closing the socket used to communicate with the client since there is no more to withdraw
            try {
                System.out.println("**BANK-CLERK IS DONE ATTENDING " + customerName + " **");
                customer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
