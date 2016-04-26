package se.ice.client.utility;

import java.util.List;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public interface Domain {

    String createUser(String email,String name,String password);

    String createAdmin(String email,String name,String password);

    Gcode createCourse(String courseName, String adminEmail);

    boolean joinCourse(String generatedCourseCode, String user);

    boolean sendMatchRequest(String senderEmail, String receiverEmail, String generatedCourseCode);

    List<User> getMatchedWith(String email, String generatedCourseCode);

    User getUser(String email);

    List<User> getAllUsers(String generatedCourseCode);

    List<User> getMatchedWithMe(String email,String generatedCourseCode);

    Course getCourse(String generatedCourseCode);

    Admin getAdmin(String email);

    boolean login(String email, String password);

    boolean sendPartnerRequest(String generatedCourseCode, String fromEmail, String toEmail);

    List<User> getPartnerRequest(String email, String generatedCourseCode);

    boolean respondToPartner(String fromEmail, String toEmail, String generatedCourseCode, boolean response);

    User getPartner(String email, String generatedCourseCode);


}
