package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.models.Admin;
import se.ice.client.models.User;
import se.ice.client.utility.Constants;
import se.ice.client.utility.CurrentSession;
import se.ice.client.utility.Domain;
import se.ice.client.utility.ServerRequestService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    Domain server = new ServerRequestService();
    EditText emailField;
    EditText passwordField;
    Button registerButton;
    TextView statusView;
    Toolbar t;
    CurrentSession currentSession = CurrentSession.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.login_email);
        passwordField = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_login_button);
        loginButton.setOnClickListener(this);
        registerButton = (Button) findViewById(R.id.login_register);
        registerButton.setOnClickListener(this);
        statusView = (TextView) findViewById(R.id.login_status);

        t = (Toolbar) findViewById(R.id.main_toolbar);
        t.setTitle("ICE");
        setSupportActionBar(t);
    }

    @Override
    public void onClick(View view) {
        Editable emailField = this.emailField.getText();
        Editable password = passwordField.getText();
        if(view.equals(loginButton)){
            if(server.login(emailField.toString(),password.toString())){

                String email = emailField.toString();

                //Saving the email for further use in the app
                SharedPreferences settings = getSharedPreferences(Constants.SETTINGS_FILE, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString(Constants.EMAIL_FIELD,email);
                editor.apply();

                Log.i("Success: ", email);


                User user = server.getUser(email);

                if(user != null) {
                    currentSession.setEmail(user.getEmail());
                    currentSession.setName(user.getName());
                    currentSession.setAdmin(false);
                } else {
                    Admin admin = server.getAdmin(email);
                    currentSession.setEmail(admin.getEmail());
                    currentSession.setName(admin.getName());
                    currentSession.setAdmin(true);
                }

                Intent i = new Intent(this,ProfileActivity.class);
                startActivity(i);
            }else{
                statusView.setText("incorrect username or password");
            }

        }
        if(view.equals(registerButton)){
            Intent i = new Intent(this,RegisterActivity.class);
            startActivityForResult(i,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String status = data.getStringExtra("status");
                statusView.setText(status);
            }
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
}
