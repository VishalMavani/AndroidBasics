package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 06-07-2016.
 */
public class SQLiteViewTableData extends Activity implements View.OnClickListener {
    TextView tvDisplayTableData;
    Button bReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_view_table_data);
        initialize();
        BostonDatabase bostonDatabase = new BostonDatabase(SQLiteViewTableData.this);
        bostonDatabase.openDatabase();
        String result = bostonDatabase.selectPersonalInfo();
        bostonDatabase.closeDatabase();
        tvDisplayTableData.setText(result);
    }

    private void initialize() {
        tvDisplayTableData = (TextView) findViewById(R.id.tvDisplayTableData);
        bReturn = (Button) findViewById(R.id.bReturn);
        bReturn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(SQLiteViewTableData.this, SQLiteExample.class);
        startActivity(i);
        finish();

    }
}
