//Name Adam Schaible
//CS3750
//Homework # 9

/* copyrighted to Tomas Vilda.
 * http://stilius.net/java/java_ssl.php */

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class InfoCollectionClient {
    public static void main(String[] arstring) {

        System.setProperty("javax.net.ssl.trustStore", "3750truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "testtest");		
        try {
            Scanner theScanner = new Scanner(System.in);
            System.out.println("What is the ip/dns of the server, such as cs3750a.msudenver.edu : ");
            String ip = theScanner.nextLine();
            System.out.println("Enter the port number for the server, such as 5160: ");
            String port = theScanner.nextLine();
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslsocket = null;
            try {

                sslsocket = (SSLSocket) sslsocketfactory.createSocket(ip, Integer.parseInt(port)); //"cs3750a.msudenver.edu", 5160
                //SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("localhost", 9999);
            }
            catch (Exception e)
            {
                System.out.println("You entered either a incorrect ip/dns or a incorrect port");
            }
            SSLSession session = sslsocket.getSession();
            System.out.println("Peer host is " + session.getPeerHost());
            System.out.println("Cipher suite is " + session.getCipherSuite());
            System.out.println("Protocol is " + session.getProtocol());
            System.out.println("Session ID is " + new BigInteger(session.getId()));
            System.out.println("The creation time of the session is " + session.getCreationTime());
            System.out.println("The last accessed time of this session is " + session.getLastAccessedTime());


            OutputStream outputstream = sslsocket.getOutputStream(); //to do socket output
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter writingToServer = new BufferedWriter(outputstreamwriter);
			
			//listening to server information here from socket
            InputStream inputstream2 = sslsocket.getInputStream();
            InputStreamReader inputstreamreader2 = new InputStreamReader(inputstream2);
            BufferedReader readingFromServer = new BufferedReader(inputstreamreader2);
            String userName = "";
            BufferedWriter bufferedFileWriter = null;

            String string = null;
            while ((string = readingFromServer.readLine()) != null) {
                String response = "";

                switch (string) {
                    case "User Name: ":
                        System.out.println("User Name: ");
                        userName = theScanner.nextLine();
                        response = "User Name: " + userName;
                        bufferedFileWriter = new BufferedWriter(new FileWriter(userName + ".txt"));
                        bufferedFileWriter.write(response + "\n");
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "Full Name: ":
                        System.out.println("Full Name: ");
                        response = "Full Name: " + theScanner.nextLine();
                        bufferedFileWriter.write(response  + "\n");
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "Address: ":
                        System.out.println("Address: ");
                        response = "Address: " + theScanner.nextLine();
                        bufferedFileWriter.write(response  + "\n");
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "Phone number: ":
                        System.out.println("Phone number: ");
                        response = "Phone number: " + theScanner.nextLine();
                        bufferedFileWriter.write(response  + "\n");
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "Email address: ":
                        System.out.println("Email address: ");
                        response = "Email address: " + theScanner.nextLine();
                        bufferedFileWriter.write(response  + "\n");
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "close":
                        assert bufferedFileWriter != null;
                        bufferedFileWriter.close();
                        writingToServer.write("serverReadyForNextInput" + '\n');
                        writingToServer.flush();
                        break;
                    case "Add more users? (yes or any for no)":
                        System.out.println("Add more users? (yes or any for no)");
                        response = theScanner.nextLine();
                        writingToServer.write(response + '\n');
                        writingToServer.flush();
                        break;
                }


                //System.out.println(string);
                //System.out.flush(); //means not to wait and send output now
				
				//output response to message gotten
                //writingToServer.write(string + '\n');
                //writingToServer.flush();

                if (string.equals("Bye!"))
                    break;

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

