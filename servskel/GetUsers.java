import java.io.IOException;
import java.net.Socket;

/**
 * Just an example class of a request that queries the database for a list of users and pumps
 * that list out through the socket to the requesting client.
 * @author Robert
 *
 */
public class GetUsers extends TopRequest implements Request, Runnable {

    public GetUsers(Socket socket) {
        super.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() throws IOException {
        // handle the request GetUsers
    }

}
