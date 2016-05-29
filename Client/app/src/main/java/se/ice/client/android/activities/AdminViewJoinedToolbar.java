package se.ice.client.android.activities;

import android.os.Bundle;
import android.widget.Button;

import se.ice.client.R;

/**
 * Created by niclas on 2016-05-25.
 */
public class AdminViewJoinedToolbar extends ToolBarActivity {

    private Button allButton;
    private Button partnersButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_joined_toolbar);

        allButton = (Button) findViewById(R.id.admin_view_joined_all);
        partnersButton= (Button) findViewById(R.id.admin_view_joined_partners);


    }
}
