package nu.monaden;

import domain.Course;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    IUser user = new UserDomain();
    ICourse course = new CourseDomain();

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public boolean createAdmin(@RequestParam(value = "name") String name,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "password") String password) {
        return user.createAdmin(email,name,password);
    }

    @RequestMapping(value = "/admin/{email:.*}", method = RequestMethod.GET)
    public domain.Admin getAdmin(@PathVariable String email) {
        return user.getAdmin(email);
    }

    /*
    @RequestMapping(value = "/admin/{email}/administrating",method = RequestMethod.GET)
    public List<Gcode> getAllAdministrating(@PathVariable String email){
        return course.getAllAdministrating(email);
    }
    */

    @RequestMapping(value = "/admin/{email}/administrating",method = RequestMethod.GET)
    public List<Course> getAllAdministratingCourse(@PathVariable String email) {
        List<Course> allCourses = course.getAllCourses();
        List<Course> result = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getAdmin().equals(email)) {
                result.add(course);
            }
        }
        return result;
    }
}
