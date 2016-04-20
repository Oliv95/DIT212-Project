package se.ice.client.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import se.ice.client.R;
import se.ice.client.utility.Domain;
import se.ice.client.utility.MockupServer;

/**
 * Created by Simon on 2016-04-20.
 */
public class UserSwipeActivity extends Activity {

    private final Domain domain = MockupServer.getInstance();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_swipe);

        populateData();
    }

    private void populateData() {
        //TODO initiate the state
    }

    public void no(View view) {
        //TODO Send data to the server
    }

    public void yes(View view) {
        //TODO Send data to the server
    }

}
