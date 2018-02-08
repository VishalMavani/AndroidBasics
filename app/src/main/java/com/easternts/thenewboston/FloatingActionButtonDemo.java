package com.easternts.thenewboston;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class FloatingActionButtonDemo extends AppCompatActivity implements View.OnClickListener {
    FloatingActionMenu social_floating_menu;
    FloatingActionButton floating_facebook, floating_twitter, floating_linkdin,
            floating_google_plus, floating_instagram, floating_youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_action_button_demo);
        initializeComponents();
    }

    private void initializeComponents() {
        social_floating_menu = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floating_facebook = (FloatingActionButton) findViewById(R.id.floating_facebook);
        floating_twitter = (FloatingActionButton) findViewById(R.id.floating_twitter);
        floating_linkdin = (FloatingActionButton) findViewById(R.id.floating_linkdin);
        floating_google_plus = (FloatingActionButton) findViewById(R.id.floating_google_plus);
        floating_instagram = (FloatingActionButton) findViewById(R.id.floating_instagram);
        floating_youtube = (FloatingActionButton) findViewById(R.id.floating_youtube);
        floating_facebook.setOnClickListener(this);
        floating_twitter.setOnClickListener(this);
        floating_linkdin.setOnClickListener(this);
        floating_google_plus.setOnClickListener(this);
        floating_instagram.setOnClickListener(this);
        floating_youtube.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_facebook:
                Intent facebookIntent = getOpenFacebookIntent(FloatingActionButtonDemo.this);
                startActivity(facebookIntent);
                break;
            case R.id.floating_twitter:
                Intent twitterIntent = getOpenTwitterIntent(FloatingActionButtonDemo.this);
                startActivity(twitterIntent);
                break;
            case R.id.floating_linkdin:
                Intent linkdinIntent = getOpenLinkdinIntent(FloatingActionButtonDemo.this);
                startActivity(linkdinIntent);
                break;
            case R.id.floating_google_plus:
                Intent googleplusIntent = getOpenGPlusIntent(FloatingActionButtonDemo.this);
                startActivity(googleplusIntent);
                break;
            case R.id.floating_instagram:
                Intent instagramIntent = getOpenInstagramIntent(FloatingActionButtonDemo.this);
                startActivity(instagramIntent);
                break;
            case R.id.floating_youtube:
                Intent youtubeIntent = getOpenYouTubeIntent(FloatingActionButtonDemo.this);
                startActivity(youtubeIntent);
                break;
        }
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/376227335860239")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/karthikofficialpage")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenTwitterIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/drkarthiik")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/drkarthiik")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenLinkdinIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.linkedin.android", 0); //Checks if Linkdin is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/karthikm128")); //Trys to make intent with Linkdin's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/karthikm128")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenGPlusIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.apps.plus", 0); //Checks if G+ is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/u/0/+KarthikM128")); //Trys to make intent with G+'s URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/u/0/+KarthikM128")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenYouTubeIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube", 0); //Checks if YT is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/karthikm128")); //Trys to make intent with YT's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/karthikm128")); //catches and opens a url to the desired page
        }
    }
}
