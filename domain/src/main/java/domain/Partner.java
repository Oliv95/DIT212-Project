package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliv on 5/24/16.
 */
public class Partner implements Serializable{
    private String member1;
    private String member2;

    public Partner(String member1, String member2){
        this.member1 = member1;
        this.member2 = member2;
    }

    public List<String> getMembers(){
        List <String> result = new ArrayList<>();
        result.add(member1);
        result.add(member2);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partner other = (Partner) o;
        boolean result = false;

        //It does not mather in what order the members are stored
        result = member1.equals(other.member1) || member1.equals(other.member2);
        result = result && member2.equals(other.member1) || member2.equals(other.member2);
        return result;

    }

    @Override
    public int hashCode() {
        //TODO
        return 31;
    }
}
