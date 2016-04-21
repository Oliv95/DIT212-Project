/**
 * Created by skyw on 4/14/16.
 */
import domain.App;
import domain.Course;
import domain.util.Gcode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {

    private App app;

    @Before
    public void setup(){
        app = new App();
    }

    @Test
    public void shouldNotBeAbleUnlessAdmin(){

        Gcode code = app.createCourse("TDA755", "j_almen@hotmail.com");
        Course c   = app.getCourse(code);

        assertNull(c);
    }

    @Test
    public void shouldBeAbleToCreateCourseAsAdmin(){

        app.createAdmin("j_almen@hotmail.com", "jonatan", "password");

        Gcode code = app.createCourse("TDA755", "j_almen@hotmail.com");
        Course c   = app.getCourse(code);

        assertTrue(code.equals(c.getCode()));
    }
}
