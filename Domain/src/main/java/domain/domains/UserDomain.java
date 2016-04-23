package domain.domains;

import domain.Admin;
import domain.User;
import domain.interfaces.IUser;
import domain.interfaces.IUserRepo;

/**
 * Created by oliv on 4/23/16.
 */
public class UserDomain implements IUser{
    private IUserRepo repo;

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
}
