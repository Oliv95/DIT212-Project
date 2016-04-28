package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class RegisterActivity extends Activity implements View.OnClickListener {

    EditText mail;
    EditText name;
    EditText password1;
    EditText password2;
    Button registerButton;

    MockupServer server = (MockupServer) MockupServer.getInstance();

    // Used for log
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail = (EditText) findViewById(R.id.register_email);
        name = (EditText) findViewById(R.id.register_name);
        password1 = (EditText) findViewById(R.id.register_password1);
        registerButton = (Button) findViewById(R.id.register_register_button);
        registerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.equals(registerButton)){
            String pw1 = ((Editable) password1.getText()).toString();
            String pw2 = ((Editable) password2.getText()).toString();
            if( pw1.equals(pw2) ){
                server.createUser(mail.getText().toString(), name.getText().toString(),
                        password1.getText().toString());
            }else{

            }

        }
    }
}
