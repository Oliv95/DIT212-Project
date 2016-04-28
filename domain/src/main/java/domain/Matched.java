package domain;

import domain.util.Gcode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class keeps track of who is matched with who, and for what course.
 * Objects of this class should only be instantiated if *both* users have agreed to work with eachother.
 *
 * Class is using a list instead of simple user1 and user2 variables to simplify extending groups from 2 to more members later.
 * Created by robertkrook on 4/13/16.
 */
public class Matched implements Serializable{
    private String member1;
    private String member2;

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
}
