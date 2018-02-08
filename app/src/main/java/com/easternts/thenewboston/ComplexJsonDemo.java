package com.easternts.thenewboston;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.easternts.thenewboston.adapters.MovieAdapter;
import com.easternts.thenewboston.models.Movie;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bansi on 11-19-2016.
 */

public class ComplexJsonDemo extends AppCompatActivity {
    ListView lvMovies;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_json);
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        progressDialog = new ProgressDialog(ComplexJsonDemo.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading. Please Wait...");

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complex_json_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionRefresh:
                try {
                    URL url = new URL("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
                    new ComplexJSONTask().execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ComplexJSONTask extends AsyncTask<URL, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List<Movie> doInBackground(URL... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                connection = (HttpURLConnection) params[0].openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJSON = buffer.toString();

                //JSON Parsing
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray movies = parentObject.getJSONArray("movies");
                List<Movie> movieList = new ArrayList<>();

                //Gson
                Gson gson = new Gson();
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject movieJSON = movies.getJSONObject(i);
                    Movie movie = gson.fromJson(movieJSON.toString(), Movie.class);
                    movieList.add(movie);
                }

                //without Gson
//                for (int i = 0; i < movies.length(); i++) {
//                    JSONObject movieJSON = movies.getJSONObject(i);
//                    Movie movie = new Movie();
//                    movie.setMovie(movieJSON.getString("movie"));
//                    movie.setYear(movieJSON.getInt("year"));
//                    movie.setRating((float) movieJSON.getDouble("rating"));
//                    movie.setDuration(movieJSON.getString("duration"));
//                    movie.setDirector(movieJSON.getString("director"));
//                    movie.setTagline(movieJSON.getString("tagline"));
//
//                    List<Movie.Cast> castList = new ArrayList<>();
//                    for (int j = 0; j < movieJSON.getJSONArray("cast").length(); j++) {
//                        Movie.Cast cast = new Movie.Cast();
//                        JSONObject castJSON = movieJSON.getJSONArray("cast").getJSONObject(j);
//                        cast.setName(castJSON.getString("name"));
//                        castList.add(cast);
//                    }
//                    movie.setCastList(castList);
//
//                    movie.setImage(movieJSON.getString("image"));
//                    movie.setStory(movieJSON.getString("story"));
//                    movieList.add(movie);
//                }

                return movieList;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
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
        protected void onPostExecute(final List<Movie> movieList) {
            super.onPostExecute(movieList);
            progressDialog.dismiss();
            if (movieList != null) {
                MovieAdapter movieAdapter = new MovieAdapter(ComplexJsonDemo.this, R.layout.movie_row, movieList);
                lvMovies.setAdapter(movieAdapter);
                lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Movie movie = movieList.get(position);
                        Intent intent = new Intent(ComplexJsonDemo.this, ComplexJsonDetailActivity.class);
                        intent.putExtra("movieJSON", new Gson().toJson(movie));
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from the server, Please Check your Internet Connection...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


