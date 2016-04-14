package domain.interfaces;

import domain.Admin;
import domain.User;

/**
 * Created by robertkrook on 4/13/16.
 */
public interface Domain {

    public String createUser(String email);

    public String createAdmin(String email);

    public void createCourse(String name, Admin admin);

    public boolean joinCourse(String generatedCode, User user);

    public void matchRequest(int sender, int from, String generatedCode);

    public User getUser(String email);

    public User[] getAllUsers(String generatedCode);

    public User[] getMatchedWithMe(int id);

}
