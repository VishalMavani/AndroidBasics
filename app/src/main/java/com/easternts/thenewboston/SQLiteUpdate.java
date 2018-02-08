package com.easternts.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 07-07-2016.
 */
public class SQLiteUpdate extends Activity implements View.OnClickListener {
    EditText firstName, lastName;
    Button updateData;
    String rowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_update);
        initialize();
        Bundle bundle = getIntent().getExtras();
        rowId = bundle.getString("rowID");
        try {
            BostonDatabase bostonDatabase = new BostonDatabase(SQLiteUpdate.this);
            bostonDatabase.openDatabase();
            String[] personalInfo = bostonDatabase.selectPersonalInfoBasedOnID(rowId);
            bostonDatabase.closeDatabase();
            firstName.setText(personalInfo[0]);
            lastName.setText(personalInfo[1]);
        } catch (Exception e) {
            e.printStackTrace();
            Dialog dialog = new Dialog(this);
            dialog.setTitle("Personal Information");
            TextView textView = new TextView(this);
            textView.setText(e.toString());
            dialog.setContentView(textView);
            dialog.show();
        }

    }

    private void initialize() {
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        updateData = (Button) findViewById(R.id.bUpdateData);
        updateData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        BostonDatabase bostonDatabase = new BostonDatabase(SQLiteUpdate.this);
        try {
            bostonDatabase.openDatabase();
            bostonDatabase.updatePersonalInfoBasedOnID(rowId, firstName.getText().toString(), lastName.getText().toString());
            bostonDatabase.closeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            Dialog dialog = new Dialog(this);
            dialog.setTitle("Personal Information");
            TextView textView = new TextView(this);
            textView.setText(e.toString());
            dialog.setContentView(textView);
            dialog.show();
        }
        Intent i = new Intent(SQLiteUpdate.this, SQLiteViewTableData.class);
        startActivity(i);
        finish();
    }
}
