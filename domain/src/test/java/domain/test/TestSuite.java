package domain.test;

//TODO test might fail due to save files...
//TODO copy to some other place then move back??

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suites
 * Created by robert on 4/20/16.
 */
@RunWith(Suite.class)
/* Add any testclass you add to this suite, and execute the entire suite */
@Suite.SuiteClasses({
         CourseTest.class,
         MatchTest.class,
         UserAdminTest.class,
         EqualsTest.class
})
public class TestSuite {
}
