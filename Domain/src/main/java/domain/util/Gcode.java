package domain.util;

/**
 * class for generating a random coursecode.
 * Created by robertkrook 4/14/16.
 */
public class Gcode {
    static int counter = 0;
    private int id = 0;

    public Gcode(){
        counter++;
        id = counter;
    }

    public static Gcode makeGcode(int destinationId) {
        Gcode result = new Gcode();
        result.id = destinationId;
        return result;
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

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
