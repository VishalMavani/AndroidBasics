package com.easternts.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Bansi..!! on 09-07-2016.
 */
public class HttpExample extends Activity {
    TextView tvLoadData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.htttp_example);
        initialize();
        HttpDataHandler httpData = new HttpDataHandler();
        try {
//            String data = httpData.getInternetData();
//            tvLoadData.setText(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        tvLoadData = (TextView) findViewById(R.id.tvLoadData);
    }
}
