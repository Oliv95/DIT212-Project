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
        String email = app.createUser("j_almen@hotmail.com");
        assertFalse(email.equals(""));
    }

    @Test
    public void shouldBeAbleTogetUserIdAfterCreation(){
        String email = app.createUser("rewbert93@gmail.com");
        User user = app.getUser(email);
        assertEquals(email, user.getEmail(), 0);
    }

    @Test
    public void createAdminUser() {
        String email = app.createAdmin("j_almen@hotmail.com");
        assertTrue(app.getUser(email).admin());
    }
}
