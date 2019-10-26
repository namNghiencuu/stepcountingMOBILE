package com.example.stepcounting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {


    Database mydb;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabs = findViewById(R.id.tabLayout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.AddFragment(new FragmentMain(), "Main");
        sectionsPagerAdapter.AddFragment(new FragmentCalendar(),"Calendar");
        sectionsPagerAdapter.AddFragment(new FragmentStatistic(),"Statistic");
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);





    }



}
