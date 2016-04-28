
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.domains.UserDomain;
import domain.interfaces.IUser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by robert on 4/20/16.
 */
public class UserAdminTest {
    private IUser app;

    @Before
    public void setup(){
        app = (IUser) new UserDomain(LocalUserRepo.getInstance());
    }

    /*-----------------------Create user/admin tests----------------------------------*/
    @Test
    public void createUser(){
        String email = "j_almen@hotmail.com";
        app.createUser(email, "Jonatan", "password");
        assertFalse(email.equals(""));
    }

    @Test
    public void createUserPersistentInformation(){
        String useremail= "j_almen@hotmail.com";
        String name = "Jonatan";
        String password = "password";
        app.createUser(useremail, name, password);
        User u = app.getUser(useremail);
        assertTrue(u.getEmail().equals(useremail) && u.getName().equals(name) && u.getPassword().equals(password));
    }

    @Test
    public void testPersonHashCode() {
        String user1 = "hubbe@overlord.food";
        app.createUser(user1,"jonathan","almen");
        String user2 = "hube@overlord.food";
        app.createUser(user2,"jonathan","almen");
        User one = app.getUser(user1);
        User two = app.getUser(user2);
        assertFalse(one.hashCode() == two.hashCode());
    }

    @Test
    public void shouldBeAbleTogetUserEmailAfterCreation(){
        String email = "rewbert93@gmail.com";
        app.createUser(email, "Robert", "password");
        User user = app.getUser(email);
        assertTrue(user.getEmail().equals(email));
    }

    @Test
    public void createAdminUser() {
        String signup = "j_almen@hotmail.com";
        app.createAdmin(signup, "Jonatan", "password");
        assertTrue(app.getAdmin(signup).getEmail().equals(signup));
    }

    @Test
    public void createAdminWithUsersEmail() {
        String signup = "j_almen@hotmail.com";
        app.createUser(signup, "Jonatan", "password");
        app.createAdmin(signup, "Jonatan", "password");
        assertTrue(app.getAdmin(signup) == null); // was equals "" before
    }

    @Test
    public void createUserWithAdminsEmail() {
        String signup = "j_almen@hotmail.com";
        app.createAdmin(signup, "Jonatan", "password");
        app.createUser(signup, "Jonatan", "password");
        assertTrue(app.getUser(signup) == null);
    }
}
