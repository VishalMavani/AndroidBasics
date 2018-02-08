package com.easternts.thenewboston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easternts.thenewboston.util.FileOperationsHelper;

import java.io.IOException;

public class InternalStorage extends AppCompatActivity implements View.OnClickListener {

    private static final String FILE_NAME = "internal_file";
    EditText etSaveInternal;
    TextView tvDisplayData;
    Button bSaveInternal, bLoadInternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        initializeComponents();
    }

    private void initializeComponents() {
        etSaveInternal = (EditText) findViewById(R.id.etSaveInternal);
        tvDisplayData = (TextView) findViewById(R.id.tvDisplayData);
        bSaveInternal = (Button) findViewById(R.id.bSaveExternal);
        bLoadInternal = (Button) findViewById(R.id.bLoadInternal);
        bSaveInternal.setOnClickListener(this);
        bLoadInternal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSaveExternal:
                String dataToSave = etSaveInternal.getText().toString();
                try {
                    FileOperationsHelper.getInstance().saveInternalFile(getApplicationContext(), FILE_NAME, dataToSave);
                    etSaveInternal.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved Successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bLoadInternal:
                try {
                    String data = FileOperationsHelper.getInstance().readInternalFile(getApplicationContext(), FILE_NAME);
                    tvDisplayData.setText(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
