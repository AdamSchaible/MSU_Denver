//Name Adam Schaible
//CS3750
//Homework # 9


/* Copyrighted to Tomas Vilda
 * http://stilius.net/java/java_ssl.php */

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.math.BigInteger;

public class InfoCollectionServer {
    public static void main(String[] arstring) {

        System.setProperty("javax.net.ssl.keyStore", "3750keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "testtest");
        try {

            SSLServerSocketFactory sslserversocketfactory =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket =
                    (SSLServerSocket) sslserversocketfactory.createServerSocket(5160);
                    //(SSLServerSocket) sslserversocketfactory.createServerSocket(9999);

            while(true)
            {
            System.out.println("Waiting for a client");
            SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();
            //socket session information derived from http://www.java2s.com/Tutorial/Java/0490__Security/SSLServerSession.htm
            SSLSession session = sslsocket.getSession();
            System.out.println("Peer host is " + session.getPeerHost());
            System.out.println("Cipher suite is " + session.getCipherSuite());
            System.out.println("Protocol is " + session.getProtocol());
            System.out.println("Session ID is " + new BigInteger(session.getId()));
            System.out.println("The creation time of the session is " + session.getCreationTime());
            System.out.println("The last accessed time of this session is " + session.getLastAccessedTime());

            //listening to client information here
            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader readFromClient = new BufferedReader(inputstreamreader);

			//now the socket has a output stream, use buffered writer for this
			OutputStream outputstream = sslsocket.getOutputStream(); //to do socket output
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter writeToClient = new BufferedWriter(outputstreamwriter);
            boolean addUser = true;
            while(addUser) {
                writeToClient.write("User Name: " + '\n');
                writeToClient.flush();
                String message = null;
                if ((message = readFromClient.readLine()) != null) {
                    System.out.println("Response from server: " + message);
                }
                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("Full Name: " + '\n');
                writeToClient.flush();

                if ((message = readFromClient.readLine()) != null) {
                    System.out.println("Response from server: " + message);
                }
                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("Address: " + '\n');
                writeToClient.flush();

                if ((message = readFromClient.readLine()) != null) {
                    System.out.println("Response from server: " + message);
                }
                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("Phone number: " + '\n');
                writeToClient.flush();
                if ((message = readFromClient.readLine()) != null) {
                    System.out.println("Response from server: " + message);
                }
                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("Email address: " + '\n');
                writeToClient.flush();
                if ((message = readFromClient.readLine()) != null) {
                    System.out.println("Response from server: " + message);
                }
                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("close" + '\n');
                writeToClient.flush();

                while (!readFromClient.readLine().equals("serverReadyForNextInput")) {
                } //make program wait until server is done

                writeToClient.write("Add more users? (yes or any for no)" + '\n');
                writeToClient.flush();

                String addMoreUsers = null;
                if ((addMoreUsers = readFromClient.readLine()) != null) {
                    System.out.println("Server was told " + addMoreUsers + " by client in terms of adding more users");
                    if (!addMoreUsers.equals("yes"))
                    {
                        sslsocket.close();
                        addUser = false;
                        System.out.println("Socket is now closed out");
                    }
                }
            }}
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}