package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class CreateCourseActivity extends Activity implements View.OnClickListener {

    TextView courseName;
    Button createButton;
    MockupServer server = new MockupServer();

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
        server.createAdmin(MockupServer.email,"test_course",MockupServer.password);
    }
}