package domain.test; /**
 * Created by robert on 4/19/16.
 */

import domain.*;
import domain.Repos.LocalCourseRepo;
import domain.Repos.LocalUserRepo;
import domain.domains.CourseDomain;
import domain.domains.UserDomain;
import domain.interfaces.ICourse;
import domain.interfaces.IUser;
import domain.util.Gcode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.regex.MatchResult;

import static org.junit.Assert.*;

public class MatchTest {

    private ICourse courseDomain;
    private IUser userDomain;
    private String admin;
    private Gcode code;
    private String user1;
    private String user2;
    private String user3;

    @Before
    public void setup() {
        courseDomain = new CourseDomain();
        userDomain = new UserDomain();
        admin = "jonathannn@almen.se";
        userDomain.createAdmin(admin, "jonathan", "almen");
        code = courseDomain.createCourse("Databases", admin);
        user1 = "Axel@axel.se";
        user2 = "robert@sweglord.se";
        user3 = "nickeboi";
        userDomain.createUser(user1, "axel", "hackwell");
        userDomain.createUser(user2, "robert", "krook");
        userDomain.createUser(user3, "niklas", "krause");
        courseDomain.joinCourse(code,user1);
        courseDomain.joinCourse(code,user2);
        courseDomain.joinCourse(code,user3);
    }

    //-----------------------------tests for sendMatchRequest()-------------------------
    @Test
    public void testSendMatchRequest() {
        courseDomain.matchRequest(user1, user2, code);
        MatchRequest mr = new MatchRequest(user1,user2);
        Course course = courseDomain.getCourse(code);
        List<MatchRequest> requests = course.returnMatchRequests();
        assertTrue(requests.contains(mr));
    }

    @Test
    public void testSendMatchRequestGoodData() {
        courseDomain.matchRequest(user1, user2, code);
        Course course = courseDomain.getCourse(code);
        List<MatchRequest> requests = course.returnMatchRequests();
        assertTrue(requests.get(0).getFrom().equals(user1) && requests.get(0).getTo().equals(user2));
    }

    @Test
    public void testSendTwoRequests() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user1, user3, code);
        List<MatchRequest> requests = courseDomain.getCourse(code).returnMatchRequests();
        assertTrue(requests.size() >= 2 && requests.get(0).getFrom().equals(requests.get(1).getFrom()));
    }

    @Test
    public void testGetAMatch() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user2, user1, code);
        List<Matched> matches = courseDomain.getCourse(code).returnMatched();
        List<String> members = matches.get(0).getMembers();
        assertTrue(matches.size() == 1 && members.contains(user1) && members.contains(user2));
    }

    @Test
    public void testGetAMatchNoRequests() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user2, user1, code);
        MatchRequest mr = new MatchRequest(user1,user2);
        List<MatchRequest> requests = courseDomain.getCourse(code).returnMatchRequests();
        assertFalse(requests.contains(mr));
    }

    @Test
    public void testAlreadyMatchedUsers() {
        courseDomain.matchRequest(user1,user2,code);
        courseDomain.matchRequest(user2,user1,code);
        courseDomain.matchRequest(user1,user2,code);
        MatchRequest mr = new MatchRequest(user1,user2);
        Matched md = new Matched(user1,user2);
        List<MatchRequest> requests = courseDomain.getCourse(code).returnMatchRequests();
        List<Matched> matches = courseDomain.getCourse(code).returnMatched();
        assertTrue(!requests.contains(mr) && matches.contains(md));
    }

    //----------------------------Tests for getMatchedWithMe()----------------------------------------

    @Test
    public void testNoMatches() {
        courseDomain.matchRequest(user1, user2, code);
        assertTrue(courseDomain.getMatchedWithMe(user1, code).size() == 0);
    }

    @Test
    public void testNoMatchesOtherUser() {
        courseDomain.matchRequest(user1, user2, code);
        assertTrue(courseDomain.getMatchedWithMe(user2, code).size() == 0);
    }

    @Test
    public void testGoodMatch() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user2, user1, code);
        List<String> res = courseDomain.getMatchedWithMe(user1, code);
        assertTrue(res.size() == 1 && res.get(0).equals(user2));
    }

    @Test
    public void testGoodMatchTwo() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user1,user3,code);
        courseDomain.matchRequest(user2, user1, code);
        courseDomain.matchRequest(user3, user1, code);

        List<String> res = courseDomain.getMatchedWithMe(user1,code);
        assertTrue(res.size() == 2 && res.get(0).equals(user2) && res.get(1).equals(user3));
    }

    @Test
    public void testGoodMatchThree() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user1,user3,code);
        courseDomain.matchRequest(user2, user1, code);
        courseDomain.matchRequest(user3, user1, code);

        List<String> res2 = courseDomain.getMatchedWithMe(user2, code);
        assertTrue(res2.size() == 1 && res2.get(0).equals(user1));
    }

    @Test
    public void testGoodMatchFour() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user1,user3,code);
        courseDomain.matchRequest(user2, user1, code);
        courseDomain.matchRequest(user3, user1, code);

        List<String> res3 = courseDomain.getMatchedWithMe(user3,code);
        assertTrue(res3.size() == 1 && res3.get(0).equals(user1));
    }

    @Test
    public void testAllMatchesNoRequests() {
        courseDomain.matchRequest(user1, user2, code);
        courseDomain.matchRequest(user1,user3,code);
        courseDomain.matchRequest(user2, user1, code);
        courseDomain.matchRequest(user3, user1, code);

        List<MatchRequest> requests = courseDomain.getCourse(code).returnMatchRequests();
        assertTrue(requests.size() == 0);
    }

    @Test
    public void getALlUsersBadGcode() {
        List<String> users = courseDomain.getAllUsers(new Gcode());
        assertTrue(null == users);
    }

}
