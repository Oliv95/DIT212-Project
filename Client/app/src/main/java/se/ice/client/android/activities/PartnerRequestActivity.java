package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.ice.client.R;
import se.ice.client.models.Course;
import se.ice.client.models.User;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.Gcode;
import se.ice.client.utility.ServerRequestService;

/**
 * Created by Simon on 2016-05-25.
 */
public class PartnerRequestActivity extends AppCompatActivity implements View.OnClickListener{
    CurrentSession currentSession = CurrentSession.getInstance();
    Domain server = new ServerRequestService();
    Button createButton;
    Button joinButton;
    ListView courseList;
    TextView status;
    ArrayAdapter<String> arrayAdapter;
    HashMap<Integer,Gcode> itemToGcode = new HashMap<>();
    Toolbar t;

    private String course;
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_request);

        Intent intent = getIntent();

        course =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");

        courseList = (ListView) findViewById(R.id.course_list);


        t = (Toolbar)findViewById(R.id.course_toolbar);
        t.setTitle("Courses");
        setSupportActionBar(t);

        populateUserData();

    }

    private void populateUserData(){

        List<User> enrolled = server.getMatchedWith(currentSession.getEmail(), course);
        List<String> courseNames = new ArrayList<>();
        int counter = 0;
        itemToGcode.clear();
        for(User c : enrolled){
            courseNames.add(c.getName().toUpperCase());
            itemToGcode.put(counter, new Gcode(Integer.parseInt(course)));
            counter++;
        }

        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                //TODO
            }
        });
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courseNames);
        courseList.setAdapter(arrayAdapter);
    }






    @Override
    public void onClick(View view) {
        Intent i;
        if(view.equals(createButton) && currentSession.isAdmin()){
            i = new Intent(this, CreateCourseActivity.class);
            startActivity(i);
        }else if(view.equals(createButton) && !currentSession.isAdmin()){
            status.setText("you need admin priviliges");
        }
        else if(view.equals(joinButton) && !currentSession.isAdmin()){
            i = new Intent(this, JoinCourseActivity.class);
            startActivity(i);
        } else if(view.equals(joinButton) && currentSession.isAdmin()){
            status.setText("admin can't join courses");
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
            case R.id.menu_courses:
                //When in course activity we don't want to start a new courses activity
                i = new Intent(this, CoursesActivity.class);
                startActivity(i);
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

    public void toSwipe(View view) {
        Intent intent = new Intent(this, UserSwipeActivity.class);
        intent.putExtra("gcode", course);
        intent.putExtra("name", courseName);
        startActivity(intent);
    }

    public void toPartner(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        populateUserData();
    }
}
