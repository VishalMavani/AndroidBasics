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
 * Created by Bansi..!! on 06-07-2016.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {
    EditText firstName, lastName, rowID;
    Button insertData, updateData, deleteData, viewRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_example);
        initialize();
    }

    private void initialize() {
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        rowID = (EditText) findViewById(R.id.etRowID);
        insertData = (Button) findViewById(R.id.bInsertData);
        updateData = (Button) findViewById(R.id.bUpdateData);
        deleteData = (Button) findViewById(R.id.bDeleteData);
        viewRecords = (Button) findViewById(R.id.bViewRecords);
        insertData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        viewRecords.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bInsertData:
                insertData();
                break;
            case R.id.bUpdateData:
                updateData();
                break;
            case R.id.bDeleteData:
                deleteData();
                break;
            case R.id.bViewRecords:
                viewRecords();
                break;
        }
    }

    private void insertData() {
        boolean flag = true;
        try {
            String stringFirstName = firstName.getText().toString();
            String stringLastName = lastName.getText().toString();
            BostonDatabase bostonDatabase = new BostonDatabase(SQLiteExample.this);
            bostonDatabase.openDatabase();
            bostonDatabase.insertPersonalInfo(stringFirstName, stringLastName);
            bostonDatabase.closeDatabase();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            Dialog dialog = new Dialog(this);
            dialog.setTitle("Personal Information");
            TextView textView = new TextView(this);
            textView.setText(e.toString());
            dialog.setContentView(textView);
            dialog.show();
        } finally {
            if (flag) {
                Dialog dialog = new Dialog(this);
                dialog.setTitle("Personal Information");
                TextView textView = new TextView(this);
                textView.setText("Data Successfully Inserted!!!");
                dialog.setContentView(textView);
                dialog.show();
            }
        }
    }

    private void updateData() {
            Intent i = new Intent(SQLiteExample.this, SQLiteUpdate.class);
            Bundle bundle = new Bundle();
            bundle.putString("rowID", rowID.getText().toString());
            i.putExtras(bundle);
            startActivity(i);
    }

    private void deleteData() {
        try {
            BostonDatabase bostonDatabase = new BostonDatabase(SQLiteExample.this);
            bostonDatabase.openDatabase();
            bostonDatabase.deletePersonalInfo(rowID.getText().toString());
            bostonDatabase.closeDatabase();
            Intent i2 = new Intent(SQLiteExample.this, SQLiteViewTableData.class);
            startActivity(i2);
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

    private void viewRecords() {
        Intent i = new Intent(SQLiteExample.this, SQLiteViewTableData.class);
        startActivity(i);
        finish();
    }

}
