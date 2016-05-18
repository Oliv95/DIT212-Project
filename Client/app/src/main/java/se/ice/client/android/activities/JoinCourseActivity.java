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
import se.ice.client.utility.MockupServer;

public class JoinCourseActivity extends AppCompatActivity implements View.OnClickListener {

    MockupServer server = (MockupServer) MockupServer.getInstance();
    EditText codeView;
    Button joinButton;
    Toolbar t;

    // Used for logging
    private static final String TAG = "JoinCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        codeView = (EditText) findViewById(R.id.course_join_code);
        joinButton = (Button) findViewById(R.id.course_create_button);
        joinButton.setOnClickListener(this);

        t = (Toolbar) findViewById(R.id.main_toolbar);
        t.setTitle("Join course");
        setSupportActionBar(t);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(joinButton)){
            Editable courseCode = codeView.getText();
            boolean joined = server.joinCourse(courseCode.toString(),"admin@mail.com");
            Log.d(TAG, joined + " courseCode");
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
