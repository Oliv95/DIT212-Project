package se.ice.client.utility;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public class ServerRequestService extends Service implements Domain {

    private final String server = "localhost:8080";
    private final String users = "/users";
    private final String courses = "/courses";
    private final String admins = "/admins";
    private final String charset = "UTF-8";

    public ServerRequestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String createUser(String email, String name, String password) {
        try {
            URL url = new URL(String.format(server + users + "name=%s&email=%s&password=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)));

            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readValue(url, JsonNode.class);

            return node.path("email").asText();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String createAdmin(String email, String name, String password) {
        try {
            URL url = new URL(String.format(server + admins + "name=%s&email=%s&password=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)));

            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readValue(url, JsonNode.class);

            return node.path("email").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Gcode createCourse(String courseName, String adminEmail) {

        try {
           URL url = new URL(String.format(server + courses + "name=%s&email=%s",
                    URLEncoder.encode(courseName, charset),
                    URLEncoder.encode(adminEmail, charset)));



        ObjectMapper mapper = new ObjectMapper();

        Gcode courseCode = mapper.readValue(url, Gcode.class);
            return courseCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public boolean joinCourse(String generatedCourseCode, String user) {
        try {
            URL url = new URL(String.format(server + users + "name=%s&gcode=%s",
                    URLEncoder.encode(user, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();


            JsonNode node = mapper.readValue(url, JsonNode.class);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean sendMatchRequest(String senderEmail, String receiverEmail, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "sender=%s&receiver=%s&gcode=%s",
                    URLEncoder.encode(senderEmail, charset),
                    URLEncoder.encode(receiverEmail, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();


            JsonNode node = mapper.readValue(url, JsonNode.class);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getMatchedWith(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + courses + "email=%sgcode=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            // This should convert to a list with User
            List<User> user = mapper.readValue(url, List.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public User getUser(String email) {
        try {
            URL url = new URL(String.format(server + users + "name=%s",
                    URLEncoder.encode(email, charset)));

            ObjectMapper mapper = new ObjectMapper();


            User user = mapper.readValue(url, User.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers(String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + courses + "gcode=%s",
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            // This should convert to a list with User
            List<User> user = mapper.readValue(url, List.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<User> getMatchedWithMe(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "email=%s&gcode=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            // This should convert to a list with User
            List<User> user = mapper.readValue(url, List.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Course getCourse(String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + courses + "gcode=%s",
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            Course course = mapper.readValue(url, Course.class);

            return course;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin getAdmin(String email) {
        try {
            URL url = new URL(String.format(server + admins + "email=%s",
                    URLEncoder.encode(email, charset)));

            ObjectMapper mapper = new ObjectMapper();

            Admin admin = mapper.readValue(url, Admin.class);

            return admin;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init() {
        //TODO What is this, why is it in Domain?
    }

    @Override
    public boolean login(String email, String password) {
        try {
            URL url = new URL(String.format(server + users + "email=%s&password=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)));

            ObjectMapper mapper = new ObjectMapper();


            JsonNode node = mapper.readValue(url, JsonNode.class);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendPartnerRequest(String generatedCourseCode, String fromEmail, String toEmail) {
        try {
            URL url = new URL(String.format(server + users + "from=%s&to=%s&gcode=%s",
                    URLEncoder.encode(fromEmail, charset),
                    URLEncoder.encode(toEmail, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();


            JsonNode node = mapper.readValue(url, JsonNode.class);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getPartnerRequest(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "email=%s&gcode=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            // This should convert to a list with User
            List<User> user = mapper.readValue(url, List.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean respondToPartner(String fromEmail, String toEmail, String generatedCourseCode, boolean response) {
        try {
            URL url = new URL(String.format(server + users + "from=%s&to=%s&gcode=%s&response=%s",
                    URLEncoder.encode(fromEmail, charset),
                    URLEncoder.encode(toEmail, charset),
                    URLEncoder.encode(generatedCourseCode, charset),
                    URLEncoder.encode(Boolean.toString(response), charset)));

            ObjectMapper mapper = new ObjectMapper();


            JsonNode node = mapper.readValue(url, JsonNode.class);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getPartner(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "email=%s&gcode=%s",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

            ObjectMapper mapper = new ObjectMapper();

            // This should convert to a list with User
            User user = mapper.readValue(url, User.class);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
