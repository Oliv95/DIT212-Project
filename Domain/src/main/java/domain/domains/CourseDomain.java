package domain.domains;

import domain.Course;
import domain.User;
import domain.interfaces.ICourse;
import domain.interfaces.ICourseRepo;
import domain.util.Gcode;

/**
 * Created by oliv on 4/23/16.
 */
public class CourseDomain implements ICourse{
    private ICourseRepo repo;

    @Override
    public Gcode createCourse(String name, String admin) {
        return null;
    }

    @Override
    public boolean joinCourse(Gcode generatedCode, String email) {
        return false;
    }

    @Override
    public boolean matchRequest(String sender, String receiver, Gcode generatedCode) {
        return false;
    }

    @Override
    public User[] getAllUsers(Gcode generatedCode) {
        return new User[0];
    }

    @Override
    public User[] getMatchedWithMe(String email, Gcode generatedCode) {
        return new User[0];
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return null;
    }
}
