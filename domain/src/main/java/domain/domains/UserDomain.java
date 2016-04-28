package domain.domains;

import domain.Admin;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.interfaces.IUser;
import domain.interfaces.IUserRepo;
import domain.util.Gcode;

import java.util.ArrayList;
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
        List<User> allUsers = repo.getAllUsers();
        List<Admin> allAdmins = repo.getAllAdmins();

        for (Admin admin : allAdmins) {
            String adminEmail = admin.getEmail();
            if (admin.equals(email)) {
                return false;
            }
        }

        for (User user : allUsers) {
            String userEmail = user.getEmail();
            if (userEmail.equals(email)) {
                return false;
            }
        }
        repo.createUser(email,name,password);
        return true;
    }

    @Override
    public boolean createAdmin(String email, String name, String password) {
        List<Admin> allAdmins = repo.getAllAdmins();
        List<User> allUsers = repo.getAllUsers();

        for (Admin admin : allAdmins) {
            String adminEmail = admin.getEmail();
            if (admin.equals(email)) {
                return false;
            }
        }

        for (User user : allUsers) {
            String userEmail = user.getEmail();
            if (userEmail.equals(email)) {
                return false;
            }
        }
        repo.createAdmin(email,name,password);
        return true;
    }

    @Override
    public User getUser(String email) {
        return repo.getUser(email);
    }

    @Override
    public Admin getAdmin(String email) {
        return repo.getAdmin(email);
    }
}
