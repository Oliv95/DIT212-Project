import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Figures out what type of service is requested and creates the proper object to service that request.
 * @author Robert
 *
 */
public class RequestFactory implements Runnable {
    
    private Socket socket;
    
    public RequestFactory(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Process the request-line and create the type of Request specified by it.
        // If protocol is header-based like HTTP, the first header could say what type of service it is.
        // If header is perhaps "UPDATE", create new Update(socket); and then start that thread, where the 
        // request is processed. Since only the first line is pulled out of the socket the rest of the
        // request is still in the socket, and this data will be used to process the request.
        
        // If we want to implement more services all we have to do is write the new class and add another else-if here.
        try {
            // THIS IS JUST AN EXAMPLE
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = br.readLine();
            if(request.equals("Update")) {
                new Thread((new Update(socket))).start();
            } else if(request.equals("GetUsers")) {
                new Thread((new GetUsers(socket))).start();
            } else {
                // some default shit, like bad request in HTTP or something
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
