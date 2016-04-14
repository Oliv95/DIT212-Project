package domain;

import domain.interfaces.Domain;
import domain.util.Gcode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertkrook on 4/13/16.
 */
public class App implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users
    Map<String, Admin> admins = new HashMap<>(); // All registered admins
    Map<Gcode, Course> courses = new HashMap<>();

    public static void main(String[] arg) {
        System.out.println("hi");
    }

    @Override
    public String createUser(String email, String name, String password) {
        if (users.containsKey(email)) {
            return "";
        }
        User user = new User(email, name, password);
        users.put(email, user);
        return user.getEmail();
    }

    @Override
    public String createAdmin(String email, String name, String password) {
        Admin admin = new Admin(email, name, password);
        admins.put(email, admin);
        return admin.getEmail();
    }

    @Override
    public Gcode createCourse(String name, String admin) {
        Gcode code = null;

        if (admins.get(admin) != null) {
            code = new Gcode();
            courses.put(code, new Course(name, admin, code));
        }

        return code;
   }

    @Override
    public boolean joinCourse(Gcode generatedCode, String email) {
        throw new NotImplementedException();
    }

    @Override
    public void matchRequest(String sender, String from, Gcode generatedCode) {
        throw new NotImplementedException();
    }


    //have get Admin??
    public User getUser(String email) {
        return users.get(email); // returns null if no such user is present
    }

    public User[] getAllUsers(Gcode generatedCode) {
        throw new NotImplementedException();
    }

    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        throw new NotImplementedException();
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return courses.get(courseCode);
    }

    @Override
    public Admin getAdmin(String email) {
        return admins.get(email);
    }

}
