package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dante.diploma.Course.Course;
import com.example.dante.diploma.Course.CourseAdapter;
import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity
        implements View.OnClickListener {

    private final String TAG = "Main Activity";

    private FirebaseAuth mAuth;

    private ArrayList<Course> courses;

    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;

    private Button btnSettings, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        btnSettings = findViewById(R.id.btn_settings);
        btnSignOut = findViewById(R.id.btn_log_out);

        btnSettings.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        courses = new ArrayList<>();

        FirebaseUtils.getInstance().getCoursesRef().
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InitRecyclerView();
                GenericTypeIndicator<ArrayList<Course>> t =
                        new GenericTypeIndicator<ArrayList<Course>>() {};
                courses = dataSnapshot.getValue(t);
                courseAdapter.setCourses(courses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_settings:
                Intent intent =
                        new Intent(CoursesActivity.this,
                                SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_log_out:
                mAuth.signOut();
                Intent intentSignIn =
                        new Intent(CoursesActivity.this,
                                EmailPasswordActivity.class);
                startActivity(intentSignIn);
                break;
        }
    }
}
