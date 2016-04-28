package se.ice.client.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;


public class MockupServer implements Domain {

    Map<String, User> users = new HashMap<>(); // All registered users
    Map<String, Admin> admins = new HashMap<>(); // All registered admins
    Map<Gcode, Course> courses = new HashMap<>();

    private static Domain instance;

    public static Domain getInstance() {
        if(instance == null) {
            instance = new MockupServer();
            instance.init();
        }
        return instance;
    }

    public void init(){
        // Adding initial data to the server
        createAdmin("admin@mail.com","name","password");
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    @Override
    public boolean sendPartnerRequest(String generatedCourseCode, String fromEmail, String toEmail) {
        return false;
    }

    @Override
    public List<User> getPartnerRequest(String email, String generatedCourseCode) {
        return null;
    }

    @Override
    public boolean respondToPartner(String fromEmail, String toEmail, String generatedCourseCode, boolean response) {
        return false;
    }

    @Override
    public User getPartner(String email, String generatedCourseCode) {
        return null;
    }

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

    /**
     * Creates a course
     * @param name name of the course
     * @param admin admin of the course
     * @return null if admin is not admin, else the generated code for the course
     */
    @Override
    public Gcode createCourse(String name, String admin) {
        Gcode code = null;

        if (admins.get(admin) != null) {
            code = new Gcode();
            courses.put(code, new Course(name, admin, code));
        }

        return code;
    }

    /**
     * Registeres the given user to the requested course
     * @param generatedCode the code of the course of which user wishes to register itself to
     * @param email the email of the user wishing to register to a course
     * @return false if the user is an admin, there is no such course or no such user, else true
     */
    @Override
    public boolean joinCourse(String generatedCode, String email) {
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
    public boolean sendMatchRequest(String senderEmail, String receiverEmail, String generatedCourseCode) {
        return false;
    }

    @Override
    public List<User> getMatchedWith(String email, String generatedCourseCode) {
        return null;
    }

    /*
    @Override
    public void matchRequest(String sender, String from, Gcode generatedCode) {
        throw new UnsupportedOperationException();
    }
    */

    //have get Admin??
    public User getUser(String email) {
        return users.get(email); // returns null if no such user is present
    }

    @Override
    public List<User> getAllUsers(String generatedCourseCode) {
        return null;
    }

    @Override
    public List<User> getMatchedWithMe(String email, String generatedCourseCode) {
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        return admins.get(email);
    }
    /**
     * Returns all users registered to the given coursecode.
     * @param generatedCode the generated code which is queried for a list of its registered users
     * @return an array of User if course has registered users and null if no such course exists
     */
    public User[] getAllUsers(Gcode generatedCode) {
        if(courses.containsKey(generatedCode)) {
            return courses.get(generatedCode).getRegistered();
        } else {
            return null;
        }
    }

    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

}
