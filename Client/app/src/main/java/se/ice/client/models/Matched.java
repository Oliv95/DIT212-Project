package se.ice.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016-05-25.
 */
public class Matched {

    @JsonProperty("members")
    private List<String> members;

    private String member1;
    private String member2;

    public Matched() {}

    public Matched(String user1, String user2) {
        member1 = user1;
        member2 = user2;
    }

    /**
     * @return a list containing all the members in this match
     */
    public List<String> getMembers() {
        List<String> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matched other = (Matched) o;
        boolean result = false;

        //It does not mather in what order the members are stored
        result = member1.equals(other.member1) || member1.equals(other.member2);
        result = result && member2.equals(other.member1) || member2.equals(other.member2);
        return result;
    }

    @Override
    public int hashCode() {
        //TODO
        return 42;
    }
}
