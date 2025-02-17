package com.example.q1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.TimeUnit;

public class MainActivity extends ComponentActivity {
    private Button b1, b3, b4;
    private FloatingActionButton b2;
    private ImageView iv;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1, tx2, tx3;
    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        iv = findViewById(R.id.imageView);

        tx1 = findViewById(R.id.textView2);
        tx2 = findViewById(R.id.textView3);
        tx3 = findViewById(R.id.textView4);

        tx3.setText("Westworld Official Sountrack");

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        seekbar = findViewById(R.id.seekBar);
        seekbar.setMax(mediaPlayer.getDuration());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        b2.setEnabled(false);

        b3.setOnClickListener(v -> {
            mediaPlayer.start();
            b2.setImageResource(android.R.drawable.ic_media_pause);
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();

            if (oneTimeOnly == 0) {
                seekbar.setMax((int) finalTime);
                oneTimeOnly = 1;
            }

            updateTimers();
            myHandler.postDelayed(updateSongTime, 100);

            b2.setEnabled(true);
            b3.setEnabled(false);
        });

        b2.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                b2.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mediaPlayer.start();
                b2.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

        b1.setOnClickListener(v -> {
            int temp = (int) startTime;
            if ((temp + forwardTime) <= finalTime) {
                startTime += forwardTime;
                mediaPlayer.seekTo((int) startTime);
            } else {
            }
        });

        b4.setOnClickListener(v -> {
            int temp = (int) startTime;
            if ((temp - backwardTime) > 0) {
                startTime -= backwardTime;
                mediaPlayer.seekTo((int) startTime);
            } else {
                mediaPlayer.seekTo(0);

            }
        });
    }

    private void updateTimers() {
        tx2.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))
        ));

        tx1.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))
        ));
    }

    private final Runnable updateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            updateTimers();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        myHandler.removeCallbacks(updateSongTime);
    }
}
