package nu.monaden;

import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    IUser user = new UserDomain();
    ICourse course = new CourseDomain();


    @RequestMapping(value = "/users/{email:.*}", method = RequestMethod.GET)
    public domain.User getUser(@PathVariable String email){
        return user.getUser(email);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public boolean createUser(@RequestParam(value = "name") String name,
                              @RequestParam(value = "email") String email,
                              @RequestParam(value = "password" ) String password){
        return user.createUser(email,name,password);
    }

    @RequestMapping(value = "/users/{email:.*}/enrolledin", method = RequestMethod.GET)
    public List<Gcode> getEnrolledIn(@PathVariable String email) {
        return course.getEnrolledIn(email);
    }

    @RequestMapping(value = "/users/{email:.*}/{course}/matched", method = RequestMethod.GET)
    public List<String> getMatchedWithMe(@PathVariable String email, @PathVariable String gcode)
    {
        return course.getMatchedWithMe(email, Gcode.fromString(gcode));
    }

    @RequestMapping(value = "/users/{email:.*}/{course}/notmatched", method = RequestMethod.GET)
    public List<String> getMatchedNotWithMe(@PathVariable String email,
                                            @PathVariable String gcode)
    {
        List<String> users = course.getAllUsers(Gcode.fromString(gcode));
        users.removeAll(course.getMatchedWithMe(email,Gcode.fromString(gcode)));
        users.remove(email);
        return users;

    }

}

