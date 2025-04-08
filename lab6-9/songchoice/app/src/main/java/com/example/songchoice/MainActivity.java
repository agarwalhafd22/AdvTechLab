package com.example.songchoice;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView songListView;
    Button playPauseBtn;
    MediaPlayer mediaPlayer;
    boolean isPlaying = true;

    int[] songResIds = {R.raw.song1}; // Add your song resource ids here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songListView = findViewById(R.id.songListView);
        playPauseBtn = findViewById(R.id.playPauseBtn);

        String[] songNames = getResources().getStringArray(R.array.song_names);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, songNames);
        songListView.setAdapter(adapter);

        songListView.setOnItemClickListener((adapterView, view, position, id) -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, songResIds[position]);
            mediaPlayer.start();
            playPauseBtn.setText("Pause");
            playPauseBtn.setEnabled(true);
            isPlaying = true;
        });

        playPauseBtn.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    playPauseBtn.setText("Play");
                } else {
                    mediaPlayer.start();
                    playPauseBtn.setText("Pause");
                }
                isPlaying = !isPlaying;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
