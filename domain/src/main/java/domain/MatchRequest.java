package domain;

/**
 * Class representing one user willing to work with another, not both.
 * Created by robert on 4/19/16.
 */
public class MatchRequest {
    private String from; // this user says he's willing to work with
    private String to; // this user

    public MatchRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
