/**
 * Created by skyw on 4/14/16.
 */
import domain.App;
import domain.Course;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    private ICourse course;
    private IUser user;

    @Before
    public void setup(){
        course = new CourseDomain(LocalCourseRepo.getInstance());
        user = new UserDomain(LocalUserRepo.getInstance());
    }

    @Test
    public void shouldNotBeAbleUnlessAdmin(){

        Gcode code = course.createCourse("TDA755", "j_almen@hotmail.com");
        Course c   = course.getCourse(code);

        assertNull(c);
    }

    @Test
    public void shouldBeAbleToCreateCourseAsAdmin(){

        user.createAdmin("j_almen@hotmail.com", "jonatan", "password");

        Gcode code = course.createCourse("TDA755", "j_almen@hotmail.com");
        Course c   = course.getCourse(code);

        assertTrue(code.equals(c.getCode()));
    }

    @Test
    public void createCourse() {
        user.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        String admin = user.getAdmin("j_almen@hotmail.com").getEmail();

        Gcode code = course.createCourse("Databases", admin);
        assertTrue(code != null);
    }

    @Test
    public void createCourseTestAdmin() {
        String email = "j_almen@hotmail.com";
        user.createAdmin(email, "jonathan", "password");
        String admin = user.getAdmin(email).getEmail();
        Gcode code = course.createCourse("Databases", admin);
        assertTrue(course.getCourse(code).getAdmin().equals(email));
    }

    @Test
    public void createCourseWithUser() {
        user.createUser("j_almen@hotmail.com", "jonathan", "password");
        String email = user.getUser("j_almen@hotmail.com").getEmail();
        assertNull(course.createCourse("databases", email));
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
