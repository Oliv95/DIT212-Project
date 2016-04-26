package domain.interfaces;

import domain.Admin;
import domain.Course;
import domain.User;
import domain.util.Gcode;

public interface Domain {

    /**
     * Creates a user and registers it in the domain
     * @param email email of the user
     * @param name name of the user
     * @param password users password
     * @return the registered email associated with this user
     */
    public String createUser(String email,String name,String password);

    /**
     * Creates an admin and registers it in the domain
     * @param email email of the admin
     * @param name name of the admin
     * @param password admins password
     * @return the registered email associated with this admin
     */
    public String createAdmin(String email,String name,String password);

    /**
     * Creates a course
     * @param name name of the course
     * @param admin admin of the course
     * @return null if admin is not admin, else the generated code for the course
     */
    public Gcode createCourse(String name, String admin);

    /**
     * Registeres the given user to the requested course
     * @param generatedCode the code of the course of which user wishes to register itself to
     * @param email the email of the user wishing to register to a course
     * @return false if the user is an admin, there is no such course or no such user, else true
     */
    public boolean joinCourse(Gcode generatedCode, String email);

    /**
     * Sends a match request to the course corresponding to generatedCode, and matches them if both have said yes.
     * @param from this user says he's willing to work with
     * @param to this user
     * @param generatedCode in this course
     */
    public void matchRequest(String from, String to, Gcode generatedCode);

    /**
     * Returns the user object associated with this email
     * @param email the requested users email
     * @return the requested user
     */
    public User getUser(String email);

    /**
     * Returns all users registered to the given coursecode.
     * @param generatedCode the generated code which is queried for a list of its registered users
     * @return an array of User if course has registered users and null if no such course exists
     */
    public User[] getAllUsers(Gcode generatedCode);

    /**
     * Returns all users the user associated with the email is matched with in the given course associated with generatedCode.
     * @param email the users email
     * @param generatedCode the generated coode associated with the course
     * @return an array containing all the users this user is matched with in the course
     */
    public User[] getMatchedWithMe(String email,Gcode generatedCode);

    /**
     * Returns the course object associated with the given courseCode
     * @param courseCode the code associated with the requested course
     * @return the course object
     */
    public Course getCourse(Gcode courseCode);

    /**
     * Returns the admin object associated with this email
     * @param email the requested admins email
     * @return
     */
    public Admin getAdmin(String email);
}
