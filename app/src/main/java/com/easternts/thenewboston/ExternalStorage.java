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

public class ExternalStorage extends AppCompatActivity implements View.OnClickListener {
    private static final String FILE_NAME = "external_file.txt";
    EditText etSaveExternal;
    TextView tvDisplayData;
    Button bSaveExternal, bLoadExternal, bDeleteExternal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage);
        initializeComponents();
    }

    private void initializeComponents() {
        etSaveExternal = (EditText) findViewById(R.id.etSaveExternal);
        tvDisplayData = (TextView) findViewById(R.id.tvDisplayData);
        bSaveExternal = (Button) findViewById(R.id.bSaveExternal);
        bLoadExternal = (Button) findViewById(R.id.bLoadExternal);
        bDeleteExternal = (Button) findViewById(R.id.bDeleteExternal);
        bSaveExternal.setOnClickListener(this);
        bLoadExternal.setOnClickListener(this);
        bDeleteExternal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSaveExternal:
                String dataToSave = etSaveExternal.getText().toString();
                try {
                    FileOperationsHelper.getInstance().saveExternalFile(FILE_NAME, dataToSave);
                    etSaveExternal.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved Successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bLoadExternal:
                try {
                    String data = FileOperationsHelper.getInstance().readExternalFile(FILE_NAME);
                    tvDisplayData.setText(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bDeleteExternal:
                    Boolean isFileDeleted = FileOperationsHelper.getInstance().deleteExternalFile(FILE_NAME);
                    if (isFileDeleted) {
                        Toast.makeText(getApplicationContext(), "File Deleted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                        Toast.makeText(getApplicationContext(), "File Delete Failed.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
