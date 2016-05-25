package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
public class UserSwipeActivity extends Activity {

    /**
     * View elements
     */
    private Button noButton;
    private Button yesButton;
    private TextView name;

    /**
     * Class variables
     */
    private String course;
    private List<User> users;
    private int currentUser = 0;

    private final Domain domain = new ServerRequestService();
    private CurrentSession currentSession = CurrentSession.getInstance();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_swipe);

        noButton = (Button) findViewById(R.id.noButton);
        yesButton = (Button) findViewById(R.id.yesButton);
        name = (TextView) findViewById(R.id.name);

        Intent intent = getIntent();

        course =  (String) intent.getExtras().get("gcode");

        populateData();
    }

    private void populateData() {

        users = domain.getNotMatchedWith(currentSession.getEmail(), course);

        if(!users.isEmpty()) {
            Log.d("Number of Users: ", String.valueOf(users.size()));
            yesButton.setVisibility(View.VISIBLE);
            noButton.setVisibility(View.VISIBLE);
            name.setText(users.get(0).getName());
            currentUser = 0;
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
        Log.i("currentUser", users.get(currentUser).getEmail());
        domain.sendMatchRequest(currentSession.getEmail(), users.get(currentUser).getEmail(), course);
        nextUser();

    }

    private boolean nextUser() {

        if (currentUser+1 < users.size()) {
            name.setText(users.get(currentUser+1).getName());
            currentUser++;
        } else {
            currentUser = 0;
            populateData();
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();



    }

}
