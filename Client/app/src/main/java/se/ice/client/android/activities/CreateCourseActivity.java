package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.Gcode;
import se.ice.client.utility.MockupServer;

public class CreateCourseActivity extends Activity implements View.OnClickListener {

    EditText courseName;
    Button createButton;
    MockupServer server = (MockupServer) MockupServer.getInstance();

    // Used for loggin
    private static final String TAG = "CreateCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        courseName = (EditText) findViewById(R.id.create_course_textfield);
        createButton = (Button) findViewById(R.id.course_create_button);
        createButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Editable course = courseName.getText();
        if(view.equals(createButton)){
            Gcode code = server.createCourse(course.toString(),"admin@mail.com");
            Log.d(TAG, code.toString() + " was created");
        }

    }
}