package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import se.ice.client.R;

public class ProfileActivity extends Activity{

    TextView emailView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailView = (TextView) findViewById(R.id.profile_email);

        String email = getIntent().getExtras().getString("email");
        emailView.setText(email);
    }

}
