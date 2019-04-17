package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Debug;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dante.diploma.Adapters.TopicAdapter;
import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.example.dante.diploma.Topic;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TopicsActivity extends AppCompatActivity {

    private final String TAG = "Topics Activity";

    private RecyclerView rvTopics;
    private TopicAdapter topicAdapter;

    private ArrayList<Topic> topics;
    private ArrayList<Course> courseArrayList;

    private FirebaseDatabase database;
    private DatabaseReference courseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        final Intent intent = getIntent();
        final int coursePos = intent.getIntExtra("CoursePos", 0);
        courseArrayList = new ArrayList<>();

        InitRecyclerView();

        database = FirebaseDatabase.getInstance();
        courseRef = database.getReference();
        Query myQuery = courseRef;
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Course course =  dataSnapshot.child(Integer.toString(coursePos)).getValue(Course.class);
                if(course != null) {
                    courseArrayList.add(course);
                    topicAdapter.setTopics(course.getTopics());
                }





            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d(TAG, Integer.toString(coursePos));
        if(courseArrayList.size() > coursePos){
            topicAdapter.setTopics(courseArrayList.get(coursePos).getTopics());
        }
    }

    private void InitRecyclerView(){
        Log.d(TAG, "InitRecyclerView: ");

        rvTopics = findViewById(R.id.rv_topics);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTopics.setLayoutManager(linearLayoutManager);
        //rvCourses.setHasFixedSize(true);
        topicAdapter = new TopicAdapter();
        rvTopics.setAdapter(topicAdapter);

    }
}
