package com.example.greeting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

         TextView textView = findViewById(R.id.TextView8);

        //Receiving the data from the first activity
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        String msg = extras.getString("myName");
        textView.setText("Hello " + msg + ", We hope you enjoy the app!");
    }
}