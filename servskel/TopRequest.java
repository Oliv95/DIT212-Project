import java.net.Socket;

import Database.Database;

/**
 * To eliminate as much repetition as possible, everything that all or most requests have in common
 * should be placed in an abstract superclass. For example all requests must have a socket.
 * 
 * Class is abstract since this class alone is not smart enough to handle a request. Should not
 * be able to instantiate this class.
 * @author Robert
 *
 */
public abstract class TopRequest {
    protected Socket socket;
    protected static Database database;
}
