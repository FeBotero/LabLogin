package com.motoacademy.lablogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Register extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    MaterialButton btnReg;
    TextView gotoLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.etName);
        txtPassword= findViewById(R.id.etPassword);
        btnReg = findViewById(R.id.btRegister);
        gotoLogin = findViewById(R.id.textGotoLogin);
        progressBar = findViewById(R.id.progressbar);


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                progressBar.setVisibility(View.VISIBLE);
                btnReg.setVisibility(View.GONE);

                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Email field cannot is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Password field cannot is empty", Toast.LENGTH_SHORT).show();

                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Success", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Failed", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed!",
                                            Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    btnReg.setVisibility(View.VISIBLE);

                                }
                            }
                        });
            }
        });
    }
}