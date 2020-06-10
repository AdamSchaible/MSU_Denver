import java.io.*;
import java.net.*;

/**
 * This program is an implementation of a server that takes a UserSettings object to generate the required
 * amount of producer threads and sockets values inside the object, handles the interactions
 * that the bank staff members make with the customers and with objects present inside the bank simulation
 * (server side processing of producer threads).
 *
 * @author Krzysztof Rabka and Adam Schaible
 */
public class Main {
    private static ServerSocket serverSocket;
    private static final int port = 9999;
    private static Bank bank;

    /**
     * main opens a server socket with a port number and calls initialize()
     */
    public static void main(String[] args) {
        System.out.println("Opening port " + port + "...\n");

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            System.out.println("Can't attach to port");
            System.exit(1);
        }

        initialize();
    }

    /**
     * initialize() takes UserSettings object from the client, replies to the client,
     * verifies reception, creates a Bank object with parameters from the UserSettings object,
     * creates amount of MinerThreads (producers) and BankClerk (threads for serving consumers)
     */
    public static void initialize() {
        Socket socket = null;
        int numCustomers = 0;
        int withdrawalAmount = 0;
        int vaultSize = 0;
        int numMiners = 0;

        try {
            System.out.println("Waiting for parameters from client...\n");

            socket = serverSocket.accept();     // Establish a socket from next message server socket

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());  //ObjectInputStream for the
                                                                                    //UserSettings object from client

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            UserSettings settings = (UserSettings) in.readObject(); // Creates a UserSettings object locally from
                                                                    // the UserSettings object received from client
                                                                    // to access getters to set values below
            numCustomers = settings.getNumCustomers();
            withdrawalAmount = settings.getwithdrawalAmount();
            vaultSize = settings.getvaultSize();
            numMiners = settings.getNumMiners();

            out.println("***CONTACT CREATED*** "
                    + "Initial Parameters: "
                    + "number of customers = " + numCustomers
                    + ", withdrawal amount = " + withdrawalAmount
                    + ", vault size = " + vaultSize
                    + ", number of miners = " + numMiners + ".");

            System.out.println("Parameters received, starting...\n");

        } catch (IOException IOEX) {
            IOEX.printStackTrace();

        } catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();

        } finally {     //close this socket used for receiving UserSetting objects since it is no longer required
            try {
                System.out.println("**CLOSING SOCKET THAT RECEIVES PARAMETERS**");
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bank = new Bank(withdrawalAmount,
                        vaultSize,
                        numMiners,
                        numCustomers);

        for (int i = 0; i < numMiners; i++) {       // Creates specified amount of MinerThreads
            new MinerThread(i + 1, bank).start();
        }

        for (int i = 0; i < numCustomers; i++) {
            newCustomer();
        }
    }

    /**
     * newCustomer() assigns a new BankClerk thread for each customer
     */
    private static void newCustomer(){
        try {
            Socket customer = serverSocket.accept();    // Socket from the first message from a customer thread
                                                        // on the client side
            System.out.println("\n**New customer accepted**");

            BankClerk bankClerk = new BankClerk(customer, bank);     //create a new BankClerk thread with
            // the socket and data present in the
                                                                            //Bank object named "bank"
            bankClerk.start();           // Starts the new thread
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}