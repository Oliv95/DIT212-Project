package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.ice.client.R;
import se.ice.client.models.Course;
import se.ice.client.models.PartnerRequest;
import se.ice.client.models.User;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.ServerRequestService;

public class ReceivedPartnerRequestActivity extends AppCompatActivity{

    ListView userList;
    CurrentSession currentSession = CurrentSession.getInstance();
    Domain server = new ServerRequestService();
    ArrayAdapter<String> arrayAdapter;
    HashMap<Integer,String> itemToGcode;
    HashMap<Integer,String> itemToEmail;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved_partner_requests);

        userList = (ListView) findViewById(R.id.received_partner_request_list);

    }

    private void populateData(){
        List<Course> courses = server.getEnrolledIn(currentSession.getEmail());
        List<String> courseAndPartners = new ArrayList<>();
        itemToEmail = new HashMap<>();
        itemToGcode =new HashMap<>();
        int counter = 0;
        for(Course c : courses){
            for(PartnerRequest pr : c.getPartnerRequests()){
                if(pr.getTo().equals(currentSession.getEmail())){
                    User from = server.getUser(pr.getFrom());
                    courseAndPartners.add(c.getName() + "   " + from.getName());
                    itemToEmail.put(counter,from.getEmail());
                    itemToGcode.put(counter,c.getCode().toString());
                    counter++;
                }
            }
        }
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                startProfileActivity(itemToGcode.get(position),itemToEmail.get(position),"Yes");
            }
        });

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courseAndPartners);
        userList.setAdapter(arrayAdapter);

    }
    @Override
    public void onResume(){
        super.onResume();
        populateData();
    }

    public void startProfileActivity(String gcode, String email, String button){
        Intent i = new Intent(this, PartnerRequestProfileActivity.class);
        i.putExtra("gcode", gcode);
        i.putExtra("email", email);
        i.putExtra("button", button);
        startActivity(i);
    }
}
