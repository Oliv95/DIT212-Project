/**
 * Created by skyw on 4/14/16.
 */
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
        user.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        String admin = user.getAdmin("j_almen@hotmail.com").getEmail();
        Gcode code = course.createCourse("databases", admin);
        Gcode code2 = course.createCourse("testing, debugging and verification", admin);
        assertFalse(code.equals(code2));
    }

    @Test
    public void createTwoCoursesSameAdmin() {
        user.createAdmin("j_almen@hotmail.com", "jonathan", "password");
        String admin = user.getAdmin("j_almen@hotmail.com").getEmail();
        Gcode code = course.createCourse("databases", admin);
        Gcode code2 = course.createCourse("testing, debugging and verification", admin);

        Course one = course.getCourse(code);
        Course two = course.getCourse(code2);

        assertTrue(one.getAdmin().equals(two.getAdmin()));
    }

    /*----------------------Tests for registering to courses---------------------------*/
    @Test
    public void registerUser() {
        user.createUser("j_almen@hotmail.com", "jonathan", "password");
        String userEmail = user.getUser("j_almen@hotmail.com").getEmail();
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Databases", admin);
        assertTrue(course.joinCourse(code, userEmail));
    }

    @Test
    public void registerAdmin() {
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Databases", admin);
        assertFalse(course.joinCourse(code, admin));
    }

    @Test
    public void registerBadUser() {
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Databases", admin);
        assertFalse(course.joinCourse(code, "not_registered@chalmers.se"));
    }

    @Test
    public void registerBadCourse() {
        user.createUser("j_almen@hotmail.com", "jonathan", "password");
        String email = user.getUser("j_almen@hotmail.com").getEmail();
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        course.createCourse("Databases", admin);
        assertFalse(course.joinCourse(new Gcode(), email));
    }

    @Test
    public void registerGoodUserAndCheckJoined() {
        user.createUser("j_almen@hotmail.com", "jonathan", "password");
        String email = user.getUser("j_almen@hotmail.com").getEmail();
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Databases", admin);
        course.joinCourse(code, email);
        assertTrue(course.getAllUsers(code).size() == 1);
    }

    @Test
    public void registerGoodUserAndCheckEmail() {
        user.createUser("j_almen@hotmail.com", "jonathan", "password");
        String email = user.getUser("j_almen@hotmail.com").getEmail();
        user.createAdmin("krausman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krausman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Databases", admin);
        course.joinCourse(code, email);
        assertTrue(course.getAllUsers(code).get(0).equals(user.getUser("j_almen@hotmail.com")));
    }
}
