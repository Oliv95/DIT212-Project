package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.models.User;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.ServerRequestService;

/**
 * Created by Simon on 2016-05-26.
 * This activity Requires an intent with 3 extras: a String named "email" and String with a gcode named "gcode" and String with button text named "button"
 */
public class PartnerRequestProfileActivity extends AppCompatActivity {

    TextView emailView;
    TextView nameView;
    Button requestButton;
    private User toUser;
    private String gcode;

    Domain server = new ServerRequestService();
    CurrentSession currentSession = CurrentSession.getInstance();
    private String buttonText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_request_profile);

        emailView = (TextView) findViewById(R.id.profile_email);
        nameView = (TextView) findViewById(R.id.profile_name);
        requestButton = (Button) findViewById(R.id.requestButton);

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle(currentSession.getName());
        setSupportActionBar(t);

        Intent intent = getIntent();
        String email = (String) intent.getExtras().get("email");
        gcode = (String) intent.getExtras().get("gcode");
        buttonText = (String) intent.getExtras().get("button");
        toUser = server.getUser(email);

        populateData();
    }

    public void populateData(){
        emailView.setText(toUser.getEmail());
        nameView.setText(toUser.getName());
        requestButton.setText(buttonText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_profile:
                // When in profile we dont want to start a new profile activity
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                finish();
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
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onRequestButtonClick(View view) {
        server.sendPartnerRequest(gcode, currentSession.getEmail(), toUser.getEmail());
        onBackPressed();
    }
}
