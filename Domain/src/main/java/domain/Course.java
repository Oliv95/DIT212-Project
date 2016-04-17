package domain;

import domain.util.Gcode;

import java.util.*;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 * Created by robertkrook on 4/13/16.
 */
public class Course {
    private Gcode code;
    private String name;
    private String admin; // email of admin

    private Map<String,User> listed;

    public Course(String name, String admin, Gcode gcode) {
        this.name = name;
        this.admin = admin;
        code = gcode;
        listed = new HashMap<>();
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
