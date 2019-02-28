package com.magad.alertprogress;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pb = findViewById(R.id.pb_slash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.VISIBLE);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }

        },1000);
    }

    @Override
    protected void onStop() {
        super.onStop();

        pb.setVisibility(View.INVISIBLE);
    }
}
