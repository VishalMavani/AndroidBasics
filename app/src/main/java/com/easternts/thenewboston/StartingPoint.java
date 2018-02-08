package com.easternts.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartingPoint extends Activity {
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent logIn = new Intent(StartingPoint.this, LogIn.class);
                    startActivity(logIn);
                }
            }
        };
        thread.start();
    }
}
