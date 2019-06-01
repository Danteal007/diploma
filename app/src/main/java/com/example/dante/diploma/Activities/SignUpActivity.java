package com.example.dante.diploma.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private EditText etEmail, etPassword, etConfirmPassword,
            etName, etLastName;
    private TextView tvCheckPasswords;
    private Button btnFinishSignUp;

    private boolean passwordsAreChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.et_email_sign_up);
        etPassword = findViewById(R.id.et_password_sign_up);
        etConfirmPassword = findViewById(R.id.et_confirm_password_sign_up);
        etName = findViewById(R.id.et_user_name);
        etLastName = findViewById(R.id.et_user_last_name);

        tvCheckPasswords = findViewById(R.id.tv_passwords_check);
        btnFinishSignUp = findViewById(R.id.btn_confirm_sign_up);

        TextWatcher passwordsChecker = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence,
                                          int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence,
                                      int i, int i1, int i2) {
                if(etPassword.getEditableText().
                        toString().
                        equals(etConfirmPassword.
                                getEditableText().
                                toString())){
                    passwordsAreChecked = true;
                    tvCheckPasswords.setText("Пароли совпадают");
                }
                else {
                    passwordsAreChecked = false;
                    tvCheckPasswords.setText("Пароли не совпадают");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        etPassword.addTextChangedListener(passwordsChecker);
        etConfirmPassword.addTextChangedListener(passwordsChecker);
        btnFinishSignUp.setOnClickListener(this);
    }

    public void SignUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DiplomaUserInfo diplomaUserInfo =
                            new DiplomaUserInfo(etName.getText().toString(),
                                    etLastName.getText().toString());
                    user = mAuth.getCurrentUser();

                    FirebaseDatabase.getInstance().getReference("Users").
                            child(user.getUid()).
                            setValue(diplomaUserInfo);

                    Toast.makeText(SignUpActivity.this,
                            "Регистрация прошла успешно",
                            Toast.LENGTH_LONG).show();

                    SignUpActivity.this.finish();
                }else{
                    Toast.makeText(SignUpActivity.this,
                            "Ошибка регистрации",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm_sign_up:
                if(passwordsAreChecked) {
                    SignUp(etEmail.getText().toString(),
                            etPassword.getText().toString());
                }
                break;
        }
    }
}
