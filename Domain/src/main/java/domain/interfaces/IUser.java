package domain.interfaces;

import domain.Admin;
import domain.User;

/**
 * Created by oliv on 4/21/16.
 */
public interface IUser {

    boolean createUser(String email,String name,String password);

    boolean createAdmin(String email,String name,String password);

    User getUser(String email);

    Admin getAdmin(String email);
}
