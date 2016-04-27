// import domain.App;
import domain.User;
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
    /*
    App app;
    User user1;
    User user2;

    @Before
    public void setup() {
        app = new App();
        user1 = app.getUser(app.createUser("hubbe@overlord.food","jonathan","almen"));
        user2 = app.getUser(app.createUser("axel@mango.com","squishy","mango"));
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
    public void testBadEqualsBadPerson() {
        String nulluser = app.createUser(null, "robert", "krook");
        User robert = app.getUser(nulluser);
        assertFalse(robert.equals(user2));
    }

    @Test
    public void testBadEqualsBadPerson2() {
        String nulluser = app.createUser("RObert@krook.se", null, "krook");
        User robert = app.getUser(nulluser);
        assertFalse(robert.equals(user2));
    }

    @Test
    public void testBadEqualsBadPerson3() {
        String nulluser = app.createUser("RObert@krook.se", "robert", null);
        User robert = app.getUser(nulluser);;
        assertFalse(robert.equals(user2));
    }
    */
}
