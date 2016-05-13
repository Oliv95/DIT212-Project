package domain.domains;

import domain.Course;
import domain.MatchRequest;
import domain.Matched;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.interfaces.ICourse;
import domain.interfaces.ICourseRepo;
import domain.interfaces.IUserRepo;
import domain.util.Gcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class CourseDomain implements ICourse{

    private ICourseRepo courseRepo;
    private IUserRepo userRepo;

    public CourseDomain(){
        this.courseRepo = LocalCourseRepo.getInstance();
        this.userRepo = LocalUserRepo.getInstance();
    }

    @Override
    public Gcode createCourse(String name, String admin) {
        return courseRepo.createCourse(admin, name);
    }

    /* No way to check if user is a proper, registered user for now */
    @Override
    public boolean joinCourse(Gcode generatedCode, String email) {
        Course c = courseRepo.getCourse(generatedCode);
        if(c == null) {
            return false;
        } else {
            c.registerUser(email);
            return true;
        }
    }

    @Override
    public boolean matchRequest(String sender, String receiver, Gcode generatedCode) {
        Course c = courseRepo.getCourse(generatedCode);
        List<String> listed = c.getRegisteredEmails();
        List<MatchRequest> match_requests = c.returnMatchRequests(); // Map<From,To>
        List<Matched> matches = c.returnMatched();
        boolean result = false;
        if(listed.contains(sender) && listed.contains(receiver)) {
            for (Matched m : matches) {
                if (m.getMembers().contains(sender) && m.getMembers().contains(receiver)) { // if users are matched, should not be able to send another request
                    result = true;
                }
            }
            for (MatchRequest m : match_requests) {
                if (m.getTo().equals(sender) && m.getTo().equals(sender)) {
                    match_requests.remove(m);
                    matches.add(new Matched(sender, receiver));
                    result = true;
                }
            }
            match_requests.add(new MatchRequest(sender, receiver));
            result = true;
        } else {
            result = false;
        }
        c.putMatchRequest(sender, receiver);
        return result;
    }

    @Override
    public List<String> getAllUsers(Gcode generatedCode) {
        Course c = courseRepo.getCourse(generatedCode);
        return c.getRegisteredEmails();
    }

    @Override
    public List<String> getMatchedWithMe(String email, Gcode generatedCode) {
        Course c = courseRepo.getCourse(generatedCode);
        List<String> users = new ArrayList<>();
        List<Matched> matches = c.returnMatched();

        for(Matched m : matches) { // for every match in this course
            if(m.getMembers().contains(email)) { // if email is a member of that match
                for(String s : m.getMembers()) { // return every other member in that group
                    if(!s.equals(email)) {
                        users.add(s);
                    }
                }
            }
        }
        return  users;
        //return c.getMatchedWith(email);
    }

    @Override
    public Course getCourse(Gcode courseCode) {
        return courseRepo.getCourse(courseCode);
    }

    @Override
    public List<Gcode> getEnrolledIn(String user) {
        return courseRepo.getEnrolledIn(user);
    }
}
