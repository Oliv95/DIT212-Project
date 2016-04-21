import domain.App;
import domain.User;
import domain.interfaces.Domain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by robert on 4/20/16.
 */
public class UserAdminTest {
    private Domain app;

    @Before
    public void setup(){
        app = new App();
    }

    /*-----------------------Create user/admin tests----------------------------------*/
    @Test
    public void createUser(){
        String email = app.createUser("j_almen@hotmail.com", "Jonatan", "password");
        assertFalse(email.equals(""));
    }

    @Test
    public void createUserPersistentInformation(){
        String useremail= "j_almen@hotmail.com";
        String name = "Jonatan";
        String password = "password";
        String email = app.createUser(useremail, name, password);
        User u = app.getUser(email);
        assertTrue(u.getEmail().equals(useremail) && u.getName().equals(name) && u.getPassword().equals(password));
    }

    @Test
    public void testPersonHashCode() {
        String user1 = app.createUser("hubbe@overlord.food","jonathan","almen");
        String user2 = app.createUser("hube@overlord.food","jonathan","almen");
        User one = app.getUser(user1);
        User two = app.getUser(user2);
        assertFalse(one.hashCode() == two.hashCode());
    }

    @Test
    public void shouldBeAbleTogetUserEmailAfterCreation(){
        String email = app.createUser("rewbert93@gmail.com", "Robert", "password");
        User user = app.getUser(email);
        assertTrue(user.getEmail().equals(email));
    }

    @Test
    public void createAdminUser() {
        String signup = "j_almen@hotmail.com";
        String email = app.createAdmin(signup, "Jonatan", "password");
        assertTrue(app.getAdmin(email).getEmail().equals(signup));
    }

    @Test
    public void createBadAdmin() {
        String signup = "j_almen@hotmail.com";
        String email = app.createAdmin(signup, "Jonatan", "password");
        assertTrue(app.createAdmin(signup, "Jonatan", "password").equals(""));
    }

    @Test
    public void createAdminWithUsersEmail() {
        String signup = "j_almen@hotmail.com";
        app.createUser(signup, "Jonatan", "password");
        String admin = app.createAdmin(signup, "Jonatan", "password");
        assertTrue(admin.equals(""));
    }

    @Test
    public void createUserWithAdminsEmail() {
        String signup = "j_almen@hotmail.com";
        app.createAdmin(signup, "Jonatan", "password");
        String user = app.createUser(signup, "Jonatan", "password");
        assertTrue(user.equals(""));
    }
}
