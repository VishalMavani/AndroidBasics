package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 06-07-2016.
 */
public class StartActivityForResult2 extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText etFullName;
    TextView tvDisplayCaller;
    RadioGroup rgGender;
    Button bReturn;
    String genderType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_for_result2);
        initialize();
        Bundle bundle = getIntent().getExtras();
        tvDisplayCaller.setText("Caller Activity is: " + bundle.getString("callerActivity"));
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(StartActivityForResult2.this, StartActivityForResult.class);
        Bundle bundle = new Bundle();
        bundle.putString("fullName", etFullName.getText().toString());
        bundle.putString("genderType", genderType);
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        finish();
    }
    private void initialize() {
        etFullName = (EditText) findViewById(R.id.etFullName);
        tvDisplayCaller = (TextView) findViewById(R.id.tvDisplayCaller);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        bReturn = (Button) findViewById(R.id.bReturn);
        bReturn.setOnClickListener(this);
        rgGender.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbMale:
                System.out.println("Male id : " + R.id.rbMale);
                genderType = "Male";
                break;
            case R.id.rbFemale:
                System.out.println("Female id : " + R.id.rbFemale);
                genderType = "Female";
                break;
    }
}
}
