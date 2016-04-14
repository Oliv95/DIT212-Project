package domain;

/**
 * Created by robertkrook on 4/13/16.
 */
public class User extends Person{

    private boolean privileged_user;

    public User(String email,String name,String password) {
        super(password,name,email);
    }


}
