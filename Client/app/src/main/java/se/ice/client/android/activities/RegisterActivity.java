package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class RegisterActivity extends Activity implements View.OnClickListener {

    TextView mail;
    TextView name;
    TextView password;
    Button registerButton;

    MockupServer server = (MockupServer) MockupServer.getInstance();

    // Used for log
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail = (TextView) findViewById(R.id.register_email);
        name = (TextView) findViewById(R.id.register_name);
        password = (TextView) findViewById(R.id.register_password);
        registerButton = (Button) findViewById(R.id.register_register_button);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.equals(registerButton)){
            server.createUser((String) mail.getText(), (String) name.getText(), (String)password.getText())
        }
    }
}
