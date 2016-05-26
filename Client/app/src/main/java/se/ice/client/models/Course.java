package se.ice.client.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

import se.ice.client.models.User;
import se.ice.client.utility.Gcode;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 */
public class Course {

    private Gcode code;
    private String name;
    private String admin; // email of admin

    @JsonProperty("partnerRequests")
    private List<PartnerRequest> partnerRequests;

    @JsonProperty("listed")
    private List<String> listed;

    @JsonProperty("match_requests")
    private List<MatchRequest> matchRequests;

    @JsonProperty("matches")
    private List<Matched> matches;

    @JsonProperty("partners")
    private List<Partner> partners;


    private List<String> registeredEmails;

    public Course(){}

    public Course(String name, String admin, Gcode gcode, List<String> registeredEmails) {
        this.name = name;
        this.admin = admin;
        code = gcode;
        this.registeredEmails = registeredEmails;
        listed = new ArrayList<>();
    }

    public String getName(){
        return new String(name);
    }

    public Gcode getCode() {
        return code;
    }

    public void registerUser(User user) {
        listed.add(user.getEmail());
    }

    public User[] getRegistered() {
        return (User[]) registeredEmails.toArray();
    }

    public String getAdmin() {
        return admin;
    }

    public List<String> getRegisteredEmails() {
        return registeredEmails;
    }

    public void setRegisteredEmails(List<String> registeredEmails) {
        this.registeredEmails = registeredEmails;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public List<PartnerRequest> getPartnerRequests() {
        return partnerRequests;
    }

    public List<MatchRequest> getMatchRequests() {
        return matchRequests;
    }

    public List<Matched> getMatches() {
        return matches;
    }
}
