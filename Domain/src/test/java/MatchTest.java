/**
 * Created by robert on 4/19/16.
 */

import domain.*;
import domain.interfaces.Domain;
import domain.util.Gcode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MatchTest {


    private Domain app;
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
        app.joinCourse(code,user1);
        app.joinCourse(code,user2);
        app.joinCourse(code,user3);
    }

    /*-----------------------------tests for sendMatchRequest()-------------------------*/
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
        List<String> members = matches.get(0).getMembers();
        assertTrue(matches.size() == 1 && members.contains(user1) && members.contains(user2));
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

    /*----------------------------Tests for getMatchedWithMe()----------------------------------------*/

    @Test
    public void testNoMatches() {
        app.matchRequest(user1, user2, code);
        assertTrue(app.getMatchedWithMe(user1, code).length == 0);
    }

    @Test
    public void testNoMatchesOtherUser() {
        app.matchRequest(user1, user2, code);
        assertTrue(app.getMatchedWithMe(user2, code).length == 0);
    }

    @Test
    public void testGoodMatch() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user2, user1, code);
        User[] res = app.getMatchedWithMe(user1, code);
        assertTrue(res.length == 1 && res[0].getEmail().equals(user2));
    }

    @Test
    public void testGoodMatchTwo() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user1,user3,code);
        app.matchRequest(user2, user1, code);
        app.matchRequest(user3, user1, code);

        User[] res = app.getMatchedWithMe(user1,code);
        assertTrue(res.length == 2 && res[0].getEmail().equals(user2) && res[1].getEmail().equals(user3));
    }

    @Test
    public void testGoodMatchThree() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user1,user3,code);
        app.matchRequest(user2, user1, code);
        app.matchRequest(user3, user1, code);

        User[] res2 = app.getMatchedWithMe(user2, code);
        assertTrue(res2.length == 1 && res2[0].getEmail().equals(user1));
    }

    @Test
    public void testGoodMatchFour() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user1,user3,code);
        app.matchRequest(user2, user1, code);
        app.matchRequest(user3, user1, code);

        User[] res3 = app.getMatchedWithMe(user3,code);
        assertTrue(res3.length == 1 && res3[0].getEmail().equals(user1));
    }

    @Test
    public void testAllMatchesNoRequests() {
        app.matchRequest(user1, user2, code);
        app.matchRequest(user1,user3,code);
        app.matchRequest(user2, user1, code);
        app.matchRequest(user3, user1, code);

        List<MatchRequest> requests = app.getCourse(code).returnMatchRequests();
        assertTrue(requests.size() == 0);
    }

    @Test
    public void getALlUsersBadGcode() {
        assertNull(app.getAllUsers(new Gcode()));
    }
}
