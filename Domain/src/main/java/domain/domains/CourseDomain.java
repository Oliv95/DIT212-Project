package domain.domains;

import domain.Course;
import domain.User;
import domain.interfaces.ICourse;
import domain.interfaces.ICourseRepo;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class CourseDomain implements ICourse{

    private ICourseRepo repo;

    public CourseDomain(ICourseRepo repo){
        this.repo = repo;
    }

    @Override
    public Gcode createCourse(String name, String admin) {
        return repo.createCourse(admin, name);
    }

    /* No way to check if user is a proper, registered user for now */
    @Override
    public boolean joinCourse(Gcode generatedCode, String email) {
        Course c = repo.getCourse(generatedCode);
        if(c == null) {
            return false;
        } else {
            c.registerUser(email);
            return true;
        }
    }

    @Override
    public boolean matchRequest(String sender, String receiver, Gcode generatedCode) {
        Course c = repo.getCourse(generatedCode);
        return c.putMatchRequest(sender, receiver);
    }

    @Override
    public List<String> getAllUsers(Gcode generatedCode) {
        Course c = repo.getCourse(generatedCode);
        return c.getRegisteredEmails();
    }

    @Override
    public List<String> getMatchedWithMe(String email, Gcode generatedCode) {
        Course c = repo.getCourse(generatedCode);
        return c.getMatchedWith(email);
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return repo.getCourse(courseCode);
    }

    @Override
    public List<Gcode> getEnrolledIn(String user) {
        return repo.getEnrolledIn(user);
    }
}
