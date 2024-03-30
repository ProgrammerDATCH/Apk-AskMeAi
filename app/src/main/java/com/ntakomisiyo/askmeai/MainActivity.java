package com.ntakomisiyo.askmeai;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView idIVLogo;
    private Handler checkConn;
    private Runnable checkRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idIVLogo = findViewById(R.id.idIVLogo);
        Animation zoomInOut = new ScaleAnimation(0.3f, 1.0f, 0.3f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        zoomInOut.setDuration(1000);
        zoomInOut.setRepeatCount(Animation.INFINITE);
        zoomInOut.setRepeatMode(Animation.REVERSE);
        idIVLogo.startAnimation(zoomInOut);

        checkConn = new Handler();
        checkRun = new Runnable() {
            @Override
            public void run() {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo == null || !networkInfo.isConnected())
                {
                    Toast.makeText(MainActivity.this, "No internet! To chat with DATCH Robot", Toast.LENGTH_LONG).show();
                    checkConn.postDelayed(checkRun, 10000);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Connected to DATCH Ai", Toast.LENGTH_SHORT).show();
                    //good
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 4000);
                }
            }
        };

        checkConn.post(checkRun);

    }
}