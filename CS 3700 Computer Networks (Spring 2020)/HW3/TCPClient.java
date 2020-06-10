//Name Adam Schaible
//CS3700
//HW # 3

/*
 * Client App upon TCP
 *
 * Weiying Zhu
 *
 */ 

import java.io.*;
import java.net.*;

public class TCPClient
{
    public static void main(String[] args) throws IOException
    {
        Socket tcpSocket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;

        String dnsOrIp = "";
        BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.print("Enter the DNS name or IP of the server: ");
            dnsOrIp = systemInput.readLine();
            System.out.println("");

            long start = System.currentTimeMillis();
            tcpSocket = new Socket(dnsOrIp, 5280);
            long end = System.currentTimeMillis();
            System.out.println("RTT of establishing this connection is : " + (end-start) + " milliseconds");

            socketOut = new PrintWriter(tcpSocket.getOutputStream(),true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.err.println("Don't know about host: " + dnsOrIp);
            System.exit(1);
        }
        catch (IOException e)
        {
            System.err.println("Couldn't get I/O for the connection to: "  + dnsOrIp);
            System.exit(1);
        }

        String fromServer;
        String fromUser;

        boolean getMoreResponses = true;

        while (getMoreResponses)
        {
            System.out.println("Input the http method type: ");
            String httpMethodType = systemInput.readLine();

            System.out.println("Input the name of the htm file (such as CS3700.htm): ");
            String htmFileName = systemInput.readLine();

            System.out.println("Input the http version: ");
            String httpVersion = systemInput.readLine();

            System.out.println("Input the User-Agent: ");
            String userAgent = systemInput.readLine();

            String outBoundMessage = httpMethodType + " /" + htmFileName + " " + "HTTP/" + httpVersion + "\r\n" +
                    "Host: " + dnsOrIp + "\r\n" + "User-Agent: " + userAgent + "\r\n" + "" + "\r\n";

            System.out.println("\nMessage that I am sending to the server is: \n" + outBoundMessage);
            try {
                String serverData = "";
                long start = System.currentTimeMillis();
                socketOut.println(outBoundMessage);


                while ((fromServer = socketIn.readLine()) != null)
                {
                    serverData = serverData + fromServer + "\r\n";
                    if (serverData.contains("\r\n\r\n\r\n\r\n"))
                    {
                        break;
                    }
                }
                long end = System.currentTimeMillis();

                System.out.println("What I got back from the server is: \n" + serverData);
                System.out.println("RTT of HTTP query: " + (end-start) + " milliseconds");
                String[] messageFromServerLines = serverData.split("\r\n");

                boolean hitHtmlData = false;
                String htmlMessageToSaveToFile = "";
                boolean twoHundredOk = false;
                String dataBeforeAnyHtml = "";


                for(String line:messageFromServerLines)
                {
                    if(line.contains("200 OK"))
                    {
                        twoHundredOk = true;
                    }

                    if (!hitHtmlData)
                    {
                        dataBeforeAnyHtml = dataBeforeAnyHtml + line + "\r\n";
                    }

                    if (line.equals("") && twoHundredOk)
                    {
                        hitHtmlData = true;
                    }
                }

                if (!twoHundredOk)
                {
                    System.out.println("NO HTML DATA TO BE SAVED AS FILE");
                }
                System.out.println("The header and status lines sent from server: \n" + dataBeforeAnyHtml);


                if (twoHundredOk)
                {
                    htmlMessageToSaveToFile = serverData.replace(dataBeforeAnyHtml,"");
                    String[] splitDataFromFourBlankLines = htmlMessageToSaveToFile.split("\r\n\r\n\r\n\r\n", 2);
                    String htmlData = splitDataFromFourBlankLines[0] + "\r\n";
                    System.out.println("\nHTML DATA THAT WILL BE SAVED AS FILE\n" + htmlData);

                    //left off here
                    PrintWriter printHtmlFile = new PrintWriter(htmFileName);

                    String[] htmlDataArray = htmlData.split("\r\n");
                    for(String lines: htmlDataArray)
                    {
                        printHtmlFile.println(lines);
                    }

                    printHtmlFile.close();
                }
            }
            catch (Exception e)
            {
                System.out.println("Server replies nothing!");
                getMoreResponses = false;
            }

            System.out.println("If you would like to continue enter 'yes', otherwise enter anything else for no : ");
            fromUser = systemInput.readLine();

            if (!fromUser.equals("yes"))
            {
                getMoreResponses = false;
            }
            //added commented code below for testing
            socketOut.println("DONE WITH ONE REQUEST");  //done making server wait
            //socketOut.flush();
        }
        //socketOut.print("Bye"); //added line

        socketOut.close();
        socketIn.close();
        systemInput.close();
        tcpSocket.close();
    }
}
