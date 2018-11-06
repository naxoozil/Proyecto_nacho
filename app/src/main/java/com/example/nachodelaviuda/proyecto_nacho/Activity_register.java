package com.example.nachodelaviuda.proyecto_nacho;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class Activity_register extends AppCompatActivity implements View.OnClickListener {
    /*
     * Autenticacion usuario con Firebase
     */
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    private static final String TAG = "EmailPassword";

    private EditText edtNombre;
    private EditText edtEdad;
    private EditText edtCorreo;
    private EditText edtContrasenia;
    private EditText edtcontrasenia2;
    private boolean usuarioCreado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViewById(R.id.button_register_cancel).setOnClickListener(this);
        findViewById(R.id.button_register_sucessfully).setOnClickListener(this);

        // Declaracion
        edtNombre = findViewById(R.id.editText_register_name);
        edtEdad = findViewById(R.id.editText_register_edad);
        edtCorreo = findViewById(R.id.editText_register_correo);
        edtContrasenia = findViewById(R.id.editText_register_password);
        edtcontrasenia2 = findViewById(R.id.editText_register_confirm_password);

        mAuth = FirebaseAuth.getInstance();

        usuarioCreado = false;
    }

    private boolean validarDatos() {
        // Declaracion de variables

        boolean continuar = true;

        // [[COMPROBACIONES]]
        String email = edtNombre.getText().toString();
        if (TextUtils.isEmpty(email)) {
            edtNombre.setError("Required.");
            continuar = false;
        } else {
            edtNombre.setError(null);
        }

        String edad = edtEdad.getText().toString();
        if (TextUtils.isEmpty(edad)) {
            edtEdad.setError("Required.");
            continuar = false;
        } else {
            edtEdad.setError(null);
        }

        String correo = edtCorreo.getText().toString();
        if (TextUtils.isEmpty(correo)) {
            edtCorreo.setError("Required.");
            continuar = false;
        } else {
            edtCorreo.setError(null);
        }

        String contrasenia = edtContrasenia.getText().toString();
        if (TextUtils.isEmpty(contrasenia)) {
            edtContrasenia.setError("Required.");
            continuar = false;
        } else {
            edtContrasenia.setError(null);
        }

        String confirmarContrasenia = edtcontrasenia2.getText().toString();
        if (TextUtils.isEmpty(confirmarContrasenia)) {
            edtcontrasenia2.setError("Required.");
            continuar = false;
        } else if (!confirmarContrasenia.trim().equals(contrasenia.trim())) {
            edtcontrasenia2.setError("Needs to be the same password");
        }else{
            edtcontrasenia2.setError(null);
        }
        return continuar;
    }


    //[FIREBASE] CREAR USUARIO A TRAVES DEL CORREO Y CONTRASEÑA
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validarDatos()) {
            Toast.makeText(Activity_register.this, "No se ha completado el registro", Toast.LENGTH_LONG).show();
        } else {
            // [START create_user_with_email]
            Toast.makeText(Activity_register.this, "De momento vas bn", Toast.LENGTH_LONG).show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //[AZAHARA]--------------------------------------------------------------
                    if (task.isSuccessful()) {
                        usuario = mAuth.getCurrentUser();
                        //Agregamos el correo del usuario al perfil del usuario
                        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().
                                setDisplayName(edtCorreo.getText().toString()).build();

                        //Devolvemos el nombre de usuario para pasarselo al toast
                        usuario.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    String us = usuario.getDisplayName();
                                    Toast.makeText(Activity_register.this, "Bienvenid@" + us, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Activity_register.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        //Toast.makeText(Activity_register.this, "Authentication failed.",
                        //       Toast.LENGTH_SHORT).show();


                        //[Azahara]--------------------------------------------------------------
                        //En el caso de que exista el usuario no se volverá a crear
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(Activity_register.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Activity_register.this, "Error al regisrar usuario", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
        // [END create_user_with_email]
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_register_cancel) {
            Intent intentoCancelar = new Intent(Activity_register.this, LoginActivity.class);
            startActivity(intentoCancelar);
        } else if (i == R.id.button_register_sucessfully) {
            createAccount(edtCorreo.getText().toString(), edtContrasenia.getText().toString());

            Intent intentoAprobado = new Intent(Activity_register.this, LoginActivity.class);
            intentoAprobado.putExtra("pasoDeEmail", edtCorreo.getText().toString().trim());
            startActivity(intentoAprobado);
        }
    }

}
