package se.ice.client.utility;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.ArrayList;
import java.util.List;

import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.User;

public class ServerRequestService implements Domain {

    private final String server = "http://10.0.2.2:8080";
    private final String users = "/users";
    private final String courses = "/courses";
    private final String course = "/course";
    private final String admins = "/admins";
    private final String admin = "/admin";
    private final String charset = "UTF-8";

    public ServerRequestService() {
    }

    public class AsyncCall extends AsyncTask {

        @Override
        public Object doInBackground(Object... params) {
            ObjectMapper mapper = new ObjectMapper();

            Log.i("Object name: ", params[1].getClass().getName());

            try {
                return mapper.readValue((URL) params[0], params[1].getClass());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public User getUser(String email) {
        try {
            URL url = new URL(String.format(server + users + "/%s",
                    URLEncoder.encode(email, charset)));

            AsyncTask execute = new AsyncCall().execute(url, new User());

            User user = (User) execute.get();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public String createUser(String email, String name, String password) {
        try {
            URL url = new URL(String.format(server + users + "?name=%s&email=%s&password=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)));

            AsyncTask execute = new AsyncCall().execute(url, new String());

            return (String) execute.get();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
           URL url = new URL(String.format(server + course + "?name=%s&admin=%s",
                    URLEncoder.encode(courseName, charset),
                    URLEncoder.encode(adminEmail, charset)));

        Gcode courseCode = (Gcode) new AsyncCall().execute(url, new Gcode()).get();

            return courseCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean joinCourse(String generatedCourseCode, String user) {
        try {
            URL url = new URL(String.format(server + users + "name=%s&gcode=%s",
                    URLEncoder.encode(user, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));


            new AsyncCall().execute(url, new Boolean(true)).get();

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


            throw new UnsupportedOperationException("Not implemented");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getMatchedWith(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "/%s/%s/matched",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));


            AsyncTask execute = new AsyncCall().execute(url, new ArrayList<User>());

            return (List<User>) execute.get();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public List<User> getAllUsers(String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + course + "/%s/getAllUsers/",
                    URLEncoder.encode(generatedCourseCode, charset)));



            List<User> user = (List<User>) new AsyncCall().execute(url, new ArrayList<User>()).get();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    @Override
    public Course getCourse(String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + course + "/%s",
                    URLEncoder.encode(generatedCourseCode, charset)));

            Course course = (Course) new AsyncCall().execute(url, new Course()).get();

            return course;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin getAdmin(String email) {
        try {
            URL url = new URL(String.format(server + admin + "/%s",
                    URLEncoder.encode(email, charset)));

            ObjectMapper mapper = new ObjectMapper();

            Admin admin = (Admin) new AsyncCall().execute(url, new Admin()).get();

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

          throw new UnsupportedOperationException("This method is not implemented");

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


            throw new UnsupportedOperationException("This method is not implemented");

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

            throw new UnsupportedOperationException("This method is not implemented");


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

            throw new UnsupportedOperationException("This method is not implemented");

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

            throw new UnsupportedOperationException("This method is not implemented");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getNotMatchedWith(String email, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + users + "/%s/%s/notmatched",
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(generatedCourseCode, charset)));

                AsyncTask execute = new AsyncCall().execute(url, new ArrayList<User>());

            return (List<User>) execute.get();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
