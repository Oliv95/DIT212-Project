package se.ice.client.android.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class CoursesActivity extends AppCompatActivity implements View.OnClickListener {

    MockupServer server = (MockupServer) MockupServer.getInstance();
    Button createButton;
    Button joinButton;
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        createButton = (Button) findViewById(R.id.courses_create_button);
        joinButton = (Button) findViewById(R.id.courses_join_button);
        createButton.setOnClickListener(this);
        joinButton.setOnClickListener(this);

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
        t.setTitle("ICE");
        setSupportActionBar(t);
    }


    @Override
    public void onClick(View view) {
        Intent i;
        if(view.equals(createButton)){
            i = new Intent(this, CreateCourseActivity.class);
            startActivity(i);
        }else if(view.equals(joinButton)){
            i = new Intent(this, JoinCourseActivity.class);
            startActivity(i);
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                return true;
            /* When in course activity we dont want to start a new courses activity
            case R.id.menu_courses:
                i = new Intent(this, CoursesActivity.class);
                startActivity(i);
                return true;
            */
            case R.id.menu_log_out:
                i= new Intent(getApplicationContext(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
