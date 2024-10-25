package com.example.myapplication;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.content.Intent;
import android.widget.AdapterView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;

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


    @Override
    protected void onCreate(Bundle savedInstanceState){
        databaseCategories = FirebaseDatabase.getInstance().getReference("categories");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        listViewCategories = (ListView) findViewById(R.id.listViewCategories);
        buttonAddcategory = (Button) findViewById(R.id.buttonAddcategory);

        categoryList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference =database.getReference().child("users");
        listView = findViewById(R.id.listViewUsers);
        users = new ArrayList<>();


        buttonAddcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategory();
            }
        });

    }
    @Override
    protected void onStart(){

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for(DataSnapshot data: snapshot.getChildren()){

                    String name = data.child("name").getValue(String.class);
                    String role = data.child("role").getValue(String.class);
                    String username = data.child("username").getValue(String.class);
                    String email = data.child("email").getValue(String.class);
                    User user = new User(name,username,email,role);
                    users.add(user);
                }
                showUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void showUsers(){
        UserList userList = new UserList(AdminPage.this,users);
        listView.setAdapter(userList);
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

