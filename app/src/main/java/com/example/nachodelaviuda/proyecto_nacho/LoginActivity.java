package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    AutoCompleteTextView campoEmail;
    private EditText campoPassword;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    //------------------------------->nuevo
    private FirebaseAuth.AuthStateListener mAuthListener;//<----------------------
    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        campoEmail = (AutoCompleteTextView) findViewById(R.id.email);
        String recuperoIntent = getIntent().getStringExtra("pasoDeEmail");
        campoEmail.setText(recuperoIntent);
        campoPassword = findViewById(R.id.password);

        // Buttons
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_register_button).setOnClickListener(this);


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //------------------------------->nuevo
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Main Activity", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("Main Activity", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // [END initialize_auth]
    }

    // [START on_start_check_user]
    // verifica que el usuario haya accedido
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }
    // [END on_start_check_user]

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            String nomUser = mUser.getDisplayName();
                            Toast toast1 = Toast.makeText(getApplicationContext(), nomUser, Toast.LENGTH_SHORT);
                            toast1.show();
                            Log.d(TAG, "signInWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });
        // [END sign_in_with_email]



        /*mAuth.signInWithEmailAndPassword("example@email.com", "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Main Activity", "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
    }


    private boolean validateForm() {
        boolean valid = true;

        String email = campoEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            campoEmail.setError("Required.");
            valid = false;
        } else {
            campoEmail.setError(null);
        }

        String password = campoPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            campoPassword.setError("Required.");
            valid = false;
        } else {
            campoPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_sign_in_button) {
            if (mAuth != null) {
                signIn(campoEmail.getText().toString(), campoPassword.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
        } else if (i == R.id.email_register_button) {
            //Pasar datos a la segunda pantalla

            Intent intent = new Intent(this, Activity_register.class);
            startActivity(intent);
        }
    }


}

