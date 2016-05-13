package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    MockupServer server = (MockupServer) MockupServer.getInstance();
    EditText emailField;
    EditText passwordField;
    Button registerButton;
    TextView statusView;
    Toolbar t;


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
        Log.d("TAG", "onClick: ");
        System.out.println("onClick");
        if(view.equals(loginButton)){
            System.out.println("onClick login");
            if(server.login(emailField.toString(),password.toString())){
                String email = emailField.toString();
                Intent i = new Intent(this,ProfileActivity.class);
                i.putExtra(new String("email"),email);
                startActivity(i);
            }else{
                statusView.setText("incorrect username or password");
            }
        }
        if(view.equals(registerButton)){
            System.out.println("onClick register");
            Intent i = new Intent(this,RegisterActivity.class);
            startActivityForResult(i,1);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
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
}
