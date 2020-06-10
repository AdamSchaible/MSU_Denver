//Name Adam Schaible
//CS3700
//HW # 3



/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 */ 

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            do {

	       	String messageFromClient = "";
			String requestStatus = "";
			String fileName = "";
			String httpWithVersion = "";
			String fileData = "";
            fromClient = "";

            while ((fromClient = cSocketIn.readLine()) != null)
            {
                messageFromClient = messageFromClient + fromClient + "\r\n";

                if (fromClient.equals(""))
                {
                    break;
                }
            }

            System.out.println("The message that I got from the client: \n" + messageFromClient);

            String[] messageFromClientSplit = messageFromClient.split("\r\n");

            //Processing clients message
            for(String line:messageFromClientSplit)
            {
                String[] clientsLines = line.split(" ");

                if (line.contains("HTTP"))
                {
                    httpWithVersion = clientsLines[2];

                    if (!line.contains("GET"))
                    {
                        requestStatus = "400 Bad Request";
                    }
                    else
                    {
                        fileName = clientsLines[1].substring(1);
                    }
                }
            }

            //processing clients request
            if (requestStatus.equals(""))
            {
                try {
                    BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                    String line;
                    while ((line = fileReader.readLine()) != null)
                    {
                        fileData = fileData + line + "\r\n";
                    }
                    fileReader.close();
                }
                catch (Exception e)
                {
                    requestStatus = "404 Not Found";
                }
            }
            if (requestStatus.equals(""))
            {
                requestStatus = "200 OK";
            }

            //date code derived from https://mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            String outgoingMessage = httpWithVersion + " " + requestStatus + "\r\n" + "Date: " + dateFormat.format(date) + " MST" + "\r\n" + "Server: Somewhere out there" + "\r\n" + "" + "\r\n";

            if (requestStatus.equals("200 OK"))
            {
                outgoingMessage = outgoingMessage + fileData;
            }

            outgoingMessage = outgoingMessage + "" + "\r\n" + "" + "\r\n" + "" + "\r\n" + "" + "\r\n";

            System.out.println("\nWhat I am sending back to the client is: ");
            System.out.println(outgoingMessage);

            cSocketOut.println(outgoingMessage);


			cSocketIn.readLine(); //putting this here to make server wait until client is done processing, experimental
            } while (fromClient != null);

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
