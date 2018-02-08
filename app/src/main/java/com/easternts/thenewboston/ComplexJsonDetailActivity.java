package com.easternts.thenewboston;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.easternts.thenewboston.models.Movie;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Bansi on 1/28/2017.
 */

public class ComplexJsonDetailActivity extends AppCompatActivity{
    private ImageView ivMovieIcon;
    private TextView tvMovie;
    private TextView tvTagline;
    private TextView tvYear;
    private TextView tvDuration;
    private TextView tvDirector;
    private RatingBar rbMovieRating;
    private TextView tvCast;
    private TextView tvStory;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_json_detail);

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initializeComponents();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String movieJSON = bundle.getString("movieJSON");
            Movie movie = new Gson().fromJson(movieJSON, Movie.class);


            ImageLoader.getInstance().displayImage(movie.getImage(), ivMovieIcon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });

            tvMovie.setText(movie.getMovie());
            tvTagline.setText(movie.getTagline());
            tvYear.setText("Year: " + movie.getYear());
            tvDuration.setText("Duration:" + movie.getDuration());
            tvDirector.setText("Director:" + movie.getDirector());

            // rating bar
            rbMovieRating.setRating(movie.getRating() / 2);

            StringBuffer castBuffer = new StringBuffer();
            for(Movie.Cast cast : movie.getCastList()){
                castBuffer.append(cast.getName() + ", ");
            }

            tvCast.setText("Cast:" + castBuffer);
            tvStory.setText(movie.getStory());
        }
    }

    private void initializeComponents() {
        ivMovieIcon = (ImageView)findViewById(R.id.ivIcon);
        tvMovie = (TextView)findViewById(R.id.tvMovie);
        tvTagline = (TextView)findViewById(R.id.tvTagline);
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvDuration = (TextView)findViewById(R.id.tvDuration);
        tvDirector = (TextView)findViewById(R.id.tvDirector);
        rbMovieRating = (RatingBar)findViewById(R.id.rbMovie);
        tvCast = (TextView)findViewById(R.id.tvCast);
        tvStory = (TextView)findViewById(R.id.tvStory);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
