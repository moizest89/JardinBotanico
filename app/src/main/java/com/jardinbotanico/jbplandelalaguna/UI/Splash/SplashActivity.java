package com.jardinbotanico.jbplandelalaguna.UI.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.Main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private long splashDelay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = null;
                intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, splashDelay);

    }

}
