package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.Gcode;
import se.ice.client.utility.MockupServer;

public class CreateCourseActivity extends Activity implements View.OnClickListener {

    TextView courseName;
    Button createButton;
    MockupServer server = new MockupServer();
    private static final String TAG = "CreateCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        courseName = (TextView) findViewById(R.id.course_create_textview);
        createButton = (Button) findViewById(R.id.course_create_button);

        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.equals(createButton)){
            Gcode code = server.createCourse("test_course",MockupServer.name);
            Log.d(TAG,code + " was created");
        }

    }
}