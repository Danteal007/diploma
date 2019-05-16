package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dante.diploma.Adapters.CourseAdapter;
import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "Main Activity";

    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference courseRef;

    private ArrayList<Course> courses;

    private RecyclerView rvCourses;
    private CourseAdapter courseAdapter;

    private Button btnSettings, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSettings = findViewById(R.id.btn_settings);
        btnSignOut = findViewById(R.id.btn_log_out);

        btnSettings.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);



        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        courses = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("Courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InitRecyclerView();
                GenericTypeIndicator<ArrayList<Course>> t = new GenericTypeIndicator<ArrayList<Course>>() {};
                courses = dataSnapshot.getValue(t);
                courseAdapter.setCourses(courses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Log.d(TAG, "onCreate: " + courseAdapter.getItemCount());

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_log_out:
                mAuth.signOut();
                Intent intentSignIn = new Intent(MainActivity.this, EmailPasswordActivity.class);
                startActivity(intentSignIn);
                break;
        }
    }
}
