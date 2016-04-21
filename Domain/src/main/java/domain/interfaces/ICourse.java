package domain.interfaces;

import domain.Course;
import domain.User;
import domain.util.Gcode;

/**
 * Created by oliv on 4/21/16.
 */
public interface ICourse {

    Gcode createCourse(String name, String admin);

    boolean joinCourse(Gcode generatedCode, String user);

    boolean matchRequest(String sender, String receiver, Gcode generatedCode);

    User[] getAllUsers(Gcode generatedCode);

    User[] getMatchedWithMe(String email,Gcode generatedCode);

    Course getCourse(Gcode courseCode);
}
