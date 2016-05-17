package se.ice.client.android.activities;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class CoursesActivity extends AppCompatActivity implements View.OnClickListener {

    MockupServer server = (MockupServer) MockupServer.getInstance();
    Button createButton;
    Button joinButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        createButton = (Button) findViewById(R.id.courses_create_button);
        joinButton = (Button) findViewById(R.id.courses_join_button);
        createButton.setOnClickListener(this);
        joinButton.setOnClickListener(this);
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
}
