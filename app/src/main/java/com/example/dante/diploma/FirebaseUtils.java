package com.example.dante.diploma;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtils {
    private FirebaseDatabase mDatabase;

    private static FirebaseUtils instance = new FirebaseUtils();

    private FirebaseUtils(){
        mDatabase = FirebaseDatabase.getInstance();
    }
    public static FirebaseUtils getInstance(){
        return instance;
    }

    public DatabaseReference getDatabaseRef(){
        return mDatabase.getReference();
    }

    public FirebaseAuth getAuth(){
        return FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser(){
        return getAuth().getCurrentUser();
    }

    public String getCurrentUserID(){
        return getCurrentUser().getUid();
    }

    public DatabaseReference getCurrentUserRef(){
        return mDatabase.getReference("Users").child(getAuth().getCurrentUser().getUid());
    }

    public DatabaseReference getUsersRef(){
        return mDatabase.getReference("Users");
    }

    public DatabaseReference getEducationPlacesRef(){
        return mDatabase.getReference("EducationPlaces");
    }

    public DatabaseReference getCoursesRef(){
        return mDatabase.getReference("Courses");
    }

    public DatabaseReference getCourseUserInfos(int coursePos){
        return getCurrentUserRef().child("courseUserInfos").child(Integer.toString(coursePos));
    }

    public DatabaseReference getTopicUserInfos(int coursePos, int topicPos){
        return getCourseUserInfos(coursePos).child("topicUserInfos").child(Integer.toString(topicPos));
    }

    public DatabaseReference getStepUserInfos(int coursePos, int topicPos, int stepPos){
        return getTopicUserInfos(coursePos, topicPos).child("stepUserInfos").child(Integer.toString(topicPos));
    }
}
