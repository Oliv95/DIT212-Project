package domain.Repos;

import domain.Admin;
import domain.User;
import domain.interfaces.IUserRepo;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class LocalUserRepo implements IUserRepo{

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        return null;
    }

    @Override
    public List<Gcode> getEnrolledIn(String email) {
        return null;
    }

    @Override
    public List<Gcode> getAllAdministrating(String admin) {
        return null;
    }
}
