//Name Adam Schaible
//CS 3700
//2/9/20
//HW # 2

/*
 * Client App upon UDP
 * Weiying Zhu
 */ 

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        String serverLocation = "";
//        if (args.length != 1) {
//             System.out.println("Usage: java UDPClient <hostname>");
//             return;
//        }

            // create a UDP socket
        DatagramSocket udpSocket = new DatagramSocket();

        BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser = "";

        System.out.println("Enter the DNS or IP of the server: ");
        try
        {
            serverLocation = sysIn.readLine();
        }
        catch (Exception e)
        {
            System.out.println("Invalid DNS or IP");
        }
        boolean loopBack = true;
        List<String> validItemCodes = Arrays.asList("00001", "00002", "00003", "00004","00005","00006");
        List<String> itemDescription = Arrays.asList("New Inspiron 15", "New Inspiron 17", "New Inspiron 15R",
                "New Inspiron 15z Ultrabook","XPS 14 Ultrabook","New XPS 12 UltrabookXPS");
        while (loopBack)
        {
            boolean returnToMenu = true;
            while (returnToMenu)
            {
              System.out.println("Item ID\tItem Description");
              for (String code : validItemCodes)
              {
                  System.out.println(code + "\t" + itemDescription.get(validItemCodes.indexOf(code)));
              }
              System.out.println("\n Input the item ID: ");
              fromUser = sysIn.readLine();

              if (validItemCodes.contains(fromUser))
              {
                  returnToMenu = false;
              }
            }
          //fromUser = sysIn.readLine() != null;

			 
          // send request
          long start = System.currentTimeMillis();
          InetAddress address = InetAddress.getByName(serverLocation);
          byte[] buf = fromUser.getBytes();
          DatagramPacket udpPacket = new DatagramPacket(buf, buf.length, address, 5280);
          udpSocket.send(udpPacket);

          // get response
          byte[] buf2 = new byte[256];
          DatagramPacket udpPacket2 = new DatagramPacket(buf2, buf2.length);
          udpSocket.receive(udpPacket2);

          // display response
          fromServer = new String(udpPacket2.getData(), 0, udpPacket2.getLength());
          long end = System.currentTimeMillis();
          long RTTofQuery = end - start;
          System.out.println("Item ID\tItem Description          \tUnit Price\tInventory\tRTT of Query");
          System.out.println(fromServer + "\t" + RTTofQuery + "ms");

          System.out.println("Enter yes if you wish to continue, else enter anything else to not continue: ");
          fromUser = sysIn.readLine();
          if(!fromUser.equals("yes"))
          {
              loopBack = false;
          }

        }
		  
        udpSocket.close();
    }
}
