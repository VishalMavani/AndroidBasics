package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 04-07-2016.
 */
public class ActivityDemo extends Activity implements View.OnClickListener {
    EditText fullName;
    TextView displayName;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initialize();

    }

    @Override
    public void onClick(View v) {
        displayName.setText("Hello " + fullName.getText());
//        try {
//            Class myClass = Class.forName("com.easternts.thenewboston.StartingPoint");
//            Intent startingPoint = new Intent(ActivityDemo.this, myClass);
//            startActivity(startingPoint);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        Intent startingPoint = new Intent(ActivityDemo.this, StartingPoint.class);
        startActivity(startingPoint);
    }
    public void initialize() {
        fullName = (EditText) findViewById(R.id.etFullName);
        displayName = (TextView) findViewById(R.id.tvDisplayName);
        enter = (Button) findViewById(R.id.bEnter);
        enter.setOnClickListener(this);
    }
}
