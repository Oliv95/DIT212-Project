package se.ice.client.utility;

/**
 * Created by Simon on 2016-04-13.
 */
public class MockDataRequest implements Domain {
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
