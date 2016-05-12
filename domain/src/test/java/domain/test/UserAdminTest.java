package domain.test;

import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.domains.UserDomain;
import domain.interfaces.IUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by robert on 4/20/16.
 */
public class UserAdminTest {
    @Test
    public void t(){
        Assert.assertTrue(true);
    }

    private UserDomain userDomain;
    String email ;
    String email2;
    String admin;

    @Before
    public void setup() {
        userDomain = new UserDomain();
        email      = "j_almen@hotmail.com";
        email2     = "hube@overlord.food";
        admin      = "bert@epost.cm";
    }
    //--= new User(email2,"hube","pw")---------------------Create user/admin tests----------------------------------
    @Test
    public void createUser(){
        userDomain.createUser(email,"jon","pw");
        String e = userDomain.getUser(email).getEmail();
        assertTrue(email.equals(e));
    }


    @Test
    public void createUserPersistentInformation(){
        String useremail= "j_almen@hotmail.com";
        String name = "Jonatan";
        String password = "password";
        userDomain.createUser(useremail,name,password);
        User u = userDomain.getUser(useremail);
        assertTrue(u.getEmail().equals(useremail) && u.getName().equals(name) && u.getPassword().equals(password));
    }


    @Test
    public void testPersonHashCode() {
        String user1 = "hubbe@overlord.food";
        userDomain.createUser(user1,"jonathan","almen");
        String user2 = "hube@overlord.food";
        userDomain.createUser(user2,"jonathan","almen");
        User one = userDomain.getUser(user1);
        User two = userDomain.getUser(user2);
        assertFalse(one.hashCode() == two.hashCode());
    }

    @Test
    public void shouldBeAbleTogetUserEmailAfterCreation(){
        String email = "rewbert93@gmail.com";
        userDomain.createUser(email, "Robert", "password");
        User user = userDomain.getUser(email);
        assertTrue(user.getEmail().equals(email));
    }
/*

    @Test
    public void createAdminUser() {
        String signup = "j_almen@hotmail.com";
        userDomain.createAdmin(signup, "Jonatan", "password");
        assertTrue(userDomain.getAdmin(signup).getEmail().equals(signup));
    }

    @Test
    public void createAdminWithUsersEmail() {
        String signup = "j_almen@hotmail.com";
        boolean userMade = userDomain.createUser(signup, "Jonatan", "password");
        boolean adminMade = userDomain.createAdmin(signup, "Jonatan", "password");
        assertTrue(userDomain.getAdmin(signup) == null); // was equals "" before
    }


    @Test
    public void createUserWithAdminsEmail() {
        String signup = "j_almen@hotmail.com";
        app.createAdmin(signup, "Jonatan", "password");
        app.createUser(signup, "Jonatan", "password");
        assertTrue(app.getUser(signup) == null);
    }
    */
}
