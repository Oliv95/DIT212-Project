package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class JoinCourseActivity extends Activity implements View.OnClickListener {

    MockupServer server;
    TextView codeView;
    Button joinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        server = new MockupServer();
        codeView = (TextView) findViewById(R.id.course_join_code);
        joinButton = (Button) findViewById(R.id.course_create_button);
        joinButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.equals(joinButton)){
            String courseCode = (String) codeView.getText();
            server.joinCourse(courseCode,MockupServer.email);
        }
    }
}
