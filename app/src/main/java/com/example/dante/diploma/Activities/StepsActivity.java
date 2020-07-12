package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dante.diploma.R;
import com.example.dante.diploma.Step.Step;
import com.example.dante.diploma.Step.StepPageFragment;
import com.example.dante.diploma.Topic.Topic;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    LinearLayout llStepsButtons;
    ArrayList<Button> stepButtons;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    ImageView imageView;

    /**Содержимое текущей темы*/
    Topic topic;
    int currentStepNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        final Intent intent = getIntent();
        topic = (Topic)intent.getSerializableExtra("Topic");

        stepButtons = new ArrayList<>();

        llStepsButtons = findViewById(R.id.ll_steps_buttons);
        viewPager = findViewById(R.id.vp_steps);


        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(64, 64);
        layoutParams.setMargins(10,10,10,10);

        final LinearLayout.LayoutParams lpSelection = new LinearLayout.LayoutParams(74,74);
        lpSelection.setMargins(10,10,10,10);


        pagerAdapter = new StepPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(Button button : stepButtons){
                    button.setLayoutParams(layoutParams);
                }
                currentStepNumber = position;
                stepButtons.get(currentStepNumber).setLayoutParams(lpSelection);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (final Step step : topic.getSteps()){
            final Button stepButton = new Button(llStepsButtons.getContext());
            stepButtons.add(stepButton);
            switch (step.getStepType()){
                case Code:
                    stepButton.setBackgroundResource(R.drawable.step_code);
                    break;
                case Quiz:
                    stepButton.setBackgroundResource(R.drawable.step_quiz);
                    break;
                case Article:
                    stepButton.setBackgroundResource(R.drawable.step_article);
                    break;
            }
            stepButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(step.getStepPos(), true);
                }
            });
            stepButton.setLayoutParams(layoutParams);
            llStepsButtons.addView(stepButton);
        }

        stepButtons.get(currentStepNumber).setLayoutParams(lpSelection);
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
