package com.example.dante.diploma;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.Step.Quiz.QuizStep;
import com.example.dante.diploma.Step.Step;
import com.example.dante.diploma.UserInfo.UserInfo;
import com.example.dante.diploma.UserInfo.UserStepInfoItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference myref;

    private ArrayList<Course> courses;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myref = database.getReference();

        UserInfo userInfo1 = new UserInfo("danteal65");

        courses = new ArrayList<>();

        Query myQuery = myref;

        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Course course = dataSnapshot.getValue(Course.class);
                courses.add(course);
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

        InitRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void InitRecyclerView(){
        recyclerView = findViewById(R.id.rv_Content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
