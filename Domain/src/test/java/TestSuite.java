import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite
 * Created by robert on 4/20/16.
 */
@RunWith(Suite.class)
/* Add any testclass you add to this suite, and execute the entire suite */
@Suite.SuiteClasses({
        AppTest.class,
        CourseTest.class,
        MatchTest.class
})
public class TestSuite {
}
