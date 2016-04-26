package domain;

import domain.interfaces.Domain;
import domain.util.Gcode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robertkrook on 4/13/16.
 */
public class App implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users
    Map<String, Admin> admins = new HashMap<>(); // All registered admins
    Map<Gcode, Course> courses = new HashMap<>();

    @Override
    public String createUser(String email, String name, String password) {
        if (users.containsKey(email) || admins.containsKey(email)) {
            return "";
        }
        User user = new User(email, name, password);
        users.put(email, user);
        return user.getEmail();
    }

    @Override
    public String createAdmin(String email, String name, String password) {
        if(admins.containsKey(email) || users.containsKey(email)) {
            return "";
        }
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
        if(admins.containsKey(email) || !(courses.containsKey(generatedCode))) { // admins can't join
            return false;
        } else if (users.containsKey(email)){ // User has to be registered
            courses.get(generatedCode).registerUser(users.get(email));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void matchRequest(String from, String to, Gcode generatedCode) {
        Course course = courses.get(generatedCode);
        course.putMatchRequest(from, to);
    }

    public User getUser(String email) {
        return users.get(email); // returns null if no such user is present
    }

    @Override
    public Admin getAdmin(String email) {
        return admins.get(email);
    }

    public User[] getAllUsers(Gcode generatedCode) {
        if(courses.containsKey(generatedCode)) {
            return courses.get(generatedCode).getRegistered();
        } else {
            return null;
        }
    }

    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        String[] emails = courses.get(generatedCode).getMatchedWith(email);
        User[] users = new User[emails.length];
        for(int i = 0; i < emails.length; i++) {
            users[i] = this.users.get(emails[i]);
        }
        return users;
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return courses.get(courseCode);
    }

}
