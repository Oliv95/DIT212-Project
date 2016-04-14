/**
 * Created by robertkrook on 4/13/16.
 */
import domain.App;
import domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {


    private App app;

    @Before
    public void setup(){
        app = new App();
    }

    @Test
    public void createUser(){
        String email = app.createUser("j_almen@hotmail.com", "Jonatan", "password");
        assertFalse(email.equals(""));
    }

    @Test
    public void shouldBeAbleTogetUserIdAfterCreation(){
        String email = app.createUser("rewbert93@gmail.com", "Robert", "password");
        User user = app.getUser(email);
        assertTrue(user.getEmail().equals(email));
    }

    @Test
    public void createAdminUser() {
        String signup = "j_almen@hotmail.com";
        String email = app.createAdmin(signup, "Jonatan", "password");
        System.out.println(email);
    }
}
