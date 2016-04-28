package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import se.ice.client.R;
import se.ice.client.utility.MockupServer;

public class RegisterActivity extends Activity implements View.OnClickListener {

    EditText mail;
    EditText name;
    EditText password1;
    EditText password2;
    TextView passwordNotMatch;
    Button registerButton;

    //URL url = new URL("localhost:8080/users/");
    HttpURLConnection urlConnection;

    //  MockupServer server = (MockupServer) MockupServer.getInstance();

    // Used for log
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail = (EditText) findViewById(R.id.register_email);
        name = (EditText) findViewById(R.id.register_name);
        password1 = (EditText) findViewById(R.id.register_password1);
        password2 = (EditText) findViewById(R.id.register_password2);
        registerButton = (Button) findViewById(R.id.register_register_button);
        passwordNotMatch = (TextView) findViewById(R.id.register_password_incorrect);
        registerButton.setOnClickListener(this);

        /*
        try {
            URL url = new URL("localhost:8080");

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            writeStream(out);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                urlConnection.disconnect();
            }
            */




    }

    private void writeStream(OutputStream out) {
    }

    private void readStream(InputStream in) {
    }

    @Override
    public void onClick(View view) {

        System.out.println("Trycker ...");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                System.out.println("Making a request");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                System.out.println(statusCode);
                for(Header h : headers){
                    System.out.println(h.getName() + " : " + h.getValue());
                }
                System.out.println(Arrays.toString(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                System.out.println("FAILED");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

        if(view.equals(registerButton)){
            String pw1 = ((Editable) password1.getText()).toString();
            String pw2 = ((Editable) password2.getText()).toString();
            if( pw1.equals(pw2) ){


                /*server.createUser(mail.getText().toString(), name.getText().toString(),
                        pw1);
                Log.d(TAG, name.getText().toString() + " registered");
                */
            }else{
                passwordNotMatch.setText(" Passwords does not match");
            }

        }
    }
}
