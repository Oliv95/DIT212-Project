package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
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

public class AdminViewJoined extends AppCompatActivity {

    Domain server = new ServerRequestService();

    private String courseCode;
    private  String courseName;
    ListView userList;
    ArrayAdapter<String> arrayAdapter;

    Toolbar t;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_joined);

        Intent intent = getIntent();
        courseCode =  (String) intent.getExtras().get("gcode");
        courseName = (String) intent.getExtras().get("name");
        userList = (ListView) findViewById(R.id.user_list);

        Toolbar t = (Toolbar)findViewById(R.id.main_toolbar);
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
}
