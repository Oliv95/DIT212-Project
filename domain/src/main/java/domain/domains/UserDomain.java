package domain.domains;

import domain.Admin;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.interfaces.ICourse;
import domain.interfaces.ICourseRepo;
import domain.interfaces.IUser;
import domain.interfaces.IUserRepo;
import domain.util.Gcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public class UserDomain implements IUser{

    private IUserRepo userRepo;
    private ICourseRepo courseRepo;

    public UserDomain(){
        userRepo   = LocalUserRepo.getInstance();
        courseRepo = LocalCourseRepo.getInstance();
    }

    @Override
    public boolean createUser(String email, String name, String password) {
        List<User> allUsers = userRepo.getAllUsers();
        List<Admin> allAdmins = userRepo.getAllAdmins();

        for (Admin admin : allAdmins) {
            String adminEmail = admin.getEmail();
            if (adminEmail.equals(email)) {
                return false;
            }
        }

        for (User user : allUsers) {
            String userEmail = user.getEmail();
            if (userEmail.equals(email)) {
                return false;
            }
        }
        userRepo.createUser(email,name,password);
        return true;
    }

    @Override
    public boolean createAdmin(String email, String name, String password) {
        List<Admin> allAdmins = userRepo.getAllAdmins();
        List<User> allUsers = userRepo.getAllUsers();

        for (Admin admin : allAdmins) {
            String adminEmail = admin.getEmail();
            if (adminEmail.equals(email)) {
                return false;
            }
        }

        for (User user : allUsers) {
            String userEmail = user.getEmail();
            if (userEmail.equals(email)) {
                return false;
            }
        }
        userRepo.createAdmin(email,name,password);
        return true;
    }

    @Override
    public User getUser(String email) {
        return userRepo.getUser(email);
    }

    @Override
    public Admin getAdmin(String email) {
        return userRepo.getAdmin(email);
    }
}
