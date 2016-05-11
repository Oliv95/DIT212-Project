package nu.monaden;

import domain.Repos.LocalUserRepo;
import domain.domains.UserDomain;
import domain.interfaces.IUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    IUser user = new UserDomain(LocalUserRepo.getInstance());

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void createUsers(@RequestParam(value = "name") String name,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password" ) String password){

        System.out.println("hafha" + user.createUser(email, name, password));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public domain.User getUser(@RequestParam(value = "email") String email){
        domain.User u = user.getUser(email);
        System.out.println(u);
        return u;
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
