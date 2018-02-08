package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by Bansi..!! on 05-07-2016.
 */
public class StartActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText fullName;
    RadioGroup gender;
    String genderType;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        initialize();
    }

    private void initialize() {
        fullName = (EditText) findViewById(R.id.etFullName);
        gender = (RadioGroup) findViewById(R.id.rgGender);
        submit = (Button) findViewById(R.id.bSubmit);
        submit.setOnClickListener(this);
        gender.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(StartActivity.this, StartActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName.getText().toString());
        bundle.putString("genderType", genderType);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbMale:
                genderType = "Male";
                System.out.println("male checked");
                break;
            case R.id.rbFemale:
                genderType = "Female";
                System.out.println("Female checked");
                break;
        }
    }
}
