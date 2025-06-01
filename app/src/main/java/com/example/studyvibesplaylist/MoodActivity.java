package com.example.studyvibesplaylist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mood = getIntent().getStringExtra("MOOD_SELECTED");

        if (mood != null) {
            switch (mood) {
                case "Happy":
                    setContentView(R.layout.activity_happy);
                    break;
                case "Calm":
                    setContentView(R.layout.activity_calm);
                    break;
                case "Focus":
                    setContentView(R.layout.activity_focus);
                    break;
                case "Energetic":
                    setContentView(R.layout.activity_energetic);
                    break;
                default:
                    setContentView(R.layout.activity_mood); // fallback layout
                    break;
            }
        } else {
            setContentView(R.layout.activity_mood);

        }
    }
}
