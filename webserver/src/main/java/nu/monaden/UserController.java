package nu.monaden;

import domain.Course;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    IUser user = new UserDomain();
    ICourse course = new CourseDomain();

    /****************************Methods on /users******************************/


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public boolean createUser(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password" ) String password){
        return user.createUser(email,name,password);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public domain.User getUser(@RequestParam(value = "email") String email){
        domain.User u = user.getUser(email);
        return u;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public boolean createAdmin(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password) {
        return user.createAdmin(email,name,password);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public domain.Admin getAdmin(@RequestParam(value = "email") String email) {
        return user.getAdmin(email);
    }

    @RequestMapping(value = "/users/email", method = RequestMethod.GET)
    public List<Gcode> getEnrolledIn(@RequestParam(value = "email") String email) {
        return course.getEnrolledIn(email);
    }

    @RequestMapping(value = "/users/email/course/match", method = RequestMethod.GET)
    public List<String> getMatchedWithMe(@RequestParam(value = "email") String email,
                                         @RequestParam(value = "gcode") String gcode) {
        return course.getMatchedWithMe(email, Gcode.fromString(gcode));

    }

    @RequestMapping(value = "/users/email/course/notmatch", method = RequestMethod.GET)
    public List<String> getMatchedNotWithMe(@RequestParam(value = "email") String email,
                                         @RequestParam(value = "gcode") String gcode) {
        List<String> users = course.getAllUsers(Gcode.fromString(gcode));
        users.removeAll(course.getMatchedWithMe(email,Gcode.fromString(gcode)));
        users.remove(email);
        return users;

    }

    @RequestMapping(value = "/admin/allAdministrating",method = RequestMethod.GET)
    public List<Gcode> getAllAdministrating(@RequestParam(value = "admin") String admin) {
        return course.getAllAdministrating(admin);
    }

    @RequestMapping(value = "/admin/course/allAdministrating",method = RequestMethod.GET)
    public List<Course> getAllAdministratingCourse(@RequestParam(value = "admin") String admin) {
        List<Course> allCourses = course.getAllCourses();
        List<Course> result = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getAdmin().equals(admin)) {
                result.add(course);
            }
        }
        return result;
    }

    /***********************************Methods on /course*******************************/

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public Gcode createCourse(@RequestParam(value = "name") String name,
                              @RequestParam(value = "admin") String admin) {
        return course.createCourse(name, admin);
    }

    @RequestMapping(value = "/course/gcode/join/user", method = RequestMethod.POST)
    public boolean joinCourse(@RequestParam(value = "gcode") String code,
                              @RequestParam(value = "email") String email) {
        return course.joinCourse(Gcode.fromString(code), email);
    }

    @RequestMapping(value = "/course/gcode/users", method = RequestMethod.GET)
    public List<String> getAllUsers(@RequestParam(value = "gcode") String gcode) {
        return course.getAllUsers(Gcode.fromString(gcode));
    }

    @RequestMapping(value = "/course/gcode/matches", method = RequestMethod.GET)
    public boolean matchRequest(@RequestParam(value = "sender") String sender,
                                @RequestParam(value = "receiver") String receiver,
                                @RequestParam(value = "gcode") String code) {
        return course.matchRequest(sender,receiver,Gcode.fromString(code));
    }

    @RequestMapping(value = "/course/getCourse",method = RequestMethod.GET)
    public Course getCourse(@RequestParam(value = "gcode") String code) {
        return course.getCourse(Gcode.fromString(code));
    }

    @RequestMapping(value = "/course/all", method = RequestMethod.GET)
    public List<Course> getAllCourses(){
        return course.getAllCourses();
    }

}

