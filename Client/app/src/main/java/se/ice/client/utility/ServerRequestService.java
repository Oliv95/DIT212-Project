package se.ice.client.utility;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public class ServerRequestService extends Service implements Domain {
    public ServerRequestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String createUser(String email, String name, String password) {
        return null;
    }

    @Override
    public String createAdmin(String email, String name, String password) {
        return null;
    }

    @Override
    public Gcode createCourse(String name, String admin) {
        return null;
    }

    @Override
    public boolean joinCourse(Gcode generatedCode, String user) {
        return false;
    }

    @Override
    public void matchRequest(String sender, String receiver, Gcode generatedCode) {

    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public User[] getAllUsers(Gcode generatedCode) {
        return new User[0];
    }

    @Override
    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        return new User[0];
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        return null;
    }
}
