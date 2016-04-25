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
     * @param email user email
     * @return All codes for the courses the user is enrolled in
     */
    List<Gcode> getEnrolledIn(String email);

    /**
     * @param admin admin email
     * @return All codes for the courses the admin is administrating
     */
    List<Gcode> getAllAdministrating(String admin);
}
