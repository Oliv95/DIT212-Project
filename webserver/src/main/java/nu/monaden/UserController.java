package nu.monaden;

import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
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
        boolean u = user.createUser(email,name,password);
        return u;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public domain.User getUser(@RequestParam(value = "email") String email){
        domain.User u = user.getUser(email);
        System.out.println(u);
        return u;
    }

    @RequestMapping(value = "/admins", method = RequestMethod.POST)
    public boolean createAdmin(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password) {
        return user.createAdmin(email,name,password);
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public domain.Admin getAdmin(@RequestParam(value = "email") String email) {
        return user.getAdmin(email);
    }

    /*
     * CHANGED RETURNTYPE FROM List<Gcode> TO List<String>
     */
    @RequestMapping(value = "/users/email", method = RequestMethod.GET)
    public List<String> getEnrolledIn(@RequestParam(value = "email") String email) {
        List<Gcode> list = course.getEnrolledIn(email);
        List<String> stringList = new ArrayList<>();
        for(Gcode g : list) {
            stringList.add(g.toString());
        }
        System.out.println(list);
        return stringList;
    }

    @RequestMapping(value = "/users/email/course/match", method = RequestMethod.GET)
    public List<String> getMatchedWithMe(@RequestParam(value = "email") String email,
                                         @RequestParam(value = "gcode") String gcode) {
        List<String> list = course.getMatchedWithMe(email, Gcode.fromString(gcode));
        System.out.println(list);
        return list;

    }

    /***********************************Methods on /course*******************************/

    /**
     * CHANGED RETURNTYPE FROM Gcode TO STRING / Robert
     */
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public String createCourse(@RequestParam(value = "name") String name,
                              @RequestParam(value = "admin") String admin) {
        Gcode code = course.createCourse(name, admin);
        return code.toString();
    }

    @RequestMapping(value = "/course/gcode/join/user", method = RequestMethod.POST)
    public boolean joinCourse(@RequestParam(value = "gcode") String code,
                              @RequestParam(value = "email") String email) {
        return course.joinCourse(Gcode.fromString(code), email);
    }

    @RequestMapping(value = "/course/gcode/users", method = RequestMethod.GET)
    public List<String> getAllUsers(@RequestParam(value = "gcode") String gcode) {
        List<String> list = course.getAllUsers(Gcode.fromString(gcode));
        return list;
    }

    @RequestMapping(value = "/course/gcode/matches", method = RequestMethod.GET)
    public boolean matchRequest(@RequestParam(value = "sender") String sender,
                                @RequestParam(value = "receiver") String receiver,
                                @RequestParam(value = "gcode") String code) {
        return course.matchRequest(sender,receiver,Gcode.fromString(code));
    }

}
