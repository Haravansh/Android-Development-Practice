package com.example.customlistviewadpter;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1- ListView Initialization
        ListView listView = findViewById(R.id.listview);

        // 2- Data Source - Arrays [Title, SubTitle, Images]
        String[] mainTitle = {
                "Master Coding App",
                "Master Coding Pro App",
                "Master Flutter",
        };

        String[] subTitle = {
                "900,000 Installs",
                "10,000 Installs",
                "15,000 Installs",

        };

        int[] imagesArray = {
                R.drawable.app1,
                R.drawable.app2,
                R.drawable.app3
        };



        // 3- Adapter - Custom Adapter
        MyListAdapter adapter = new MyListAdapter(this, mainTitle, subTitle, imagesArray);
        listView.setAdapter(adapter);


        // 4- Handling Click Events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Toast.makeText(getApplicationContext(), "Clicked First Item", Toast.LENGTH_SHORT).show();
                }else if(position ==1){
                    Toast.makeText(getApplicationContext(), "Clicked Second Item", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==0){
                    Toast.makeText(getApplicationContext(), "HEYYY!! LONG CLICK", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

    }

}