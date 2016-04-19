package domain;

import domain.util.Gcode;

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
public class Matched {
    List<String> matched;
    Gcode course;

    public Matched(String[] partners, Gcode course) {
        matched = Arrays.asList(partners);
        this.course = course;
    }

    /**
     * Just here to simplify testing // Robert
     * @param members
     * @return
     */
    public boolean isEqualTo(String[] members) {
        for(String s : members) {
            if(!matched.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
