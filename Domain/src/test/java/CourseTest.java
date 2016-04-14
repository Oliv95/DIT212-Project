/**
 * Created by skyw on 4/14/16.
 */
import domain.App;
import domain.Course;
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
    public void shouldBeAbleToCreateCourse(){
        app.createCourse("TDA755", "j_almen@hotmail.com");
        Course c = app.getCourse("TDA755");
        assertNotNull(c);
    }

    public void shouldNotBeAbleUnlessAdmin(){
        new Course("TDA755", "j_almen@hotmail.com");
    }
}
