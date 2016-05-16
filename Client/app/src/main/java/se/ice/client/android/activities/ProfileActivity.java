package se.ice.client.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import se.ice.client.R;

public class ProfileActivity extends AppCompatActivity{

    TextView emailView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailView = (TextView) findViewById(R.id.profile_email);

        String email = getIntent().getExtras().getString("email");
        emailView.setText(email);

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle("ICE");
        setSupportActionBar(t);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
