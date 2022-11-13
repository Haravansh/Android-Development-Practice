package com.harry.viewpager_fragement_pt1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager2);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);

        viewPager.setAdapter(myPagerAdapter);
    }
}