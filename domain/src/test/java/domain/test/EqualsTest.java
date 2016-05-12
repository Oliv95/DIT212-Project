package domain.test;// import domain.App;
import domain.MatchRequest;
import domain.Matched;
import domain.Repos.LocalCourseRepo;
import domain.User;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * These tests all had the same @Before, so branched them out to their own class
 * Tests written to achieve high code coverage
 * Created by robert on 4/20/16.
 */
public class EqualsTest {

    User user1;
    User user2;
    String email1;
    String email2;
    CourseDomain courseDomain;
    UserDomain userDomain;
    MatchRequest matchRequest;
    Matched matched1;
    Matched matched2;

    @Before
    public void setup() {
        courseDomain = new CourseDomain();
        userDomain   = new UserDomain();
        email1 = "hubbe@overlord.food";
        email2 = "axel@mango.com";
        userDomain.createUser(email1,"jonathan","almen");
        userDomain.createUser(email2,"squishy","mango");
        user1 = userDomain.getUser(email1);
        user2 = userDomain.getUser(email2);
        matchRequest = new MatchRequest(email1,email2);
        matched1 = new Matched(email1,email2);
        matched2 = new Matched(email2,email1);
    }

    @Test
    public void testPersonEquals() {
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testGoodEquals() {
        assertTrue(user1.equals(user1));
    }

    @Test
    public void testPersonBadEquals() {
        assertFalse(user1.equals(null));
    }

    @Test
    public void testMatchedEquals() {
        assertTrue(matched1.equals(matched2));
    }

    @Test
    public void testMatchedEquals2() {
        assertTrue(matched2.equals(matched1));
    }


}
