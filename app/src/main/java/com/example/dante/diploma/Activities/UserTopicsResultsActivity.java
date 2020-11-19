package com.example.dante.diploma.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.CourseUserInfo;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.example.dante.diploma.UserInfo.TopicsUserInfoAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserTopicsResultsActivity extends AppCompatActivity {

    DiplomaUserInfo diplomaUserInfo;

    RecyclerView rvTopicsUserInfo;
    TopicsUserInfoAdapter topicResultsAdapter;
    CourseUserInfo courseUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_user_info);

        final Intent intent = getIntent();
        final Context context = this;

        FirebaseUtils.getInstance().getDatabaseRef().addValueEventListener(
                new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InitRecyclerView();
                diplomaUserInfo = dataSnapshot.child("Users").
                        child(FirebaseUtils.getInstance().getCurrentUserID()).
                        getValue(DiplomaUserInfo.class);
                courseUserInfo = diplomaUserInfo.getCourseUserInfos().
                        get(intent.getIntExtra("CoursePos",0));
                topicResultsAdapter =
                        new TopicsUserInfoAdapter(context);
                topicResultsAdapter.setTopicUserInfos(courseUserInfo.getTopicUserInfos());
                rvTopicsUserInfo.setAdapter(topicResultsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void InitRecyclerView(){
        rvTopicsUserInfo = findViewById(R.id.rv_topics_user_info);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTopicsUserInfo.setLayoutManager(linearLayoutManager);
        topicResultsAdapter = new TopicsUserInfoAdapter(this);
        rvTopicsUserInfo.setAdapter(topicResultsAdapter);
    }
}
