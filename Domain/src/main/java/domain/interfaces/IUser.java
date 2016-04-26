package domain.interfaces;

import domain.Admin;
import domain.Course;
import domain.User;
import domain.util.Gcode;

import java.util.List;

/**
 * Created by oliv on 4/21/16.
 */
public interface IUser {

    /**
     * Creates a user and registers it in the domain
     * @param email email of the user
     * @param name name of the user
     * @param password users password
     * @return false if registration failed, true otherwise
     */
    boolean createUser(String email,String name,String password);

    /**
     * Creates an admin and registers it in the domain
     * @param email email of the admin
     * @param name name of the admin
     * @param password admins password
     * @return false if registration failed, true otherwise
     */
    boolean createAdmin(String email,String name,String password);

    /**
     * Returns information about the user
     * @param email the requested users email
     * @return information about the user
     */
    User getUser(String email);

    /**
     * Returns information about the admin
     * @param email the requested admins email
     * @return information about the admin
     */
    Admin getAdmin(String email);



}
