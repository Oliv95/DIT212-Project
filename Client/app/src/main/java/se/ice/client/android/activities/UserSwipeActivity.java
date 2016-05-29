package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.ice.client.R;
import se.ice.client.models.Course;
import se.ice.client.models.MatchRequest;
import se.ice.client.models.Partner;
import se.ice.client.models.User;
import se.ice.client.utility.Constants;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.MockupServer;
import se.ice.client.utility.ServerRequestService;

/**
 * Created by Simon on 2016-04-20.
 * One must send an intent with a bundle that has the course the user is going to find a partner in.
 */
public class UserSwipeActivity extends AppCompatActivity {

    /**
     * View elements
     */
    private Button noButton;
    private Button yesButton;
    private TextView name;
    private TextView statusView;

    /**
     * Class variables
     */
    private String course;
    private String courseName;
    private List<User> users;
    private Iterator<User> iterator;
    private User currentUser;

    private final Domain domain = new ServerRequestService();
    private CurrentSession currentSession = CurrentSession.getInstance();
    Toolbar t;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_swipe);

        noButton = (Button) findViewById(R.id.noButton);
        yesButton = (Button) findViewById(R.id.yesButton);
        name = (TextView) findViewById(R.id.name);
        statusView = (TextView) findViewById(R.id.user_swipe_status);
        t = (Toolbar)findViewById(R.id.course_toolbar);

        Intent intent = getIntent();

        course =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");

        t = (Toolbar)findViewById(R.id.course_toolbar);
        setSupportActionBar(t);

        populateData();
    }

    private void populateData() {

        users = domain.getNotMatchedWith(currentSession.getEmail(), course);

        Course currentCourse = domain.getCourse(course);

        List<Partner> partners = currentCourse.getPartners();
        for (Partner p: partners) {
            User member1 = domain.getUser(p.getMembers().get(0));
            User member2 = domain.getUser(p.getMembers().get(1));

            users.remove(member1);
            users.remove(member2);

        }

        List<MatchRequest> matchRequests = currentCourse.getMatchRequests();

        for (MatchRequest m : matchRequests) {
            boolean bla = m.getFrom().equals(currentSession.getEmail());
            if (bla) {
                users.remove(domain.getUser(m.getTo()));
            }
        }


        if(!users.isEmpty()) {
            Log.d("Number of Users: ", String.valueOf(users.size()));
            yesButton.setVisibility(View.VISIBLE);
            noButton.setVisibility(View.VISIBLE);
            iterator = users.iterator();
            nextUser();
        } else {
            yesButton.setVisibility(View.INVISIBLE);
            noButton.setVisibility(View.INVISIBLE);
            name.setText("You have no more possible matches");
        }

    }

    public void no(View view) {
        nextUser();
    }

    public void yes(View view) {
        domain.sendMatchRequest(currentSession.getEmail(), currentUser.getEmail(), course);
        nextUser();

    }

    private boolean nextUser() {

        if (iterator.hasNext()) {
            User next = iterator.next();
            name.setText(next.getName());
            currentUser = next;
        } else {
            populateData();
        }
        return true;
    }

    public void toSwipe(View view) {

    }

    public void toPartner(View view) {
        Intent intent = new Intent(this, PartnerRequestActivity.class);
        intent.putExtra("gcode", course);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        populateData();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_courses:
                i = new Intent(this, CoursesActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_log_out:
                i= new Intent(getApplicationContext(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            case R.id.menu_requests:
                i = new Intent(this, ReceivedPartnerRequestActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
