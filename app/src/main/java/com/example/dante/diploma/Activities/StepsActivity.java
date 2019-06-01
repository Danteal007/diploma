package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dante.diploma.R;
import com.example.dante.diploma.Step.StepPageFragment;
import com.example.dante.diploma.Topic.Topic;

public class StepsActivity extends AppCompatActivity {

    ViewPager viewPager;
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
    }

    private class StepPagerAdapter extends FragmentPagerAdapter{
        public StepPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return StepPageFragment.newInstance(position,
                    topic.getSteps().get(position));
        }

        @Override
        public int getCount() {
            return topic.getSteps().size();
        }
    }
}
