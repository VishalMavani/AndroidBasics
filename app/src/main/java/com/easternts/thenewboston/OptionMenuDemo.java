package com.easternts.thenewboston;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bansi on 11-16-2016.
 */

public class OptionMenuDemo extends AppCompatActivity {
    TextView tvOptionMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_menu_demo);
        tvOptionMenu = (TextView) findViewById(R.id.tvOptionMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miRefresh:
                System.out.println("option clicked");
                tvOptionMenu.setText("Refresh option clicked...");
                Toast.makeText(OptionMenuDemo.this, "Refresh option clicked...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miSettings:
                System.out.println("settings clicked");
                tvOptionMenu.setText("Settings option clicked...");
                Toast.makeText(OptionMenuDemo.this, "Settings option clicked...", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}