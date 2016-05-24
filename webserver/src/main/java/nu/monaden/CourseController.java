package nu.monaden;

import domain.Course;
import domain.domains.CourseDomain;
import domain.interfaces.ICourse;
import domain.util.Gcode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    ICourse course = new CourseDomain();

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public Gcode createCourse(@RequestParam(value = "name") String name,
                              @RequestParam(value = "admin") String admin) {
        return course.createCourse(name, admin);
    }

    @RequestMapping(value = "/course/{gcode}/join", method = RequestMethod.POST)
    public boolean joinCourse(@PathVariable String gcode,
                              @RequestParam(value = "email") String email) {
        return course.joinCourse(Gcode.fromString(gcode), email);
    }

    @RequestMapping(value = "/course/{gcode}/getAllUsers/", method = RequestMethod.GET)
    public List<String> getAllUsers(@PathVariable String gcode) {
        return course.getAllUsers(Gcode.fromString(gcode));
    }

    @RequestMapping(value = "/course/{gcode}/match", method = RequestMethod.POST)
    public boolean matchRequest(@RequestParam(value = "sender") String sender,
                                @RequestParam(value = "receiver") String receiver,
                                @PathVariable String gcode){
        return course.matchRequest(sender,receiver,Gcode.fromString(gcode));
    }

    @RequestMapping(value = "/course/{code}",method = RequestMethod.GET)
    public Course getCourse(@PathVariable String code) {
        return course.getCourse(Gcode.fromString(code));
    }

}
