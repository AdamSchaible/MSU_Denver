//Name Adam Schaible
//CS3700
//HW # 4

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
        BufferedReader socketIn = null;
        PrintWriter socketOut = null;

        String dnsOrIp = "";
        BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.print("Enter in the DNS name or IP of the server: ");
            dnsOrIp = systemInput.readLine();
            System.out.println("");

            tcpSocket = new Socket(dnsOrIp, 5280);

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

        fromServer = socketIn.readLine();
        System.out.println("The 220 response from the server is: " + fromServer + "\n");

        boolean getMoreResponses = true;

        while (getMoreResponses)
        {
            System.out.println("Senders email address: ");
            String sendersEmailAddress = systemInput.readLine();

            System.out.println("Receivers email address: ");
            String receiversEmailAddress = systemInput.readLine();

            System.out.println("Subject: ");
            String subject = systemInput.readLine();

            System.out.println("Enter email contents line by line, with the last line containing only a period: \n");
            String emailContents = "";
            boolean stillGettingContentLines = true;

            while(stillGettingContentLines)
            {
                String temp = systemInput.readLine();
                emailContents = emailContents + "\r\n" + temp;
                if (temp.equals("."))
                {
                    stillGettingContentLines = false;
                }
            }

            System.out.println("Sending the following to the server: HELO " + dnsOrIp + "\n");
            long start = System.nanoTime();
            socketOut.println("HELO " + dnsOrIp);
            fromServer = socketIn.readLine();
            long end = System.nanoTime();
            float millisecondTime = (float)(end - start)/1000000;
            System.out.println("The servers response to (HELO " + dnsOrIp + ") is: " + "\n" + fromServer);
            System.out.println("The amount of time to get response from last message is : " + millisecondTime + " ms" + "\n");

            System.out.println("Sending the following to the server: MAIL FROM: " + sendersEmailAddress + "\n");
            start = System.nanoTime();
            socketOut.println("MAIL FROM: " + sendersEmailAddress);
            fromServer = socketIn.readLine();
            end = System.nanoTime();
            millisecondTime = (float)(end - start)/1000000;
            System.out.println("The servers response to (MAIL FROM: " + sendersEmailAddress + ") is: " + "\n" + fromServer);
            System.out.println("The amount of time to get response from last message is : " + millisecondTime + " ms" + "\n");

            System.out.println("Sending the following to the server: RCPT TO: " + receiversEmailAddress + "\n");
            start = System.nanoTime();
            socketOut.println("RCPT TO: " + receiversEmailAddress);
            fromServer = socketIn.readLine();
            end = System.nanoTime();
            millisecondTime = (float)(end - start)/1000000;
            System.out.println("The servers response to (RCPT TO: " + receiversEmailAddress + ") is: " + "\n" + fromServer);
            System.out.println("The amount of time to get response from last message is : " + millisecondTime + " ms" + "\n");

            System.out.println("Sending the following to the server: DATA" + "\n");
            start = System.nanoTime();
            socketOut.println("DATA");
            fromServer = socketIn.readLine();
            end = System.nanoTime();
            millisecondTime = (float)(end - start)/1000000;
            System.out.println("The servers response to DATA is: " + "\n" + fromServer);
            System.out.println("The amount of time to get response from last message is : " + millisecondTime + " ms" + "\n");

            String mailMessage = "To: " + receiversEmailAddress + "\r\n" +
                    "From: " + sendersEmailAddress + "\r\n" +
                    "Subject: " + subject + "\r\n" +
                    "" + "\r\n" +
                    emailContents;
            System.out.println("Sending following mail message to the server : \n" + mailMessage + "\n");
            start = System.nanoTime();
            socketOut.println(mailMessage);
            fromServer = socketIn.readLine();
            end = System.nanoTime();
            millisecondTime = (float)(end - start)/1000000;
            System.out.println("The servers response to the mail message is: " + "\n" + fromServer);
            System.out.println("The amount of time to get response from last message is : " + millisecondTime + " ms" + "\n");

            System.out.println("If you would like to continue enter 'yes', otherwise enter anything else for no : ");
            fromUser = systemInput.readLine();

            if (!fromUser.equals("yes"))
            {
                System.out.println("Sending QUIT command to server");
                socketOut.println("QUIT");
                fromServer = socketIn.readLine();
                System.out.println("Server's response to Quit command is: " + fromServer);
                getMoreResponses = false;
            }
            else
            {
                socketOut.println("continuing");
                System.out.println("Did not tell server to quit");
            }
            socketIn.readLine(); //experimental
            //may want to send something else here to make program wait before redoing loop

        }
        //socketOut.print("Bye"); //added line

        socketOut.close();
        socketIn.close();
        systemInput.close();
        tcpSocket.close();
    }
}
