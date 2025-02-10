package com.example.imagegallery;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter adapter = new ImageAdapter(this, images);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Toast.makeText(this, "Image " + (position + 1) + " clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
