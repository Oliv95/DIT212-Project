package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class JoinCourseActivity extends Activity implements View.OnClickListener {

    MockupServer server = (MockupServer) MockupServer.getInstance();
    EditText codeView;
    Button joinButton;

    // Used for logging
    private static final String TAG = "JoinCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        codeView = (EditText) findViewById(R.id.course_join_code);
        joinButton = (Button) findViewById(R.id.course_create_button);
        joinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(joinButton)){
            Editable courseCode = codeView.getText();
            boolean joined = server.joinCourse(courseCode.toString(),"admin@mail.com");
            Log.d(TAG, joined + " courseCode");
        }
    }
}
