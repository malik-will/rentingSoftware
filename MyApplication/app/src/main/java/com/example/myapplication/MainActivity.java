package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    Spinner spinner;
    EditText name;
    EditText userName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        name = (EditText) findViewById(R.id.editTextText);
        userName = (EditText) findViewById(R.id.editTextText2);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        confirmPassword = (EditText) findViewById(R.id.editTextTextPassword2);
        button = (Button) findViewById(R.id.button);




        button.setOnClickListener((View view)->{
            if(validateFields()){
                Intent welcomePageIntent = new Intent(MainActivity.this,WelcomePage.class);
                startActivity(welcomePageIntent);
            }
        });


        createDropDown();




    }


    //Creates dropdown menu allowing users to choose either lessor or rentor;
    private void createDropDown(){


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,new String[]{"Lessor","Rentor"});
        spinner.setAdapter(arrayAdapter);
    }






    //Validates user inputs
    private boolean validateFields(){
        boolean allFieldsValid = true;




        if(name.getText().toString().isEmpty()){
            name.setError("Name field can't be empty");
            allFieldsValid=false;
        }
        if(userName.getText().toString().isEmpty()){
            userName.setError("Username field can't be empty");
            allFieldsValid=false;
        }
        if(email.getText().toString().isEmpty()){
            email.setError("Email field can't be empty");
            allFieldsValid=false;
        }
        else if (email.getText().toString().contains("@")){
            email.setError("Must be a valid email");
            allFieldsValid=false;
        }


        if(password.getText().toString().isEmpty()){
            password.setError("Password field can't be empty");
            allFieldsValid=false;
        }


        if(confirmPassword.getText().toString().isEmpty()){
            confirmPassword.setError("Confirm password field can't be empty");
            allFieldsValid=false;
        }
        else if(!confirmPassword.getText().toString().equals(password.getText().toString())){
            password.setError("Password and Confirm password are not equal");
            confirmPassword.setError("Password and Confirm password are not equal");
            allFieldsValid=false;
        }


        return allFieldsValid;


    }




}

