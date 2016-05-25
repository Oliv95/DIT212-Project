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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;

import se.ice.client.R;
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

        Intent intent = getIntent();

        course =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");

        t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle(courseName + "     " + course);
        setSupportActionBar(t);

        populateData();
    }

    private void populateData() {

        users = domain.getNotMatchedWith(currentSession.getEmail(), course);

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

    @Override
    public void onResume() {
        super.onResume();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
