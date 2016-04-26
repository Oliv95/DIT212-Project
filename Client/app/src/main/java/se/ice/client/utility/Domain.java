package se.ice.client.utility;

import java.util.List;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public interface Domain {

    public String createUser(String email,String name,String password);

    public String createAdmin(String email,String name,String password);

    public Gcode createCourse(String courseName, String adminEmail);

    public boolean joinCourse(String generatedCourseCode, String user);

    public boolean matchRequest(String sender, String receiver, String generatedCourseCode);

    public User getUser(String email);

    public List<User> getAllUsers(String generatedCourseCode);

    public List<User> getMatchedWithMe(String email,String generatedCourseCode);

    Course getCourse(String generatedCourseCode);

    Admin getAdmin(String email);
}
