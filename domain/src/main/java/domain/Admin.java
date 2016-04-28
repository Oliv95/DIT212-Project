package domain;

import java.io.Serializable;

/**
 * Created by robertkrook on 4/13/16.
 */
public class Admin extends Person implements Serializable{
    public Admin(String email, String name, String password){
        super(email, name, password);
    }

}
