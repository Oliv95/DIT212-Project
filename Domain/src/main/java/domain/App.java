package domain;

import domain.interfaces.Domain;
import domain.util.Gcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertkrook on 4/13/16.
 */
public class App implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users
    Map<String, Admin> admins = new HashMap<>(); // All registered admins
    Map<String, Course> courses = new HashMap<>();

    public static void main(String[] arg) {
        System.out.println("hi");
    }

    public String createUser(String email,String name,String password) {
        if(users.containsKey(email)) {
            return "";
        }
        User user = new User(email,name,password);
        users.put(email, user);
        return user.getEmail();
    }

    public String createAdmin(String email,String name,String password) {
        Admin admin = new Admin(password,name,email);
        admins.put(email,admin);
        return admin.getEmail();
    }

    public Gcode createCourse(String name, String admin) {
        //TODO proper course implementation
        Course course = new Course(name,admin);
        courses.put(name, course);
        return new Gcode();
    }

    public boolean joinCourse(Gcode generatedCode, String email) {
        return false;
    }

    public void matchRequest(String sender, String from, Gcode generatedCode) {

    }

    //have get Admin??
    public User getUser(String email) {
        return users.get(email); // returns null if no such user is present
    }

    public User[] getAllUsers(Gcode generatedCode) {
        return new User[0];
    }

    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        return new User[0];
    }

    @Override
    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

}
