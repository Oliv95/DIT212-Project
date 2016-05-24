package domain;

import domain.util.Gcode;

import java.io.Serializable;
import java.util.*;
import java.util.regex.MatchResult;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 * Created by robertkrook on 4/13/16.
 */
public class Course implements Serializable{
    private Gcode code;
    private String name;
    private String admin; // email of admin

    private List<String> listed;
    private List<MatchRequest> match_requests; // Map<From,To>
    private List<Matched> matches;
    private List<Partner> partners;
    private List<PartnerRequest> partnerRequests;

    public Course(String name, String admin, Gcode gcode) {
        this.name = name;
        this.admin = admin;
        code = gcode;
        listed = new ArrayList<>();
        match_requests = new ArrayList<>();
        matches = new ArrayList<>();
        partners = new ArrayList<>();
        partnerRequests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * @return the generated code associated with this course object
     */
    public Gcode getCode() {
        return code;
    }

    /**
     * registers a user to this course
     * @param user who wishes to be registered
     */
    public void registerUser(String user) {
        listed.add(user);
    }


    public List<String> getRegisteredEmails() {
        return listed;
    }

    /**
     * @return the admin of this course
     */
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
    public boolean putMatchRequest(String from, String to) {
        return match_requests.add(new MatchRequest(from, to));
    }

    /**
     * returns an array containing the emails of all the users who have matched with you in this course
     * @param email user who wishes to know who he has been matched with
     * @return an array of the emails correspendoing to the users he has been matched with
     */
    //TODO maybe remove from here, moved to courseDomain for the moment
    public List<String> getMatchedWith(String email) {
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
        return users;
    }

    public List<MatchRequest> returnMatchRequests() {
        return match_requests;
    }

    // just to simplify testing
    public List<Matched> returnMatched() {
        return matches;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public List<PartnerRequest> getPartnerRequests() {
        return partnerRequests;
    }

    public void makePartnerRequst(String from,String to){
        PartnerRequest partnerRequest = new PartnerRequest(from,to);
        partnerRequests.add(partnerRequest);
    }

}
