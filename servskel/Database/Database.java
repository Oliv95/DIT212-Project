package Database;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to handle concurrency when writing to and reading from our database (our txt files)
 * Several readers at once allowed, but only one writer allowed at once.
 * @author Robert
 *
 */
public class Database {

    /**
     * I don't know how we want to do this. Lets just assume for now we have a list of all the txt names for some reason.
     */
    ArrayList<String> files = new ArrayList<>();
    
    Lock lock = new ReentrantLock();
    Condition write = lock.newCondition();
    private boolean writing;
    private int waitingwriters = 0; // this is to give writers priority. If readers and writers got the same priority, just remove this.
    private int readers = 0;
    
    /**
     * Just an example. The variable request could represent instructions for how the database should be queried.
     * Could consist of headers as well, i don't know.
     */
    public String read(String request) throws InterruptedException {
        lock.lock();
        while(writing || (waitingwriters > 0)) { // if someone is writing or someone wants to write
            write.await(); // wait
        }
        readers++; // now you may read
        // ---------------------------------------------------------------
        // read
        // ---------------------------------------------------------------
        readers--; // now you are done reading
        write.signalAll(); // let any waiting writers know you are done
        lock.unlock();
        return null; // return result
    }
     /**
      * Same reasoning as above for the read method, about the string update (respectively request).
      */
    public void write(String update) throws InterruptedException {
        lock.lock();
        waitingwriters++; // queue up for writing
        while(writing || (readers > 0)) { // while someone is writing or there are still readers
            write.await(); // wait
        }
        waitingwriters--; // you are now writing, exit queue
        writing = true;
        // ---------------------------------------------------------------
        // write to the database
        // ---------------------------------------------------------------
        writing = false;
        write.signalAll();
        lock.unlock();
    }
}
