package com.example.myeventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText editTextemail;
    private EditText editTextpassword;
    private EditText editTextPasswordConfirm;
    private Button buttonSignUp;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextemail = findViewById(R.id.etEmail);
        editTextpassword = findViewById(R.id.etPassword);
        buttonSignUp = findViewById(R.id.btnSignUp);
        editTextPasswordConfirm = findViewById(R.id.etPasswordConfirm);

        Auth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();
        String passwordConfirm = editTextPasswordConfirm.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordConfirm)){
            Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(passwordConfirm)){
            Toast.makeText(this, "passwords should match", Toast.LENGTH_SHORT).show();
            return;
        }

        Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Sign up sucsessful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
//                    intent goes here
                }
                else {
                    Toast.makeText(SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}