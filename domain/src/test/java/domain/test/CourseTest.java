package domain.test;

import domain.Course;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.User;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseTest {


    private ICourse course;
    private IUser user;
    private String email;
    private String name;
    private String password;
    private String courseName;

    @Before
    public void setup(){
        course = new CourseDomain();
        user = new UserDomain();
        email = "jj_almen@hotmaill.com";
        name = "jonatan";
        password = "password";
        courseName = "TDA755";
    }

    @Test
    public void shouldNotBeAbleUnlessAdmin(){
        Gcode code = course.createCourse(courseName, email+'k');
        assertNull(code);
    }

    @Test
    public void shouldBeAbleToCreateCourseAsAdmin(){
        user.createAdmin(email, name, password);
        Gcode code = course.createCourse(courseName, email);
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
        String email = "axel@epost";
        user.createAdmin(email, "mrBigOliv", "password");
        String admin = user.getAdmin(email).getEmail();
        Gcode code = course.createCourse("Datababes42",admin);
        Course c = course.getCourse(code);
        assertTrue(c.getAdmin().equals(email));;
    }

    @Test
    public void createCourseWithUser() {
        user.createUser("jjj_almen@hotmail.com", "jonathan", "password");
        String email = user.getUser("jjj_almen@hotmail.com").getEmail();
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
        user.createUser("j_almen@hotmade.com", "jonathan", "password");
        String userEmail = user.getUser("j_almen@hotmade.com").getEmail();
        user.createAdmin("kraus@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("kraus@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("Datababe", admin);
        assertTrue(course.joinCourse(code, userEmail));
    }

    @Test
    public void registerAdmin() {
        user.createAdmin("krauman@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("krauman@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("PLT", admin);
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
        user.createUser("jalmen@hotmail.com", "jonathan", "password");
        String email = user.getUser("jalmen@hotmail.com").getEmail();
        user.createAdmin("kran@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("kran@kraus.hubbe").getEmail();
        course.createCourse("DataBabes", admin);
        assertFalse(course.joinCourse(new Gcode(), email));
    }

    @Test
    public void registerGoodUserAndCheckJoined() {
        user.createUser("j@hotmail.com", "jonathan", "password");
        String email = user.getUser("j@hotmail.com").getEmail();
        user.createAdmin("k@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("k@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("DataBabes1337", admin);
        course.joinCourse(code, email);
        assertTrue(course.getAllUsers(code).size() >= 1);
    }

    @Test
    public void registerGoodUserAndCheckEmail() {
        user.createUser("n@hotmail.com", "jonathan", "password");
        String email = user.getUser("n@hotmail.com").getEmail();
        user.createAdmin("kn@kraus.hubbe", "hubbe", "overlord");
        String admin = user.getAdmin("kn@kraus.hubbe").getEmail();
        Gcode code = course.createCourse("MOPTOPPOP", admin);
        course.joinCourse(code, email);
        List<String> allUsers = course.getAllUsers(code);
        String userEmail = user.getUser("n@hotmail.com").getEmail();

        assertTrue(allUsers.contains(userEmail));
    }

    @Test
    public void makePartnerRequst() {
        user.createUser("test","name","pw");
        user.createUser("test2","name2","pw");
        user.createAdmin("tofu","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofu");
        course.joinCourse(gcode,"test");
        course.joinCourse(gcode,"test2");
        course.matchRequest("test","test2",gcode);
        course.matchRequest("test2","test",gcode);
        course.partnerRequest("test","test2",gcode);
        course.partnerRequest("test2","test",gcode);
        boolean result = course.getPartner("test",gcode).equals("test2");
        assertTrue(result);
    }
    @Test
    public void makeBadPartnerRequst() {
        user.createUser("test3","name","pw");
        user.createUser("test4","name2","pw");
        user.createAdmin("tofulife","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofulife");
        Gcode wrongCode = course.createCourse("myCourse","tofulife2");
        course.joinCourse(gcode,"test3");
        course.joinCourse(gcode,"test4");
        course.matchRequest("test3","test4",gcode);
        course.matchRequest("test4","test3",gcode);
        course.partnerRequest("test3","test4",gcode);
        course.partnerRequest("test4","test3",gcode);
        String result = course.getPartner("test3",wrongCode);
        assertNull(result);
    }

    @Test
    public void makePartnerCannotSendMatchRequests() {
        user.createUser("test","name","pw");
        user.createUser("test2","name2","pw");
        user.createUser("test3","name3","pw");
        user.createAdmin("tofu","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofu");
        course.joinCourse(gcode,"test");
        course.joinCourse(gcode,"test2");
        course.joinCourse(gcode,"test3");
        course.matchRequest("test","test2",gcode);
        course.matchRequest("test2","test",gcode);
        course.partnerRequest("test","test2",gcode);
        course.partnerRequest("test2","test",gcode);
        assertFalse(course.matchRequest("test3","test2",gcode));
    }

    @Test
    public void makePartnerCannotSendMatchRequestsOtherUser() {
        user.createUser("test","name","pw");
        user.createUser("test2","name2","pw");
        user.createUser("test3","name3","pw");
        user.createAdmin("tofu","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofu");
        course.joinCourse(gcode,"test");
        course.joinCourse(gcode,"test2");
        course.joinCourse(gcode,"test3");
        course.matchRequest("test","test2",gcode);
        course.matchRequest("test2","test",gcode);
        course.partnerRequest("test","test2",gcode);
        course.partnerRequest("test2","test",gcode);
        assertFalse(course.matchRequest("test3","test",gcode));
    }

    @Test
    public void makePartnerPurgeMatchRequests() {
        user.createUser("test","name","pw");
        user.createUser("test2","name2","pw");
        user.createUser("test3","name3","pw");
        user.createAdmin("tofu","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofu");
        course.joinCourse(gcode,"test");
        course.joinCourse(gcode,"test2");
        course.joinCourse(gcode,"test3");
        course.matchRequest("test","test2",gcode);
        course.matchRequest("test2","test",gcode);
        course.matchRequest("test3","test2",gcode);
        course.partnerRequest("test","test2",gcode);
        course.partnerRequest("test2","test",gcode);
        assertTrue(course.getCourse(gcode).returnMatchRequests().size() == 0);
    }

    @Test
    public void makePartnerPurgeMatched() {
        user.createUser("test","name","pw");
        user.createUser("test2","name2","pw");
        user.createUser("test3","name3","pw");
        user.createAdmin("tofu","adder","pw");
        Gcode gcode = course.createCourse("myCourse","tofu");
        course.joinCourse(gcode,"test");
        course.joinCourse(gcode,"test2");
        course.joinCourse(gcode,"test3");
        course.matchRequest("test","test2",gcode);
        course.matchRequest("test2","test",gcode);
        course.matchRequest("test3","test2",gcode);
        course.matchRequest("test","test3",gcode);
        course.partnerRequest("test","test2",gcode);
        course.partnerRequest("test2","test",gcode);
        assertTrue(course.getCourse(gcode).returnMatched().size() == 0);
    }

}
