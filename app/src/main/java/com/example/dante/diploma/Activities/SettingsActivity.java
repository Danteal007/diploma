package com.example.dante.diploma.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {

    private TextView tvUserName;
    private TextView tvUserEmail;

    private DiplomaUserInfo diplomaUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEmail = findViewById(R.id.tv_user_email);

        FirebaseUtils.getInstance().getUsersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                diplomaUserInfo = dataSnapshot.child(FirebaseUtils.getInstance().getCurrentUserID()).getValue(DiplomaUserInfo.class);
                tvUserName.setText(diplomaUserInfo.getName());
                tvUserEmail.setText(FirebaseUtils.getInstance().getCurrentUser().getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
