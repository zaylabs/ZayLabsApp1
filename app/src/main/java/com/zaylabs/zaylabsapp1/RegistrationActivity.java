package com.zaylabs.zaylabsapp1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends BaseActivity {

    private static final String TAG ="";
    private EditText mPassword;
    private EditText mName;
    private AutoCompleteTextView mEmail;
    private EditText mPhone;
    private RadioGroup mRadioGroup;
    private RadioButton mVT1;
    private RadioButton mVT2;
    private FirebaseAuth mAuth;
    private Button mRegister;
    private TextView mBack;
    private int mVTInt;
    private RadioButton mVTRadio;
    private String mVT;
    private View view;
    private DatabaseReference mDatabase;
    private DatabaseReference mDBRef;
    private String userID;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        //userID = mAuth.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        //mDBRef = mDatabase.child("driver").child(userID);
        db = FirebaseFirestore.getInstance();


        mName = (EditText) findViewById(R.id.et_name);
        mPhone= (EditText) findViewById(R.id.et_phone);
        mRadioGroup=(RadioGroup) findViewById(R.id.radio_group);
        mVT1 = (RadioButton)findViewById(R.id.rb_vt1);
        mVT2 = (RadioButton)findViewById(R.id.rb_vt2);
        mBack=(TextView)findViewById(R.id.tv_back);
        mRegister = (Button)findViewById(R.id.register_button);
        mPassword = (EditText)findViewById(R.id.password);
        mEmail=(AutoCompleteTextView)findViewById(R.id.email);


    }

    @Override
    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null){
            if (!(mAuth.getCurrentUser().isEmailVerified())){
                Toast.makeText(RegistrationActivity.this, "Kindly verify your email", Toast.LENGTH_SHORT).show();
            }
            else {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                finish();
            }
        }
    }

    public void signUp(View view) {
        Log.d(TAG, "signUp");
        if (!validateSignUpForm()) {
            return;
        }

        showProgressDialog();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        //Set Display name
        userdisplayname();
        //setphonenumber
        saveMap();
        // Go to MainActivity
        sendEmailVerification();

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        finish();
    }

    private void sendEmailVerification() {
        // Disable button
       // findViewById(R.id.verify_email_button).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
         //               findViewById(R.id.verify_email_button).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegistrationActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateSignUpForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError("Required");
            result = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            mPassword.setError("Required");
            result = false;
        } else {
            mPassword.setError(null);
        }
        if (TextUtils.isEmpty(mName.getText().toString())) {
            mName.setError("Required");
            result = false;
        } else {
            mName.setError(null);
        }

        if (TextUtils.isEmpty(mPhone.getText().toString())) {
            mPhone.setError("Required");
            result = false;
        } else {
            mPhone.setError(null);
        }


        return result;
    }

    private void userdisplayname() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName.getText().toString())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    public void saveMap() {

        mVTInt = mRadioGroup.getCheckedRadioButtonId();
        mVTRadio = findViewById(mVTInt);
        mVT = mVTRadio.getText().toString();


        Map<String, Object> userUpdates = new HashMap<>();
        final String name = mName.getText().toString();
        userUpdates.put("name", name);
        final String email = mAuth.getCurrentUser().getEmail();
        userUpdates.put("email", email);
        final String phone = mPhone.getText().toString();
        userUpdates.put("phone", phone);
        final String VT = mVT;
        userUpdates.put("vt", VT);

        db.collection("drivers")
                .add(userUpdates)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        //mDBRef.updateChildren(userUpdates);
    }


    public void backToLogin(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        backToLogin(view);
    }
}
