//Name Adam Schaible
//CS 3700
//2/9/20
//HW # 2

/*
 * Server App upon UDP
 * Weiying Zhu
 */ 
 
import java.io.*;
import java.net.*;
import java.util.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        List<String> validItemCodes = Arrays.asList("00001", "00002", "00003", "00004","00005","00006");
        List<String> additionalItemData = Arrays.asList("  New Inspiron 15           $379.99    \t157",
                "  New Inspiron 17           $449.99   \t128", "  New Inspiron 15R          $549.99   \t202",
                "  New Inspiron 15z Ultrabook$749.99   \t315","  XPS 14 Ultrabook          $999.99   \t261",
                "  New XPS 12 UltrabookXPS   $1199.99  \t178");
	    DatagramSocket udpServerSocket = null;
        BufferedReader in = null;
		  DatagramPacket udpPacket = null, udpPacket2 = null;
		  String fromClient = null, toClient = null;
        boolean morePackets = true;

		  byte[] buf = new byte[256];
	
		  udpServerSocket = new DatagramSocket(5280);
		  	  
        while (morePackets) {
            try {

                // receive UDP packet from client
                udpPacket = new DatagramPacket(buf, buf.length);
                udpServerSocket.receive(udpPacket);

					 fromClient = new String(
					 		udpPacket.getData(), 0, udpPacket.getLength());
							
					 // get the response
                     toClient = fromClient + "\t" + additionalItemData.get(validItemCodes.indexOf(fromClient));

					 
					 // send the response to the client at "address" and "port"
                InetAddress address = udpPacket.getAddress();
                int port = udpPacket.getPort();
					 byte[] buf2 = toClient.getBytes();
                udpPacket2 = new DatagramPacket(buf2, buf2.length, address, port);
                udpServerSocket.send(udpPacket2);
					 
            } catch (IOException e) {
                e.printStackTrace();
					 morePackets = false;
            }
        }
		  
        udpServerSocket.close();

    }
}
