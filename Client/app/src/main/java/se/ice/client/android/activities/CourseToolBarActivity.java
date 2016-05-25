package se.ice.client.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import se.ice.client.R;

/**
 * Created by Simon on 2016-05-25.
 */
public class CourseToolBarActivity extends ToolBarActivity {

    private Button swipeButton;
    private Button partnerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_toolbar);

        swipeButton = (Button) findViewById(R.id.swipeButton);
        partnerButton= (Button) findViewById(R.id.partnerButton);


    }


}
