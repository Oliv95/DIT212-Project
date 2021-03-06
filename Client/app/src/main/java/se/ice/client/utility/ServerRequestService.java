package se.ice.client.utility;

import android.os.AsyncTask;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import se.ice.client.models.Admin;
import se.ice.client.models.Course;
import se.ice.client.models.Partner;
import se.ice.client.models.User;

public class ServerRequestService implements Domain {

    private final String server = "http://10.0.2.2:8080";
    private final String users = "/users";
    private final String user = "/user";
    private final String courses = "/courses";
    private final String course = "/course";
    private final String admins = "/admins";
    private final String admin = "/admin";
    private final String charset = "UTF-8";

    public ServerRequestService() {
    }

    private class AsyncCall extends AsyncTask {

        @Override
        public Object doInBackground(Object... params) {
            ObjectMapper mapper = new ObjectMapper();

            Log.i("AsyncCall obj: ", params[1].getClass().getName());

            try {
                return mapper.readValue((URL) params[0], params[1].getClass());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private class AsyncPostCall extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                URL url = (URL) params[0];
                String content = (String) params[1];
                Object currentClass = params[2];
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                String type = "application/x-www-form-urlencoded; charset=UTF-8";

                connection.setRequestProperty( "Content-Type", type );
                connection.setRequestProperty( "Content-Length", String.valueOf(content.length()));


                connection.setRequestMethod("POST");

                connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

                ObjectMapper mapper = new ObjectMapper();
                wr.write(content.getBytes());

                wr.flush();

                wr.close();

            // 6. Get the response
            int responseCode = connection.getResponseCode();
            Log.i("AsyncPostCall: ", "Sending 'POST' request to URL : " + url);
            Log.i("Response Code : ", String.valueOf(responseCode));
            Log.i("Class : ", params[1].getClass().getName());

            return mapper.readValue(connection.getInputStream(), currentClass.getClass());

            } catch (IOException e) {
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
            Log.i("getUser", "not crashed yet");
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public String createUser(String email, String name, String password) {
        try {
            String content = URLEncoder.encode(String.format("?name=%s&email=%s&password=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)), charset);

            URL url = new URL(String.format(server + users + String.format("?name=%s&email=%s&password=%s", name, email, password)));

            AsyncTask execute = new AsyncPostCall().execute(url, content, new String());

            return (String) execute.get();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String createAdmin(String email, String name, String password) {
        try {

            String content = URLEncoder.encode(String.format("?name=%s&email=%s&password=%s",
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(password, charset)), charset);

            URL url = new URL(String.format(server + admin + String.format("?name=%s&email=%s&password=%s", name, email, password)));



            String response = (String) new AsyncPostCall().execute(url, content, new String()).get();

            return response;

        } catch (Exception e) {
            return null;

        }


    }

    @Override
    public Gcode createCourse(String courseName, String adminEmail) {

        try {
           URL url = new URL(String.format(server + course + "?name=%s&admin=%s", courseName, adminEmail));

            String content = URLEncoder.encode((String.format("?name=%s&admin=%s",
                    URLEncoder.encode(courseName, charset),
                    URLEncoder.encode(adminEmail, charset))), charset);

        Gcode courseCode = (Gcode) new AsyncPostCall().execute(url, content, new Gcode()).get();

            return courseCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean joinCourse(String generatedCourseCode, String user) {
        try {
            URL url = new URL(String.format(server + course + "/%s/join?email=%s", generatedCourseCode, user));

            String content = URLEncoder.encode(String.format(server + course + "/%s/join?email=%s",
                    URLEncoder.encode(generatedCourseCode, charset),
                    URLEncoder.encode(user, charset)), charset);
            boolean b = (boolean) new AsyncPostCall().execute(url, content, new Boolean(true)).get();

            return b;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean sendMatchRequest(String senderEmail, String receiverEmail, String generatedCourseCode) {
        try {
            URL url = new URL(String.format(server + course + "/%s/match?sender=%s&receiver=%s",
                    generatedCourseCode,
                    senderEmail,
                    receiverEmail));

            String content = URLEncoder.encode(String.format(server + course + "/%s/match?sender=asd&receiver=asd",
                    URLEncoder.encode(generatedCourseCode, charset),
                    URLEncoder.encode(senderEmail, charset),
                    URLEncoder.encode(receiverEmail, charset)), charset);


           boolean secureRespone= (boolean) new AsyncPostCall().execute(url, content, new Boolean(true)).get();

            return secureRespone;

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


            AsyncTask execute = new AsyncCall().execute(url, new String[0]);
            String[] userNames = (String[]) execute.get();

            List<User> users = new ArrayList<>();

            for (String name : userNames) {
                users.add(getUser(name));
            }

            return users;

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



            String[] userStrings = (String[]) new AsyncCall().execute(url, new String[0]).get();

            List<User> users = new ArrayList<>();

            for(String u : userStrings) {
                users.add(getUser(u));
            }

            return users;

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
            User user = getUser(email);

            if(user == null) {
                Log.i("getAdmin", "admin");
                return getAdmin(email).getPassword().equals(password);
            } else {

                return user.getPassword().equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendPartnerRequest(String generatedCourseCode, String fromEmail, String toEmail) {
        try {
            URL url = new URL(String.format(server + course + "/%s/partnerRequest?sender=%s&receiver=%s",
                    generatedCourseCode,
                    fromEmail,
                    toEmail));

            String content = URLEncoder.encode(String.format(server + course + "/%s/partnerRequest?sender=%s&receiver=%s",
                    URLEncoder.encode(generatedCourseCode, charset),
                    URLEncoder.encode(fromEmail, charset),
                    URLEncoder.encode(toEmail, charset)),charset);

            boolean secureResponse= (boolean) new AsyncPostCall().execute(url, content, new Boolean(true)).get();

            return secureResponse;

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
        Log.i("METHOD", "getPartner");
        try {
            Course course = getCourse(generatedCourseCode);

            for(Partner p : course.getPartners()) {
                if(p.getMembers().get(0).equals(email)) {
                        return getUser(p.getMembers().get(1));
                } else if(p.getMembers().get(1).equals(email)) {
                    return getUser(p.getMembers().get(0));
                }
            }

            return null;

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

                AsyncTask execute = new AsyncCall().execute(url, new String[0]);
                String[] userNames = (String[]) execute.get();

            List<User> users = new ArrayList<>();

            for (String name : userNames) {
                users.add(getUser(name));
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> getEnrolledIn(String user) {
        try {
            URL url = new URL(String.format(server + users + "/%s/enrolledin",
                    URLEncoder.encode(user, charset)));

            AsyncTask execute = new AsyncCall().execute(url, new Gcode[0]);

            Gcode[] gcodes = (Gcode[]) execute.get();

            List<Course> courses = new ArrayList<>();

            for (Gcode gcode : gcodes) {
                courses.add(getCourse(gcode.toString()));
            }

            return courses;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Course> getAllAdministratingCourse(String email) {
        try {
            URL url = new URL(String.format(server + admin + "/%s/administrating",
                    URLEncoder.encode(email, charset)));


            Course[] courses = (Course[]) new AsyncCall().execute(url, new Course[0]).get();

            List<Course> courseList = new ArrayList<>();

            for (Course c : courses) {
                courseList.add(c);
            }

            return courseList;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
