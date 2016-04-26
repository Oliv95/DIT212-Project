package domain.interfaces;

import domain.Admin;
import domain.User;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/23/16.
 */
public interface IUserRepo {

    /**
     * @param email user email
     * @return User with the given email
     */
    User getUser(String email);

    /**
     * @param email admin email
     * @return Admin with the given email
     */
    Admin getAdmin(String email);

    /**
     * @param email email for the new user
     * @param name name for the new user
     * @param pw password for the new user
     */
    void createUser(String email,String name, String pw);

    /**
     * @param email email for the new admin
     * @param name name for the new admin
     * @param pw password for the new admin
     */
    void createAdmin(String email,String name, String pw);

    /**
     * @return returns all existing users
     */
    List<User> getAllUsers();

    /**
     * @return returns all existing admins
     */
    List<Admin> getAllAdmins();

}
