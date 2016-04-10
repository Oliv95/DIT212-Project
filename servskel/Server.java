import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Code is really minimalistic right now. Just a skeleton.
 * @author Robert
 *
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Set port number
           int port = 0;

           // Establish the listening socket
           @SuppressWarnings("resource")
           ServerSocket serverSocket = new ServerSocket(port);
           System.out.println("Port number is: " + serverSocket.getLocalPort());

           while (true) {
               // Wait for connection
               Socket requestSocket = serverSocket.accept();
               requestSocket.setSoLinger(true, 5);

               // delegate as much work as possible to other threads so server can service other requests
               new Thread(new RequestFactory(requestSocket)).start();
           }
       }
}
