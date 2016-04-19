package se.ice.client.utility;

public interface Domain {

    public String createUser(String email,String name,String password);

    public String createAdmin(String email,String name,String password);

    public Gcode createCourse(String name, String admin);

    public boolean joinCourse(Gcode generatedCode, String user);

    public void matchRequest(String sender, String receiver, Gcode generatedCode);

    public User getUser(String email);

    public User[] getAllUsers(Gcode generatedCode);

    public User[] getMatchedWithMe(String email,Gcode generatedCode);

    Course getCourse(Gcode courseCode);

    Admin getAdmin(String email);
}
