package se.ice.client.utility;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public class ServerRequestService extends Service implements Domain {

    private final String server = "localhost:8080";
    private final String users = "/users";
    private final String courses = "/courses";
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
        return null;
    }

    @Override
    public String createAdmin(String email, String name, String password) {
        return null;
    }

    @Override
    public Gcode createCourse(String courseName, String adminEmail) {

        URL url = null;
        try {
            url = new URL(String.format(server + courses + "name=%s&email=%s",
                    URLEncoder.encode(courseName, charset),
                    URLEncoder.encode(adminEmail, charset)));

        ObjectMapper mapper = new ObjectMapper();

        Gcode courseCode = null;
            courseCode = mapper.readValue(url, Gcode.class);
            return courseCode;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public boolean joinCourse(String generatedCourseCode, String user) {
        return false;
    }

    @Override
    public boolean sendMatchRequest(String senderEmail, String receiverEmail, String generatedCourseCode) {
        return false;
    }

    @Override
    public List<User> getMatchedWith(String email, String generatedCourseCode) {
        return null;
    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers(String generatedCourseCode) {
        return null;
    }

    @Override
    public List<User> getMatchedWithMe(String email, String generatedCourseCode) {
        return null;
    }

    @Override
    public Course getCourse(String generatedCourseCode) {
        return null;
    }

    @Override
    public Admin getAdmin(String email) {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    @Override
    public boolean sendPartnerRequest(String generatedCourseCode, String fromEmail, String toEmail) {
        return false;
    }

    @Override
    public List<User> getPartnerRequest(String email, String generatedCourseCode) {
        return null;
    }

    @Override
    public boolean respondToPartner(String fromEmail, String toEmail, String generatedCourseCode, boolean response) {
        return false;
    }

    @Override
    public User getPartner(String email, String generatedCourseCode) {
        return null;
    }
}
