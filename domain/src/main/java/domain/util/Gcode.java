package domain.util;

import java.io.Serializable;

/**
 * class for generating a random coursecode.
 * Created by robertkrook 4/14/16.
 */
public class Gcode implements Serializable{
    private static int counter = 0;
    private int id;

    public Gcode(){
        counter++;
        id = counter;
    }

    private Gcode(int id) {
        this.id = id;
    }

    public static Gcode fromString(String id) {
        int parsed = Integer.parseInt(id);
        if(0 < parsed && parsed <= counter) {
            return new Gcode(parsed);
        } else {
            return null;
        }
    }

    public String toString() {
        return id + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gcode gcode = (Gcode) o;

        return id == gcode.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static Gcode fromString(int i){
        Gcode result = new Gcode();
        result.id = i;
        return result;
    }
}
