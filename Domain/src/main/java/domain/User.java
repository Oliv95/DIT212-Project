package domain;

/**
 * Created by robertkrook on 4/13/16.
 */
public class User {

    private String username;
    private String name;
    private String email;
    private boolean privileged_user;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Either gives or removes privileges.
     * @return true if the user is given privileges, or false if privileges are removed.
     */
    public boolean privileges() {
        privileged_user = true;
        return privileged_user;
    }

    /**
     * See if this user is an admin
     * @return true if user is admin, else false
     */
    public boolean admin() {
        return privileged_user;
    }
}
