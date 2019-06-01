package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dante.diploma.Topic.TopicAdapter;
import com.example.dante.diploma.Course.Course;
import com.example.dante.diploma.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TopicsActivity extends AppCompatActivity {

    private RecyclerView rvTopics;
    private TopicAdapter topicAdapter;

    private FirebaseDatabase database;
    private DatabaseReference courseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        final Intent intent = getIntent();

        InitRecyclerView();

        Course course = (Course)intent.getSerializableExtra("Course");
        topicAdapter.setTopics(course.getTopics());

    }

    private void InitRecyclerView(){
        rvTopics = findViewById(R.id.rv_topics);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTopics.setLayoutManager(linearLayoutManager);
        topicAdapter = new TopicAdapter(this);
        rvTopics.setAdapter(topicAdapter);

    }
}
