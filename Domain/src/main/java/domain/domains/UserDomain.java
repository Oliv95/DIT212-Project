package domain.domains;

import domain.Admin;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.interfaces.IUser;
import domain.interfaces.IUserRepo;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class UserDomain implements IUser{

    private IUserRepo repo;

    public UserDomain(IUserRepo repo){
        this.repo = repo;
    }

    @Override
    public boolean createUser(String email, String name, String password) {
        return false;
    }

    @Override
    public boolean createAdmin(String email, String name, String password) {
        return false;
    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        return null;
    }

    @Override
    public List<Gcode> getEnrolledIn(Gcode gcode) {
        return null;
    }
}
