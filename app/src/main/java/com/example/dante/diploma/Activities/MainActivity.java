package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dante.diploma.Adapters.CourseAdapter;
import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";

    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference courseRef;

    private ArrayList<Course> courses;

    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitRecyclerView();

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //courseRef = database.getReference("Courses");

        UserInfo userInfo1 = new UserInfo("danteal65");

        courses = new ArrayList<>();

        /*Query myQuery = courseRef;

        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Course course =  dataSnapshot.getValue(Course.class);
                if(course != null) {
                    courseAdapter.addCourse(course);
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
        });*/

        FirebaseDatabase.getInstance().getReference().child("Courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Course>> t = new GenericTypeIndicator<ArrayList<Course>>() {};
                courses = dataSnapshot.getValue(t);
                courseAdapter.setCourses(courses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d(TAG, "onCreate: " + courseAdapter.getItemCount());

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void InitRecyclerView(){
        Log.d(TAG, "InitRecyclerView: ");

        rvCourses = findViewById(R.id.rv_courses);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCourses.setLayoutManager(linearLayoutManager);
        //rvCourses.setHasFixedSize(true);
        courseAdapter = new CourseAdapter(this);
        rvCourses.setAdapter(courseAdapter);

    }
}
