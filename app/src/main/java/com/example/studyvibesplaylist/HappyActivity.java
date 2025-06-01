package com.example.studyvibesplaylist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;



public class HappyActivity extends AppCompatActivity {

    TextView[] songTitles = new TextView[8];
    ImageButton[] playButtons = new ImageButton[8];
    int[] songResIds = {
            R.raw.happy_song1, R.raw.happy_song2, R.raw.happy_song3, R.raw.happy_song4,
            R.raw.happy_song5, R.raw.happy_song6, R.raw.happy_song7, R.raw.happy_song8
    };

    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Handler handler = new Handler();
    Runnable updateSeekBar;
    int currentSongIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);


        seekBar = findViewById(R.id.seekBar);
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());

        for (int i = 0; i < 8; i++) {
            int titleId = getResources().getIdentifier("songTitle" + (i + 1), "id", getPackageName());
            int playBtnId = getResources().getIdentifier("playButton" + (i + 1), "id", getPackageName());

            songTitles[i] = findViewById(titleId);
            playButtons[i] = findViewById(playBtnId);

            int finalI = i;

            playButtons[i].setOnClickListener(v -> handleSongClick(finalI));
            songTitles[i].setOnClickListener(v -> handleSongClick(finalI));
        }

        setupSeekBar();
    }

    private void handleSongClick(int index) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            handler.removeCallbacks(updateSeekBar);
        }

        if (currentSongIndex != -1) {
            playButtons[currentSongIndex].setImageResource(android.R.drawable.ic_media_play);
            songTitles[currentSongIndex].setBackgroundColor(Color.TRANSPARENT);
        }

        mediaPlayer = MediaPlayer.create(this, songResIds[index]);
        mediaPlayer.start();
        currentSongIndex = index;

        playButtons[index].setImageResource(android.R.drawable.ic_media_pause);
        songTitles[index].setBackgroundColor(Color.parseColor("#333333"));

        seekBar.setMax(mediaPlayer.getDuration());

        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(updateSeekBar, 0);

        mediaPlayer.setOnCompletionListener(mp -> {
            playButtons[index].setImageResource(android.R.drawable.ic_media_play);
            songTitles[index].setBackgroundColor(Color.TRANSPARENT);
            seekBar.setProgress(0);
            currentSongIndex = -1;
        });
    }

    private void setupSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            handler.removeCallbacks(updateSeekBar);
        }
        super.onDestroy();
    }
}
