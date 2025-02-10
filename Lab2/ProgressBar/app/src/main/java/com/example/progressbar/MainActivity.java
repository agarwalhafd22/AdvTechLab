package com.example.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar determinateProgressBar;
    private ProgressBar indeterminateProgressBar;
    private Button startProgressButton;
    private Handler handler = new Handler();
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        determinateProgressBar = findViewById(R.id.determinateProgressBar);
        indeterminateProgressBar = findViewById(R.id.indeterminateProgressBar);
        startProgressButton = findViewById(R.id.startProgressButton);

        startProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the indeterminate progress bar
                indeterminateProgressBar.setVisibility(View.VISIBLE);

                // Start updating the determinate progress bar
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        progress = 0; // Reset progress
                        while (progress <= 100) {
                            try {
                                Thread.sleep(50); // Simulate work
                                progress++;

                                // Update the determinate progress bar on the UI thread
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        determinateProgressBar.setProgress(progress);

                                        // Hide the indeterminate progress bar when complete
                                        if (progress == 100) {
                                            indeterminateProgressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
