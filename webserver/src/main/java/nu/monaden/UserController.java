package nu.monaden;

import domain.MatchRequest;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value = "/users/{email:.*}/{gcode}/matched", method = RequestMethod.GET)
    public List<String> getMatchedWithMe(@PathVariable String email, @PathVariable String gcode)
    {
        return course.getMatchedWithMe(email, Gcode.fromString(gcode));
    }

    @RequestMapping(value = "/users/{email:.*}/{gcode}/notmatched", method = RequestMethod.GET)
    public List<String> getMatchedNotWithMe(@PathVariable String email,
                                            @PathVariable String gcode)
    {
        //needs Deep copy
        List<String> users = course.getAllUsers(Gcode.fromString(gcode));
        List<String> usersCopy = new ArrayList<>();
        for (String s : users) {
            usersCopy.add(s);
        }

        usersCopy.removeAll(course.getMatchedWithMe(email,Gcode.fromString(gcode)));
        usersCopy.remove(email);
        return usersCopy;

    }

}

