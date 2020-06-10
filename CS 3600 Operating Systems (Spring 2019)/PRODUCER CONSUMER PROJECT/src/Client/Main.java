import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This application organizes a communication with the user who chooses the parameters that control the
 * simulation of events inside the bank. It handles the interactions that customers make with the bank clerk
 * (client side processing of consumer threads).
 *
 * @author Krzysztof Rabka and Adam Schaible
 */
public class Main {
    static InetAddress host;
    static final int port = 9999;

    /**
     * main() finds the host IP, creates a new instance of UserSettings, and calls initialize()
     */
    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {
            System.out.println("Can't find host address");
            System.exit(1);
        }

        UserSettings settings = new UserSettings();  // UserSettings object that will be sent to server

        initialize(settings);
    }

    /**
     * initialize() calls contactServer() and starts new customer threads
     * @param settings is an UserSettings object that has the values for the simulation
     */
    public static void initialize(UserSettings settings){
        contactServer(settings);

        for (int i = 0; i < settings.getNumCustomers(); i++){   // Utilize a for loop to create the defined
            new CustomerThread(i + 1).start();                  // amount of customer threads
        }
    }

    /**
     * contactServer() Contacts the server, sends an UserSettings object to the
     * server, and waits for the server response before closing the socket
     * @param settings is an UserSettings object that have the values for the simulation
     */
    public static void contactServer(UserSettings settings){
        Socket socket = null;

        try{
            socket = new Socket(host, port);    // Sends the socket to the host IP and port number

            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));

            ObjectOutputStream out = new ObjectOutputStream(            // Creates a new ObjectOutputStream to send
                                        socket.getOutputStream());      // UserSettings objects
            String response;

            System.out.println("Contacting server with settings...\n");

            out.writeObject(settings);                   // Sends "settings" to the server

            response = in.readLine();                          // Waits for response and reads the next server response
            System.out.println("SERVER> " + response + "\n");  // Displays server response

        } catch (IOException IOEX) {
            IOEX.printStackTrace();

        } finally {     // Closes socket used for sending UserSettings objects when done
            try {
                // System.out.println("**CLOSING SOCKET DESIGNATED FOR SENDING PARAMETERS**\n");
                System.out.println("***************************\n");
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
