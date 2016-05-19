package domain.interfaces;

import domain.Course;
import domain.Matched;
import domain.User;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/21/16.
 */
public interface ICourse {

    /**
     * Creates a course
     * @param name name of the course
     * @param admin admin of the course
     * @return null if admin is not admin, else the generated code for the course
     */
    Gcode createCourse(String name, String admin);

    /**
     * Registeres the given user to the requested course
     * @param generatedCode the code of the course of which user wishes to register itself to
     * @param email the email of the user wishing to register to a course
     * @return false if the user is an admin, there is no such course or no such user, else true
     */
    boolean joinCourse(Gcode generatedCode, String email);

    /**
     * Sends a match request to the course corresponding to generatedCode, and matches them if both have said yes.
     * @param sender this user says he's willing to work with
     * @param receiver this user
     * @param generatedCode in this course
     */
    boolean matchRequest(String sender, String receiver, Gcode generatedCode);

    /**
     * Returns all users registered to the given coursecode.
     * @param generatedCode the generated code which is queried for a list of its registered users
     * @return an array of User if course has registered users and null if no such course exists
     */
    List<String> getAllUsers(Gcode generatedCode);

    /**
     * Returns all users the user associated with the email is matched with in the given course associated with generatedCode.
     * @param email the users email
     * @param generatedCode the generated coode associated with the course
     * @return an array containing all the users this user is matched with in the course
     */
    List<String> getMatchedWithMe(String email,Gcode generatedCode);

    /**
     * Returns the course information associated with the given courseCode
     * @param courseCode the code associated with the requested course
     * @return the course information
     */
    Course getCourse(Gcode courseCode);

    List<Gcode> getEnrolledIn(String email);

    List<Gcode> getAllAdministrating(String email);

    List<Course> getAllCourses();
}
