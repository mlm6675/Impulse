package com.example.impulse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private Button logoutBtn;
    private Button loginBtn;
    private Button regBtn;
    private EditText emailTxtView;
    private EditText pswdTxtView;
    private TextView msgTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        logoutBtn = findViewById(R.id.idBtnLogout);
        loginBtn = findViewById(R.id.idBtnLogin);
        regBtn = findViewById(R.id.idBtnReg);
        emailTxtView = findViewById(R.id.idEdtUserName);
        pswdTxtView = findViewById(R.id.idEdtPassword);
        msgTxtView = findViewById(R.id.idTVHeader);

        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(emailTxtView.getText().toString(), pswdTxtView.getText().toString());
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signIn(emailTxtView.getText().toString(), pswdTxtView.getText().toString());
                mAuth.signOut();
                updateUI(null);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(emailTxtView.getText().toString(), pswdTxtView.getText().toString());
            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
        }
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(MainActivity.this, "None of the fields should be blank. Try again after typing stuff.",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "createUserWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(MainActivity.this, "None of the fields should be blank. Try again after typing stuff.",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "login:success",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
        // [END send_email_verification]
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // user profile

            Intent myIntent = new Intent(MainActivity.this, Home.class);
            MainActivity.this.startActivity(myIntent);

//            msgTxtView.setText("welcome " + user.getEmail());
//            // hide edittexts and login & sign up buttons
//            emailTxtView.setVisibility(View.INVISIBLE);
//            pswdTxtView.setVisibility(View.INVISIBLE);
//            loginBtn.setVisibility(View.INVISIBLE);
//            regBtn.setVisibility(View.INVISIBLE);
//            // display logout button
//            logoutBtn.setVisibility(View.VISIBLE);



        } else {
            msgTxtView.setText("Welcome to CMPSC 475 \\n Login Form");
            emailTxtView.setVisibility(View.VISIBLE);
            pswdTxtView.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.VISIBLE);
            regBtn.setVisibility(View.VISIBLE);
            // display logout button
            logoutBtn.setVisibility(View.INVISIBLE);
        }
        emailTxtView.setText("");
        pswdTxtView.setText("");
    }
}