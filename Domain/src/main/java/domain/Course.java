package domain;

import domain.util.Gcode;

/**
 * This class represents a course, or a 'circle' of people to be matched in groups.
 * Created by robertkrook on 4/13/16.
 */
public class Course {
    private Gcode code;

    public Course(String name, String admin, Gcode gcode) {
        code = gcode;
    }

    public Gcode getCode() {
        return code;
    }
}
