package domain;

import domain.interfaces.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robertkrook on 4/13/16.
 */
public class App implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users

    public static void main(String[] arg) {
        System.out.println("hi");
    }

    public String createUser(String email) {
        if(users.containsKey(email)) {
            return "";
        }
        User user = new User(email);
        users.put(email, user);
        return user.getEmail();
    }

    public String createAdmin(String email) {
        String user_email = createUser(email);
        User admin = users.get(user_email);
        admin.privileges();
        return user_email;
    }

    public void createCourse(String name, Admin admin) {

    }

    public boolean joinCourse(String generatedCode, User user) {
        return false;
    }

    public void matchRequest(int sender, int from, String generatedCode) {

    }

    public User getUser(String email) {
        return users.get(email); // returns null if no such user is present
    }

    public User[] getAllUsers(String generatedCode) {
        return new User[0];
    }

    public User[] getMatchedWithMe(int id) {
        return new User[0];
    }
}
