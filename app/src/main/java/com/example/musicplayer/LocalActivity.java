package com.example.musicplayer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer.adapters.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LocalActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        setTitle("本地音乐");
    }
}
