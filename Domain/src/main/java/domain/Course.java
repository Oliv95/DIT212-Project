package domain;

import domain.util.Gcode;

import java.util.*;
import java.util.regex.MatchResult;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 * Created by robertkrook on 4/13/16.
 */
public class Course {
    private Gcode code;
    private String name;
    private String admin; // email of admin

    private Map<String,User> listed;
    private List<MatchRequest> match_requests; // Map<From,To>
    private List<Matched> matches;

    public Course(String name, String admin, Gcode gcode) {
        this.name = name;
        this.admin = admin;
        code = gcode;
        listed = new HashMap<>();
        match_requests = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public Gcode getCode() {
        return code;
    }

    public void registerUser(User user) {
        listed.put(user.getEmail(), user);
    }

    public User[] getRegistered() {
        Collection<User> users = listed.values();
        return users.toArray(new User[users.size()]);
    }

    public String getAdmin() {
        return admin;
    }

    public void putMatchRequest(String from, String to) {
        for(Matched m : matches) {
            if(m.isEqualTo(new String[]{from, to})) { // if users are matched, should not be able to send another request
                return;
            }
        }
        for(MatchRequest m : match_requests) {
            if(m.getTo().equals(from) && m.getTo().equals(from)) {
                match_requests.remove(m);
                matches.add(new Matched(new String[]{from, to}, code));
                return;
            }
        }
        match_requests.add(new MatchRequest(from, to));
    }

    // Just to simplify testing
    public List<MatchRequest> returnMatchRequests() {
        return match_requests;
    }

    // just to simplify testing
    public List<Matched> returnMatched() {
        return matches;
    }
}
