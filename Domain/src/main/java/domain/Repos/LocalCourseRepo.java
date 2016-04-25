package domain.Repos;

import domain.Admin;
import domain.Matched;
import domain.User;
import domain.interfaces.ICourseRepo;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class LocalCourseRepo implements ICourseRepo{

    @Override
    public Admin getCourseAdmin(Gcode gcode) {
        return null;
    }

    @Override
    public List<User> getAllEnrolled(Gcode gcode) {
        return null;
    }

    @Override
    public void createCourse(Admin admin, String name) {

    }

    @Override
    public List<Matched> getAllMatches(Gcode gcode) {
        return null;
    }

    @Override
    public void matchWith(List<String> toMatch) {
    }
}
