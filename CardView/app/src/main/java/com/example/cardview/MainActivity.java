package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CourseModel> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.recyclerView);

        courseModelArrayList = new ArrayList<>();
        courseModelArrayList.add(new CourseModel("Android development testing",R.drawable.course1));
        courseModelArrayList.add(new CourseModel("Android development testing 1",R.drawable.course2));
        courseModelArrayList.add(new CourseModel("Android development testing 2",R.drawable.course3));
        courseModelArrayList.add(new CourseModel("Android development testing 3",R.drawable.course4));


        MyAdapter myAdapter = new MyAdapter(this,courseModelArrayList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);



    }
}