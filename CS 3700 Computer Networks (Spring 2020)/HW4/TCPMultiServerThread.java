//Name Adam Schaible
//CS3700
//HW # 4

/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 */ 

import java.net.*;
import java.io.*;
import java.net.InetAddress;

public class TCPMultiServerThread extends Thread
{
    private Socket clientTCPSocket = null;

    public TCPMultiServerThread(Socket socket)
    {
		super("TCPMultiServerThread");
		clientTCPSocket = socket;
    }

    public void run()
	{

		try {


			PrintWriter cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
	  		BufferedReader cSocketIn = new BufferedReader(new InputStreamReader(clientTCPSocket.getInputStream()));

            String fromClient = "";
            boolean keepLooping = true;
            do {
            fromClient = "";

            cSocketOut.println("220 cs3700a@msudenver.edu");

            //part 3 with sections a & b
            boolean loop = true;
            while (loop)
            {
                fromClient = cSocketIn.readLine();
                System.out.println("Received this message from client: " + fromClient);
                String[] parts = fromClient.split(" ");

                if (parts[0].equals("HELO"))
                {
                    InetAddress iPAddress = InetAddress.getLocalHost();
                    String response = "250 " + iPAddress.getHostAddress() + " Hello " + clientTCPSocket.getRemoteSocketAddress().toString().substring(1);
                    cSocketOut.println(response);
                    System.out.println("Response sent to STMP client: " + response);
                    loop = false;
                }
                else
                {
                    cSocketOut.println("503 5.5.2 Send hello first");
                    System.out.println("Response sent to STMP client: 503 5.5.2 Send hello first");
                }
            }

            //part 3 with section c and d
            loop = true;
            while (loop)
            {
                fromClient = cSocketIn.readLine();
                System.out.println("Received this message from STMP client: " + fromClient);
                String[] parts = fromClient.split(":");

                if (parts[0].equals("MAIL FROM") && fromClient.contains(":"))
                {
                    cSocketOut.println("250 2.1.0 Sender OK");
                    System.out.println("Response sent to STMP client: 250 2.1.0 Sender OK");
                    loop = false;
                }
                else
                {
                    cSocketOut.println("503 5.5.2 Need mail command");
                    System.out.println("Response sent to STMP client:  Need mail command");
                }
            }

            //part 3 with section e and f
            loop = true;
            while (loop)
            {
                fromClient = cSocketIn.readLine();
                System.out.println("Received this message from STMP client: " + fromClient);
                String[] parts = fromClient.split(":");

                if (parts[0].equals("RCPT TO") && fromClient.contains(":"))
                {
                    cSocketOut.println("250 2.1.5 Recipient OK");
                    System.out.println("Response sent to STMP client: 250 2.1.5 Recipient OK");
                    loop = false;
                }
                else
                {
                    cSocketOut.println("503 5.5.2 Need rcpt command");
                    System.out.println("Response sent to STMP client:  503 5.5.2 Need rcpt command");
                }
            }

            //part 3 with section g and h
            loop = true;
            while (loop)
            {
                fromClient = cSocketIn.readLine();
                System.out.println("Received this message from STMP client: " + fromClient);
                if (fromClient.equals("DATA"))
                {
                    cSocketOut.println("354 Start mail input; end with <CRLF>.<CRLF>");
                    System.out.println("Response sent to STMP client: 354 Start mail input; end with <CRLF>.<CRLF>");
                    loop = false;
                }
                else
                {
                    cSocketOut.println("503 5.5.2 Need data command");
                    System.out.println("Response sent to STMP client:  503 5.5.2 Need rcpt command");
                }
            }

            //part 3. section i
            System.out.println("The mail message from the STMP client is: ");
            while ((fromClient = cSocketIn.readLine()) != null)
            {
                if (fromClient.equals("."))
                {
                    break;
                }
                System.out.println(fromClient);
            }
            //part 3. section j
            cSocketOut.println("250 Message received and to be delivered");
            System.out.println("Response sent to STMP client:  250 Message received and to be delivered");


            //part 4
            if (cSocketIn.readLine().equals("QUIT"))
            {
                keepLooping = false;
                InetAddress iPAddress = InetAddress.getLocalHost();
                cSocketOut.println("221 " + iPAddress.getHostAddress() + " closing connection");
                System.out.println("221 " + iPAddress.getHostAddress() + " closing connection");
            }
            //possibly putting line here to make server wait until client is done processing, experimental

            } while (fromClient != null && keepLooping);

			cSocketOut.close();
			cSocketIn.close();
			clientTCPSocket.close();

		}
		catch (IOException e)
        {
		    e.printStackTrace();
		}
    }
}
