package com.example.studyvibesplaylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner moodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moodSpinner = findViewById(R.id.moodSpinner);

        String[] moods = {"Select your mood", "Happy", "Calm", "Focus", "Energetic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, moods);
        moodSpinner.setAdapter(adapter);

        moodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMood = parent.getItemAtPosition(position).toString();

                if (!selectedMood.equals("Select your mood")) {
                    switch (selectedMood) {
                        case "Happy":
                            Intent intent = new Intent(MainActivity.this, MoodActivity.class);
                            intent.putExtra("MOOD_SELECTED", selectedMood);
                            startActivity(intent);
                            break;
                        case "Calm":
                            intent = new Intent(MainActivity.this, MoodActivity.class);
                            intent.putExtra("MOOD_SELECTED", selectedMood);
                            startActivity(intent);
                            break;
                        case "Focus":
                            intent = new Intent(MainActivity.this, MoodActivity.class);
                            intent.putExtra("MOOD_SELECTED", selectedMood);
                            startActivity(intent);

                            break;
                        case "Energetic":
                            intent = new Intent(MainActivity.this, MoodActivity.class);
                            intent.putExtra("MOOD_SELECTED", selectedMood);
                            startActivity(intent);

                            break;
                    }
                    moodSpinner.setSelection(0); // reset to default
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
