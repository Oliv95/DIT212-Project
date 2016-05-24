package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.MockupServer;
import se.ice.client.utility.ServerRequestService;

public class ProfileActivity extends AppCompatActivity{

    TextView emailView;
    TextView nameView;
    TextView phoneView;
    Domain server = new ServerRequestService();
    CurrentSession currentSession = CurrentSession.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Log.i("START: ", "ProfileActivity");

        emailView = (TextView) findViewById(R.id.profile_email);
        nameView = (TextView) findViewById(R.id.profile_name);
        phoneView = (TextView) findViewById(R.id.profile_phone);

        populateData();

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle(currentSession.getName());
        setSupportActionBar(t);
    }

    public void populateData(){
        emailView.setText(currentSession.getEmail());
        nameView.setText(currentSession.getName());
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


}
