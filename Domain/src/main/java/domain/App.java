package domain;

import domain.interfaces.Domain;
import domain.util.Gcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robertkrook on 4/13/16.
 */
public class App implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users
    Map<String, Admin> admins = new HashMap<>(); // All registered admins

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

    public void createCourse(String name, String admin) {

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
}
