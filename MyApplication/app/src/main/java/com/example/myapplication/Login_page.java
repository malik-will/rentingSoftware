package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_page extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText passlogin, emaillogin;
    private TextView signupRedirect;
    private Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Connecting to Database and fetching data entered(email and password)
        auth = FirebaseAuth.getInstance();
        emaillogin = findViewById(R.id.elogin);
        passlogin = findViewById(R.id.passlogin);

        //Signup button Functionality
        signupRedirect = findViewById(R.id.signupRedirect);
        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_page.this, MainActivity.class));
            }
        });

        loginbutton  = findViewById(R.id.login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = emaillogin.getText().toString();
                String pass = passlogin.getText().toString();

                if(useremail.equals("admin")&&pass.equals("XPI76SZUqyCjVxgnUjm0")){
                    auth.signInWithEmailAndPassword("admin@gmail.com", pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login_page.this, "Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_page.this, WelcomePage.class));

                        }
                    });

                }
                else if(useremail.isEmpty()){
                    emaillogin.setError("Email cannot be empty");
                    return;
                }
                else if(pass.isEmpty()){
                    passlogin.setError("Password Cannot be empty");
                    return;
                }

                else if(pass.length()<6){
                    passlogin.setError("Password must be at least 6 characters");
                    return;
                }
                if(Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                    auth.signInWithEmailAndPassword(useremail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_page.this, WelcomePage.class));

                        }

                    }).addOnFailureListener(e -> {
                        // This will run when the credentials do not match any user
                        Toast.makeText(Login_page.this, "Login Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

                }
                else{
                    passlogin.setError("Password is invalid for this email");
                }




            }
        });


    }
}