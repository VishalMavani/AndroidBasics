package com.easternts.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Bansi..!! on 04-07-2016.
 */
public class ListActivityDemo extends ListActivity {
    String activityList[] = {"RelativeLayout", "Email", "ScrollView", "Camera",
            "StartActivity", "StartActivityForResult", "DynamicViewElement", "SQLiteExample", "HttpExample",
            "OptionMenuDemo", "ListViewDemo", "DisplayJson", "ComplexJsonDemo", "FunDapterDemo", "RecyclerViewDemo", "SharedPreferencesDemo", "InternalStorage", "ExternalStorage", "FloatingActionButtonDemo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(ListActivityDemo.this, android.R.layout.simple_list_item_1, activityList));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            Class selectedActivity = Class.forName("com.easternts.thenewboston." + activityList[position]);
            Intent i = new Intent(ListActivityDemo.this, selectedActivity);
            startActivity(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
