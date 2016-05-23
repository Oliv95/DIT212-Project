package se.ice.client.utility;

public class Gcode {
    private int id;

    public Gcode(){
    }

    public Gcode(int id) {
        this.id = id;
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
    public String toString(){
        return this.id + "";
    }
}
