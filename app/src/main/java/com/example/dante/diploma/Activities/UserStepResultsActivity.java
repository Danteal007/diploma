package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dante.diploma.R;
import com.example.dante.diploma.Topic.TopicResultPageFragment;
import com.example.dante.diploma.UserInfo.CourseUserInfo;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserStepResultsActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    DiplomaUserInfo diplomaUserInfo;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    CourseUserInfo courseUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_step_results);

        final Intent intent = getIntent();

        firebaseDatabase.getReference().addValueEventListener(
                new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                diplomaUserInfo = dataSnapshot.child("Users").
                        child(mAuth.getCurrentUser().getUid()).
                        getValue(DiplomaUserInfo.class);
                courseUserInfo = diplomaUserInfo.getCourseUserInfos().
                        get(intent.getIntExtra("CoursePos",0));
                viewPager = findViewById(R.id.vp_topic_rezults);
                pagerAdapter =
                        new TopicPagerAdapter(getSupportFragmentManager());
                viewPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private class TopicPagerAdapter extends FragmentPagerAdapter {
        public TopicPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return TopicResultPageFragment.
                    newInstance(position,
                            courseUserInfo.getTopicUserInfos().
                                    get(position));
        }

        @Override
        public int getCount() {
            return courseUserInfo.getTopicUserInfos().size();
        }
    }
}
