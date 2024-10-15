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

        auth = FirebaseAuth.getInstance();
        emaillogin = findViewById(R.id.elogin);
        passlogin = findViewById(R.id.passlogin);
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

                if(!useremail.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(useremail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login_page.this, "Login Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login_page.this, WelcomePage.class));

                            }
                        });
                    } else if(pass.isEmpty()){
                        passlogin.setError("Password Cannot be empty");
                    }else{
                        passlogin.setError("Password is wrong");
                    }
                }else if(useremail.isEmpty()){
                    emaillogin.setError("Email cannot be empty");
                }else{
                    emaillogin.setError("Please enter valid email");
                }

                }
        });


    }
}