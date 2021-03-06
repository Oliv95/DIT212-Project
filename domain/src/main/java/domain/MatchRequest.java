package domain;

import java.io.Serializable;

/**
 * Class representing one user willing to work with another, not both.
 * Created by robert on 4/19/16.
 */
public class MatchRequest implements Serializable{
    private String from; // this user says he's willing to work with
    private String to; // this user

    public MatchRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchRequest that = (MatchRequest) o;

        if (!from.equals(that.from)) return false;
        return to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

}
