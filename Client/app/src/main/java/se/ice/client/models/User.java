package se.ice.client.models;

public class User extends Person {

    public User() {
        super();
    };

    public User(String email,String name,String password) {
        super(email, name, password);
    }

}
