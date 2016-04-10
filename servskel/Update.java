import java.io.IOException;
import java.net.Socket;

/**
 * An example class of a class that handles requests of updating the database. This could
 * probably be done with even more segregation, making classes for updateProfile, updateMatches or whatever.
 * @author Robert
 *
 */
public class Update extends TopRequest implements Request, Runnable {

    public Update(Socket socket) {
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
        // handle the request Update
        
    }

}
