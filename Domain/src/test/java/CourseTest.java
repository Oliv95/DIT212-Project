/**
 * Created by skyw on 4/14/16.
 */
import domain.Course;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void shouldBeAbleToCreateCourse(){
        new Course("TDA755", "j_almen@hotmail.com");
    }
}
