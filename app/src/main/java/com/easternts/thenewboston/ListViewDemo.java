package com.easternts.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Bansi on 11-16-2016.
 */

public class ListViewDemo extends AppCompatActivity {
    ListView lvCountries;
    private String[] countries = {
            "U.S.A.",
            "U.K.",
            "India",
            "China",
            "Africa",
            "France",
            "Japan",
            "Australia",
            "Brazil",
            "Pakistan",
            "Sri Lanka",
            "U.A.E.",
            "Singapore",
            "Switzerland"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo);
        lvCountries = (ListView) findViewById(R.id.lvCountries);
        ArrayAdapter adapter = new ArrayAdapter(ListViewDemo.this, android.R.layout.simple_list_item_1, countries);
        lvCountries.setAdapter(adapter);
        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemo.this, "position: " + position + " - Value: " + countries[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
