package com.example.studyvibesplaylist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int POST_BOUNCE_DELAY = 1000; 

    private TextView logoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoText = findViewById(R.id.logoText);

        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // When bounce animation ends
        bounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // no action needed
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Wait for 4 seconds after bounce finishes, then start main activity
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    // Add this line right after startActivity()
                    overridePendingTransition(R.anim.custom_fade_in, R.anim.custom_fade_out);
                    finish();
                }, POST_BOUNCE_DELAY);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // no action needed
            }
        });

        logoText.startAnimation(bounce);
    }
}
