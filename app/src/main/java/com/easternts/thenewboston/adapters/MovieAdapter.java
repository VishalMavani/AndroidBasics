package com.easternts.thenewboston.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.easternts.thenewboston.R;
import com.easternts.thenewboston.models.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;



/**
 * Created by Bansi on 1/28/2017.
 */

public class MovieAdapter extends ArrayAdapter {

    private List<Movie> movieList;
    private int resource;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        movieList = objects;
        this.resource = resource;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(resource, null);
            holder.ivMovieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            holder.tvMovie = (TextView) convertView.findViewById(R.id.tvMovie);
            holder.tvTagLine = (TextView) convertView.findViewById(R.id.tvTagLine);
            holder.tvYear = (TextView) convertView.findViewById(R.id.tvYear);
            holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
            holder.tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
            holder.rbRating = (RatingBar) convertView.findViewById(R.id.rbRating);
            holder.tvCast = (TextView) convertView.findViewById(R.id.tvCast);
            holder.tvStory = (TextView) convertView.findViewById(R.id.tvStory);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ProgressBar pbMovieImage = (ProgressBar) convertView.findViewById(R.id.pbMovieImage);
        ImageLoader.getInstance().displayImage(movieList.get(position).getImage(), holder.ivMovieImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                pbMovieImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                pbMovieImage.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                pbMovieImage.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                pbMovieImage.setVisibility(View.GONE);
            }
        });

        holder.tvMovie.setText(movieList.get(position).getMovie());
        holder.tvTagLine.setText(movieList.get(position).getTagline());
        holder.tvYear.setText("Year: " + movieList.get(position).getYear());
        holder.tvDuration.setText("Duration: " + movieList.get(position).getDuration());
        holder.tvDirector.setText("Director: " + movieList.get(position).getDirector());
        holder.rbRating.setRating(movieList.get(position).getRating() / 2);

        StringBuffer castBuffer = new StringBuffer();
        for (Movie.Cast cast : movieList.get(position).getCastList()) {
            castBuffer.append(cast.getName() + ", ");
        }
        holder.tvCast.setText(castBuffer);

        holder.tvStory.setText(movieList.get(position).getStory());

        return convertView;
    }

    class ViewHolder {
        private ImageView ivMovieImage;
        private TextView tvMovie;
        private TextView tvTagLine;
        private TextView tvYear;
        private TextView tvDuration;
        private TextView tvDirector;
        private RatingBar rbRating;
        private TextView tvCast;
        private TextView tvStory;
    }
}
