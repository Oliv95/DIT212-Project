package nu.monaden;

import domain.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User createUsers(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email){
        Admin admin = new Admin("Jonatan", "Something", "somethng");
        return new User(name, email);
    }
}

class User {
    private final String name;
    private final String email;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
