/**
 * Created by robert on 4/19/16.
 */

import domain.App;
import domain.Course;
import domain.MatchRequest;
import domain.Matched;
import domain.util.Gcode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MatchTest {


    private App app;
    private String admin;
    private Gcode code;
    private String user1;
    private String user2;
    private String user3;

    @Before
    public void setup() {
        app = new App();
        admin = app.createAdmin("jonathan@almen.se", "jonathan", "almen");
        code = app.createCourse("Databases", admin);
        user1 = app.createUser("Axel@axel.se", "axel", "hackwell");
        user2 = app.createUser("robert@sweglord.se", "robert", "krook");
        user3 = app.createUser("nickeboi", "niklas", "krause");
    }

    @Test
    public void testSendMatchRequest() {
        app.matchRequest(user1, user2, code);
        Course course = app.getCourse(code);
        List<MatchRequest> requests = course.returnMatchRequests();
        assertTrue(requests.size() == 1);
    }

    @Test
    public void testSendMatchRequestGoodData() {
        app.matchRequest(user1, user2, code);
        Course course = app.getCourse(code);
        List<MatchRequest> requests = course.returnMatchRequests();
        assertTrue(requests.get(0).getFrom().equals(user1) && requests.get(0).getTo().equals(user2));
    }

    @Test
    public void testSendTwoRequests() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user1, user3, code);
        List<MatchRequest> requests = app.getCourse(code).returnMatchRequests();
        assertTrue(requests.size() == 2 && requests.get(0).getFrom().equals(requests.get(1).getFrom()));
    }

    @Test
    public void testGetAMatch() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user2, user1, code);
        List<Matched> matches = app.getCourse(code).returnMatched();
        assertTrue(matches.size() == 1 && matches.get(0).isEqualTo(new String[]{user2, user1}));
    }

    @Test
    public void testGetAMatchNoRequests() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user2, user1, code);
        List<MatchRequest> requests = app.getCourse(code).returnMatchRequests();
        assertTrue(requests.size() == 0);
    }

    @Test
    public void testAlreadyMatchedUsers() {
        app.matchRequest(user1,user2,code);
        app.matchRequest(user2,user1,code);
        app.matchRequest(user1,user2,code);
        List<MatchRequest> requests = app.getCourse(code).returnMatchRequests();
        List<Matched> matches = app.getCourse(code).returnMatched();
        assertTrue(requests.size() == 0 && matches.size() == 1);
    }

}
