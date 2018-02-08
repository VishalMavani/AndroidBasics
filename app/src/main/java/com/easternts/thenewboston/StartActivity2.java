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
public class StartActivity2 extends Activity implements View.OnClickListener{
    TextView tvDisplayName, tvDisplayGender;
    Button exit;
    String fullName, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity2);
        initialize();
        Bundle bundle = getIntent().getExtras();
//        System.out.println("name :::::" + bundle.getString("fullName"));
//        System.out.println("GENDER :::::" + bundle.getString("genderType"));
        fullName = bundle.getString("fullName");
        gender = bundle.getString("genderType");
        tvDisplayName.setText(fullName);
        tvDisplayGender.setText(gender);
    }

    private void initialize() {
        tvDisplayName = (TextView) findViewById(R.id.tvDisplayName);
        tvDisplayGender = (TextView) findViewById(R.id.tvDisplayGender);
        exit = (Button) findViewById(R.id.bExit);
        exit.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(StartActivity2.this, ListActivityDemo.class);
        startActivity(i);
    }
}
