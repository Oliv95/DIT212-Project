package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import se.ice.client.R;
import se.ice.client.models.User;
import se.ice.client.utility.Constants;
import se.ice.client.utility.Domain;
import se.ice.client.utility.MockupServer;

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
    private String email;

    private final Domain domain = MockupServer.getInstance();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_swipe);

        populateData();
    }

    private void populateData() {

        Intent intent = getIntent();

        course =  (String) intent.getExtras().get("course");

        users = domain.getAllUsers(course);

        name.setText(users.get(0).getName());

        SharedPreferences settings = getSharedPreferences(Constants.SETTINGS_FILE, 0);

        email = settings.getString(Constants.EMAIL_FIELD, "");

    }

    public void no(View view) {
        nextUser();
    }

    public void yes(View view) {
        domain.sendMatchRequest(email, users.get(currentUser).getEmail(), course);
        nextUser();

    }

    private boolean nextUser() {
        if(currentUser < users.size()) {
            name.setText(users.get(currentUser).getName());
            currentUser++;
        } else {
            currentUser = 0;
            name.setText(users.get(currentUser).getName());
            currentUser++;
        }
        if (users.size() > 0) {
            users.remove(currentUser - 1);
        }
        return true;
    }

}
