package com.zaylabs.zaylabsapp1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ForgetPasswordActivity";


    private FirebaseAuth mAuth;
    private EditText mEmail;
    private Button mForgetPassword;
    private TextView mBackToLogin;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.etForget);
        mForgetPassword = findViewById(R.id.btnForgetPassword);
        mBackToLogin = findViewById(R.id.tv_back);


    }


    public void forgetPassword(final View view) {

        String email = mEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(ForgetPasswordActivity.this, "Enter your email!", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetPasswordActivity.this, "Re-set Email Sent", Toast.LENGTH_SHORT).show();
                                backToLogin(view);
                            } else {
                                Toast.makeText(ForgetPasswordActivity.this, "Email Sending Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        backToLogin(view);
    }

}



