package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bansi..!! on 04-07-2016.
 */
public class LogIn extends Activity implements View.OnClickListener{
    EditText fullName;
    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        initialize();
    }

    private void initialize() {
        fullName = (EditText) findViewById(R.id.etFullName);
        enter = (Button) findViewById(R.id.bEnter);
        enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName.getText().toString());
//        Intent home = new Intent(LogIn.this, Home.class);
//        home.putExtras(bundle);
//        startActivity(home);
        Intent listActivityDemo = new Intent(LogIn.this, ListActivityDemo.class);
        listActivityDemo.putExtras(bundle);
        startActivity(listActivityDemo);
    }
}
