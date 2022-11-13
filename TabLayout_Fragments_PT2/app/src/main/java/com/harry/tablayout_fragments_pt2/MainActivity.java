package com.harry.tablayout_fragments_pt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem tabChat = findViewById(R.id.tabChats);
        TabItem tabStatus = findViewById(R.id.tabStatus);
        TabItem tabCals = findViewById(R.id.tabCalls);

        ViewPager viewPager = findViewById(R.id.viewpager);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        //remove the error - join tablayout with view pager
        tabLayout.setupWithViewPager(viewPager);
    }
}