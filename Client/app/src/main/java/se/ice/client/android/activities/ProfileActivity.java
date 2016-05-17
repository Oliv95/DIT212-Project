package se.ice.client.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class ProfileActivity extends AppCompatActivity{

    TextView emailView;
    TextView nameView;
    TextView phoneView;
    MockupServer server = (MockupServer) MockupServer.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailView = (TextView) findViewById(R.id.profile_email);
        nameView = (TextView) findViewById(R.id.profile_name);
        phoneView = (TextView) findViewById(R.id.profile_phone);

        populateData();

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle("ICE");
        setSupportActionBar(t);
    }

    public void populateData(){
        emailView.setText(server.getCurrent().getEmail());
        nameView.setText(server.getCurrent().getName());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
