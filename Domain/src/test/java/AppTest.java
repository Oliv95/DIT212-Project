/**
 * Created by robertkrook on 4/13/16.
 */
import domain.App;
import domain.Course;
import domain.User;
import domain.util.Gcode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {


    private App app;

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
    /*-------------------Tests for creating courses-----------------------------------*/
    @Test
    public void createCourse() {
        String admin = app.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        Gcode code = app.createCourse("Databases", admin);
        assertTrue(code != null);
    }

    @Test
    public void createCourseTestAdmin() {
        String email = "j_almen@hotmail.com";
        String admin = app.createAdmin(email, "jonathan", "password");
        Gcode code = app.createCourse("Databases", admin);
        assertTrue(app.getCourse(code).getAdmin().equals(email));
    }

    @Test
    public void createCourseWithUser() {
        String user = app.createUser("j_almen@hotmail.com", "jonathan", "password");
        assertNull(app.createCourse("databases", user));
    }

    @Test
    public void createTwoCourses() {
        String admin = app.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        Gcode code = app.createCourse("databases", admin);
        Gcode code2 = app.createCourse("testing, debugging and verification", admin);
        assertFalse(code.equals(code2));
    }

    @Test
    public void createTwoCoursesSameAdmin() {
        String admin = app.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        Gcode code = app.createCourse("databases", admin);
        Gcode code2 = app.createCourse("testing, debugging and verification", admin);

        Course one = app.getCourse(code);
        Course two = app.getCourse(code2);

        assertTrue(one.getAdmin().equals(two.getAdmin()));
    }

    /*----------------------Tests for registering to courses---------------------------*/
    @Test
    public void registerUser() {
        String user = app.createUser("j_almen@hotmail.com", "jonathan", "password");
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        Gcode code = app.createCourse("Databases", admin);
        assertTrue(app.joinCourse(code, user));
    }

    @Test
    public void registerAdmin() {
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        Gcode code = app.createCourse("Databases", admin);
        assertFalse(app.joinCourse(code, admin));
    }

    @Test
    public void registerBadUser() {
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        Gcode code = app.createCourse("Databases", admin);
        assertFalse(app.joinCourse(code, "not_registered@chalmers.se"));
    }

    @Test
    public void registerBadCourse() {
        String user = app.createUser("j_almen@hotmail.com", "jonathan", "password");
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        app.createCourse("Databases", admin);
        assertFalse(app.joinCourse(new Gcode(), user));
    }

    @Test
    public void registerGoodUserAndCheckJoined() {
        String user = app.createUser("j_almen@hotmail.com", "jonathan", "password");
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        Gcode code = app.createCourse("Databases", admin);
        app.joinCourse(code, user);
        assertTrue(app.getAllUsers(code).length == 1);
    }

    @Test
    public void registerGoodUserAndCheckEmail() {
        String user = app.createUser("j_almen@hotmail.com", "jonathan", "password");
        String admin = app.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        Gcode code = app.createCourse("Databases", admin);
        app.joinCourse(code, user);
        assertTrue(app.getAllUsers(code)[0].equals(app.getUser("j_almen@hotmail.com")));
    }
}
