package com.mastercoding.GridView_App;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.GridView;

import com.mastercoding.helloworld2.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        gridView = findViewById(R.id.gridView);

        // Data to be displayed into gridview
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
        courseModelArrayList.add(new CourseModel("The Complete Android 12 Course", R.drawable.course1));
        courseModelArrayList.add(new CourseModel("The Complete Java Developer Course", R.drawable.course2));
        courseModelArrayList.add(new CourseModel("The Complete Kotlin Course", R.drawable.course3));
        courseModelArrayList.add(new CourseModel("The Complete Data Structure and Algorithms Course", R.drawable.course4));


        // Attach the Adapter
        MyAdapter myAdapter = new MyAdapter(this,courseModelArrayList );
        gridView.setAdapter(myAdapter);


    }
}