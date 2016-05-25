package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;

public class AdminViewJoined extends AppCompatActivity {

    private String course;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_swipe);


        Intent intent = getIntent();
        course =  (String) intent.getExtras().get("gcode");
    }

    private void populateData(){

    }



}
