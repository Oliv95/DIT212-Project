package se.ice.client.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.Constants;
import se.ice.client.utility.MockupServer;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button loginButton;
    MockupServer server = (MockupServer) MockupServer.getInstance();
    EditText emailField;
    EditText passwordField;
    TextView incorrectPassword;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.login_email);
        passwordField = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_login_button);
        loginButton.setOnClickListener(this);
        incorrectPassword = (TextView) findViewById(R.id.login_incorrect_password);
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

                Intent i = new Intent(this,ProfileActivity.class);
                i.putExtra(new String("email"),email);
                startActivity(i);
            }else{
                incorrectPassword.setText("incorrect username or password");
            }
        }
    }
}
