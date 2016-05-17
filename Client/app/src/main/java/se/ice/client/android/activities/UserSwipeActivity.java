package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import se.ice.client.R;
import se.ice.client.models.User;
import se.ice.client.utility.Domain;
import se.ice.client.utility.Gcode;
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
    private int next = 1;

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


    }

    public void no(View view) {
        nextUser();
    }

    public void yes(View view) {

        //TODO need the user's email from login
        //domain.sendMatchRequest()
        nextUser();
        if (users.size() > 0) {
            users.remove(next - 1);
        }

    }

    private boolean nextUser() {
        if(next < users.size()) {
            name.setText(users.get(next).getName());
            next++;
        } else {
            next = 0;
            name.setText(users.get(next).getName());
            next++;
        }
        return true;
    }

}
