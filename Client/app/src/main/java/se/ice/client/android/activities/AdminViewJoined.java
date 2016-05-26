package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.ice.client.R;
import se.ice.client.models.Course;
import se.ice.client.models.Partner;
import se.ice.client.models.User;
import se.ice.client.utility.Domain;
import se.ice.client.utility.ServerRequestService;

public class AdminViewJoined extends AppCompatActivity implements View.OnClickListener{

    Domain server = new ServerRequestService();

    private String courseCode;
    private  String courseName;
    ListView userList;
    ArrayAdapter<String> arrayAdapter;
    Button allButton;
    Button partnerButton;
    Toolbar t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_joined);

        Intent intent = getIntent();
        courseCode =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");
        userList = (ListView) findViewById(R.id.user_list);
        allButton = (Button) findViewById(R.id.admin_view_joined_all);
        partnerButton = (Button) findViewById(R.id.admin_view_joined_partners);

        allButton.setOnClickListener(this);
        partnerButton.setOnClickListener(this);

        t = (Toolbar)findViewById(R.id.admin_view_joined_toolbar);
        t.setTitle(courseName + "    " + courseCode);
        setSupportActionBar(t);

        populateAllUserData();
    }

    private void populateAllUserData(){
        List<User> users = server.getAllUsers(courseCode);
        List<String> userNames = new ArrayList<>();

        for(User u : users){
            userNames.add(u.getName() + "  " + u.getEmail());
        }

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, userNames);
        userList.setAdapter(arrayAdapter);
    }

    private void populatePartnerData(){
        List<String> partnerUserNames = new ArrayList<>();
        Course c = server.getCourse(courseCode);
        List<Partner> partners = c.getPartners();
        for(Partner p : partners){
            partnerUserNames.add(p.getMembers().get(0) + "   " + p.getMembers().get(1));
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, partnerUserNames);
        userList.setAdapter(arrayAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
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
                i = new Intent(this, CoursesActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_log_out:
                i= new Intent(getApplicationContext(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            case R.id.menu_requests:
                i = new Intent(this, ReceivedPartnerRequestActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View view) {
        if(view.equals(allButton)){
            populateAllUserData();
        }else if(view.equals(partnerButton)){
            populatePartnerData();
        }
    }
}
