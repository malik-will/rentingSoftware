package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends AppCompatActivity{

    EditText editTextName;
    EditText editTextDescription;
    private DatabaseReference databaseCategories;
    private List<Category> categoryList;
    ListView listViewCategories;
    Button buttonAddcategory;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<User> users;
    ListView listView;
    Button display;
    Button deleteUser;
    EditText userToDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        databaseCategories = FirebaseDatabase.getInstance().getReference("categories");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.adminpage2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = (EditText) findViewById(R.id.editTextName29);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription278);
        //listViewCategories = (ListView) findViewById(R.id.listViewCategories);
        buttonAddcategory = (Button) findViewById(R.id.buttonAddcategory556);

        categoryList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference =database.getReference().child("users");
        //listView = findViewById(R.id.listViewUsers);
        users = new ArrayList<>();
        display = (Button) findViewById(R.id.buttonDisplayUsers);
        deleteUser = (Button) findViewById(R.id.buttonDeleteUser);

        database = FirebaseDatabase.getInstance();
        databaseReference =database.getReference().child("users");
        userToDelete = (EditText) findViewById(R.id.editTextName39);
        users = new ArrayList<>();

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userToDelete.getText().toString();
                for (User user: users){
                    if (user.getName().equals(name)){
                        //Toast.makeText(AdminPage.this, "User Deleted", Toast.LENGTH_SHORT).show();
                        databaseReference.child(user.getId()).removeValue().addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                Toast.makeText(AdminPage.this, "User Deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
                userToDelete.setText("");


            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this,UserDisplay.class);
                startActivity(intent);
            }
        });

        buttonAddcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });

    }

    protected void onStart() {

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot data : snapshot.getChildren()) {

                    String name = data.child("name").getValue(String.class);
                    String role = data.child("role").getValue(String.class);
                    String username = data.child("username").getValue(String.class);
                    String email = data.child("email").getValue(String.class);
                    String id = data.child("id").getValue(String.class);
                    User user = new User(name, username, email, role, id);
                    users.add(user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }


    private void addCategory() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String id = databaseCategories.push().getKey();
            Category category = new Category(id, name, description);
            databaseCategories.child(id).setValue(category);
            editTextName.setText("");
            editTextDescription.setText("");
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}

