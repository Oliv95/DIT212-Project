package se.ice.client.utility;

public class Gcode {
    private static int counter = 0;
    final private int id;

    public Gcode(){
        counter++;
        id = counter;
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
}
