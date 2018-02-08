package com.easternts.thenewboston;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bansi on 11-17-2016.
 */

public class DisplayJson extends AppCompatActivity implements View.OnClickListener {
    Button bHit;
    TextView tvDisplayJson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_json);
        initializeComponents();
        bHit.setOnClickListener(this);
    }

    private void initializeComponents() {
        bHit = (Button) findViewById(R.id.bHit);
        tvDisplayJson = (TextView) findViewById(R.id.tvDisplayJson);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bHit:
                URL url[] = new URL[5];
                try {
                    url[0] = new URL("http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt");
//                    url[0] = new URL("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                new JSONTask().execute(url);
                break;
        }
    }

    public class JSONTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                connection = (HttpURLConnection) params[0].openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ( (line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJSON = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray("movies");

                StringBuffer finalBufferedData = new StringBuffer();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject jsonObject = parentArray.getJSONObject(i);
                    String movie = jsonObject.getString("movie");
                    int year = jsonObject.getInt("year");
                    finalBufferedData.append(movie + " : " + year + "\n");
                }

                return finalBufferedData.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvDisplayJson.setText(result);
        }

    }
}
