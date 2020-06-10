import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * CustomerThread is the class creates consumers that make requests to the server consume items in the buffer
 */
public class CustomerThread extends Thread {
    private String name;            // Each unique CustomerThread is given a number based on the order it was issued
    private int goldCount = 0;      // and counter that keeps track of how much it has consumed

    /**
     * The constructor of CustomerThread.
     * @param number is the numerical order, it is generated inside the loop
     */
    public CustomerThread(int number){
        this.name = "Customer #" + number;
    }

    /**
     * Customers send requests to the server to take an item in the buffer, waits for a server
     * response for each request, incrementing goldCount for each fulfilled request, and waits between 2 to 5 seconds
     * before repeating steps. If the server responds with "NO MORE GOLD", the customer will display
     * goldCount before closing the socket to the server and finish its execution.
     */
    @Override
    public void run(){
        Socket socket = null;
        String message;

        try {
            socket = new Socket(Main.host, Main.port);  // Socket to the host IP and port number defined in Main

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            do {
                System.out.println(name + " ASKS FOR GOLD COINS");

                out.println(name);          // Sends the name of the CustomerThread to the server, this is a request to
                                            // take an item from the buffer

                message = in.readLine();    // Waits for response and reads the next server response

                if (message.equals("NO MORE GOLD")){    // If "NO MORE GOLD", breaks to exit
                    break;                              // the loop to complete execution
                }

                System.out.println(message);

                goldCount++;        // Increments number of items a CustomerThread consumed

                sleep((int) (Math.random() * 2000) + 3000);     // CustomerThreads pauses between 3 to 5 seconds
                                                                      // before they can request for more gold
            } while (true);

            System.out.println(name + " took " + goldCount + " coins of gold.");    // Displays the amount of items
                                                                                    // consumed when leaving the loop
        } catch (InterruptedException interruptedEX) {
            interruptedEX.printStackTrace();

        } catch (IOException IOEX) {
            IOEX.printStackTrace();

        } finally {    // Closes the socket used for sending requests to the server when done
            try {
                // System.out.println(name + **CLOSING THREAD SOCKET**");
                System.out.println(name + " NOW LEAVING THE BANK");
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}