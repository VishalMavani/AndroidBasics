package com.easternts.thenewboston;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bansi on 1/29/2017.
 */

public class SharedPreferencesDemo extends AppCompatActivity implements View.OnClickListener
{
    EditText etSavePrefs;
    TextView tvDisplayData;
    Button bSavePrefs, bLoadPrefs;
    private SharedPreferences bostonPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences_demo);
        initializeComponents();
    }

    private void initializeComponents() {
        bostonPrefs = getSharedPreferences("boston_prefs", MODE_PRIVATE);
        etSavePrefs = (EditText) findViewById(R.id.etSavePrefs);
        tvDisplayData = (TextView) findViewById(R.id.tvDisplayData);
        bSavePrefs = (Button) findViewById(R.id.bSaveExternal);
        bLoadPrefs = (Button) findViewById(R.id.bLoadPrefs);
        bSavePrefs.setOnClickListener(this);
        bLoadPrefs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSaveExternal:
                SharedPreferences.Editor editor = bostonPrefs.edit();
                editor.putString("name", etSavePrefs.getText().toString());
                editor.commit();
                break;
            case R.id.bLoadPrefs:
                String name = bostonPrefs.getString("name", "Name not found.");
                tvDisplayData.setText(name);
                break;
        }
    }
}
