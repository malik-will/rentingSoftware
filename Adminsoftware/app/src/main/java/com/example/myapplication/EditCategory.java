package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditCategory  extends AppCompatActivity{
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText descriptionEdit;
    EditText categoryEdit;
    Button saveButton;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        categoryEdit = findViewById(R.id.editTextName29);
        descriptionEdit = findViewById(R.id.editTextDescription20);


        showCategory();




    }

    public void showCategory(){
        Intent intent = getIntent();

        String categoryName = intent.getStringExtra("categoryName");
        String categoryDescription = intent.getStringExtra("description");

        categoryEdit.setText(categoryName);
        descriptionEdit.setText(categoryDescription);


    }

}
