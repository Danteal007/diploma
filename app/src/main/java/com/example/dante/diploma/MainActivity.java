package com.example.dante.diploma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.Step.Quiz.QuizStep;
import com.example.dante.diploma.Step.Step;
import com.example.dante.diploma.UserInfo.UserInfo;
import com.example.dante.diploma.UserInfo.UserStepInfoItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase database;
    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        myref = database.getReference();

        Topic topic1 = new Topic(1.0,"Введение");
        topic1.addStep(new Step("Какая-то статья"));

        ArrayList<QuizItem> quizItems = new ArrayList<>();
        quizItems.add(new QuizItem("nhuirghnu", true));
        topic1.addStep(new QuizStep("Какой то вопрос",1, quizItems));

        UserInfo userInfo = new UserInfo("danteal65");
        userInfo.getCourseResults().add(new UserStepInfoItem(topic1.getSteps().get(0)));

        myref.child("users").child(userInfo.getEmail()).setValue(userInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
