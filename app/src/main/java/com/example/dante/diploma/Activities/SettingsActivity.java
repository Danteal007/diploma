package com.example.dante.diploma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dante.diploma.EducationPlaceInfo.EducationPlaceInfo;
import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseUtils FBUtils;

    private TextView tvUserName;
    private TextView tvUserLastName;
    private TextView tvUserEmail;
    private TextView tvUserEducationPlace;

    private DiplomaUserInfo diplomaUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FBUtils = FirebaseUtils.getInstance();

        tvUserName              = findViewById(R.id.tv_user_name);
        tvUserLastName          = findViewById(R.id.tv_user_last_name);
        tvUserEmail             = findViewById(R.id.tv_user_email);
        tvUserEducationPlace    = findViewById(R.id.tv_user_education_place);

        FBUtils.getUsersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                diplomaUserInfo = dataSnapshot.child(FBUtils.getCurrentUserID()).getValue(DiplomaUserInfo.class);
                tvUserName.setText(diplomaUserInfo.getName());
                tvUserLastName.setText(diplomaUserInfo.getLastName());
                tvUserEmail.setText(FirebaseUtils.getInstance().getCurrentUser().getEmail());}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FBUtils.getEducationPlacesRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                diplomaUserInfo.setEducationPlaceInfo(dataSnapshot.child(Integer.toString(diplomaUserInfo.getEducationPlace())).getValue(EducationPlaceInfo.class));
                tvUserEducationPlace.setText(diplomaUserInfo.getEducationPlaceInfo().getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
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
            case R.id.menu_item_sign_out:
                FBUtils.getAuth().signOut();
                Intent intentSignIn =
                        new Intent(this,
                                EmailPasswordActivity.class);
                startActivity(intentSignIn);
                break;
        }
        return true;
    }
}
