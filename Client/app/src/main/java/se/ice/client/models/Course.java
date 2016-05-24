package se.ice.client.models;
import java.util.*;

import se.ice.client.models.User;
import se.ice.client.utility.Gcode;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 */
public class Course {
    private Gcode code;
    private String name;
    private String admin; // email of admin

    private Map<String,User> listed;

    public Course(){}

    public Course(String name, String admin, Gcode gcode) {
        this.name = name;
        this.admin = admin;
        code = gcode;
        listed = new HashMap<>();
    }

    public String getName(){
        return new String(name);
    }

    public Gcode getCode() {
        return code;
    }

    public void registerUser(User user) {
        listed.put(user.getEmail(), user);
    }

    public User[] getRegistered() {
        Collection<User> users = listed.values();
        return users.toArray(new User[users.size()]);
    }

    public String getAdmin() {
        return admin;
    }
}
