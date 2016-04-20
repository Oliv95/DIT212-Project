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

    /**
     * If users are not enlisted at this course, nothing is done
     * If the users are already matched, nothing is done.
     * If there exists a MatchRequest where to has requested to work with from, this request is removed and a
     * Matched object is created for these users.
     * Else a MatchRequest is created saying from wants to work with to.
     * @param from
     * @param to
     */
    public void putMatchRequest(String from, String to) {
        if(listed.containsKey(from) && listed.containsKey(to)) {
            for (Matched m : matches) {
                if (m.getMembers().contains(from) && m.getMembers().contains(to)) { // if users are matched, should not be able to send another request
                    return;
                }
            }
            for (MatchRequest m : match_requests) {
                if (m.getTo().equals(from) && m.getTo().equals(from)) {
                    match_requests.remove(m);
                    matches.add(new Matched(from, to, code));
                    return;
                }
            }
            match_requests.add(new MatchRequest(from, to));
        }
    }

    public String[] getMatchedWith(String email) {
        List<String> users = new ArrayList<>();
        for(Matched m : this.matches) { // for every match in this course
            if(m.getMembers().contains(email)) { // if email is a member of that match
                for(String s : m.getMembers()) { // return every other member in that group
                    if(!s.equals(email)) {
                        users.add(s);
                    }
                }

            }
        }
        return users.toArray(new String[users.size()]);
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
