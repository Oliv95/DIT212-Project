package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import se.ice.client.R;

public class AdminViewJoined extends AppCompatActivity {

    private String courseCode;
    private  String courseName;
    Toolbar t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_joined);

        Intent intent = getIntent();
        courseCode =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");
        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle(courseName + "    " + courseCode);
        setSupportActionBar(t);
    }

    private void populateAllUserData(){

    }

    private void populatePartnerData(){

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
