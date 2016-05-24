package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.Gcode;
import se.ice.client.utility.MockupServer;
import se.ice.client.utility.ServerRequestService;

public class CreateCourseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText courseName;
    Button createButton;
    TextView createSuccessful;
    Toolbar t;
    Domain server = new ServerRequestService();
    CurrentSession currentSession = CurrentSession.getInstance();

    // Used for loggin
    private static final String TAG = "CreateCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        courseName = (EditText) findViewById(R.id.create_course_textfield);
        createButton = (Button) findViewById(R.id.course_create_button);
        createSuccessful = (TextView) findViewById(R.id.course_create_succesful);
        createButton.setOnClickListener(this);

        t = (Toolbar) findViewById(R.id.main_toolbar);
        t.setTitle("Create course");
        setSupportActionBar(t);
    }

    @Override
    public void onClick(View view) {
        Editable course = courseName.getText();
        if(view.equals(createButton)){
            Log.i("CourseName", course.toString());
            Log.i("email", currentSession.getEmail());

            Gcode code = server.createCourse(course.toString(),currentSession.getEmail());

            if(code == null) {
                createSuccessful.setText(course.toString() + " was NOT created");
            } else {
                createSuccessful.setText(course.toString() + " was created with course code " + code.toString());
            }

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
                finish();
                return true;
            case R.id.menu_courses:
                finish();
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


}