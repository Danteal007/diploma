package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.dante.diploma.R;
import com.example.dante.diploma.StepPageFragment;
import com.example.dante.diploma.Topic;

public class StepsActivity extends AppCompatActivity {

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem[] tabItems;
    Tab[] tabs;
    PagerAdapter pagerAdapter;
    Topic topic;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        final Intent intent = getIntent();
        topic = (Topic)intent.getSerializableExtra("Topic");





        viewPager = findViewById(R.id.vp_steps);
        pagerAdapter = new StepPagerAdapter(getSupportFragmentManager());



        viewPager.setAdapter(pagerAdapter);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class StepPagerAdapter extends FragmentPagerAdapter{



        public StepPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return StepPageFragment.newInstance(position, topic.getSteps().get(position));
        }

        @Override
        public int getCount() {
            return topic.getSteps().size();
        }
    }
}
