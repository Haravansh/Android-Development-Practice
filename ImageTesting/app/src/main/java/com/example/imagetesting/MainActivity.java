package com.example.imagetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.falcon);
        image.setBackgroundColor(Color.BLACK);
    }
}