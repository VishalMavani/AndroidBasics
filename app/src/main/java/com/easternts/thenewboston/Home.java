package com.easternts.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 04-07-2016.
 */
public class Home extends Activity {
    String fullName;
    TextView welcomeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initialize();
        Bundle bundle = getIntent().getExtras();
        fullName = bundle.getString("fullName");
        welcomeName.setText("Welcome " + fullName + "...");
    }

    private void initialize() {
        welcomeName = (TextView) findViewById(R.id.tvWelcomeName);
    }
}
