package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dante.diploma.Course.Course;
import com.example.dante.diploma.Course.CourseAdapter;
import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    private final String TAG = "Courses Activity";

    private ArrayList<Course> courses;

    private TextView tvLoading;
    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;

    private Toolbar toolbar;

    private FirebaseUtils FBUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        FBUtils = FirebaseUtils.getInstance();

        toolbar = findViewById(R.id.toolbar);
        tvLoading = findViewById(R.id.tv_loading);

        setSupportActionBar(toolbar);
        courses = new ArrayList<>();

        FBUtils.getCoursesRef().
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InitRecyclerView();
                GenericTypeIndicator<ArrayList<Course>> t =
                        new GenericTypeIndicator<ArrayList<Course>>() {};
                courses = dataSnapshot.getValue(t);
                courseAdapter.setCourses(courses);
                if(courses.size()>0) {
                    tvLoading.setVisibility(View.GONE);
                    rvCourses.setVisibility(View.VISIBLE);
                }
                else {
                    tvLoading.setVisibility(View.VISIBLE);
                    rvCourses.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_profile:
                Intent intent =
                        new Intent(CoursesActivity.this,
                                SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void InitRecyclerView(){
        rvCourses = findViewById(R.id.rv_courses);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCourses.setLayoutManager(linearLayoutManager);
        courseAdapter = new CourseAdapter(this);
        rvCourses.setAdapter(courseAdapter);
    }
}
