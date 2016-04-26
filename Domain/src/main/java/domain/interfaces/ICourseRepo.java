package domain.interfaces;

import domain.Admin;
import domain.Matched;
import domain.User;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public interface ICourseRepo {

    /**
     * @param gcode The gcode for the course
     * @return Admin for the course with the gcode
     */
    String getCourseAdmin(Gcode gcode);

    /**
     * @param gcode the gcode for the course
     * @return all users in the course with that gcode
     */
    List<String> getAllEnrolled(Gcode gcode);

    /**
     * @param admin admin for the new course
     * @param name name for the new course
     */
    void createCourse(String admin, String name);

    /**
     * @param gcode gcode for the course
     * @return All the matches in the course
     */
    List<Matched> getAllMatches(Gcode gcode);

    /**
     * @param toMatch All the users that should be matched together
     */
    void matchWith(List<String> toMatch, Gcode course);

    List<Gcode> getEnrolledIn(String email);

    List<Gcode> getAllAdministrating(String email);
}
