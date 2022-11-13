package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1- initillisation of list view

        ListView listView = findViewById(R.id.listView);

        // 2- data source
        String[] androidVersions = {
                "Lollipop",
                "Oreo",
                "KitKat",
                "Marshmallow",
                "Android 10",
                "Android 11",
                "Android 12"
        };

        // 3 - Adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                R.layout.myitem,
                R.id.itemText,
                androidVersions
        );

        listView.setAdapter(adapter);


    }
}