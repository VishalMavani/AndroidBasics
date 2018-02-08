package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 05-07-2016.
 */
public class StartActivityForResult extends Activity implements View.OnClickListener {
    TextView displayName, displayGender;
    Button start;
    final static int requestCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_for_result);
        initialize();
    }

    private void initialize() {
        displayName = (TextView) findViewById(R.id.tvDisplayName);
        displayGender = (TextView) findViewById(R.id.tvDisplayGender);
        start = (Button) findViewById(R.id.bStart);
        start.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            displayName.setText("Your Name is: " + bundle.getString("fullName"));
            displayGender.setText("Your gender is: " + bundle.getString("genderType"));
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(StartActivityForResult.this, StartActivityForResult2.class);
        Bundle bundle = new Bundle();
        bundle.putString("callerActivity", "SartActivityForResult");
        i.putExtras(bundle);
        startActivityForResult(i, requestCode);
    }
}
