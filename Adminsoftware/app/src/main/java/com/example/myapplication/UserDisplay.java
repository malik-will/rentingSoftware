package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDisplay extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<User> users;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userdisplay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = FirebaseDatabase.getInstance();
        databaseReference =database.getReference().child("users");
        listView = findViewById(R.id.userListView);
        users = new ArrayList<>();

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
                    String id = data.child("id").getValue(String.class);
                    User user = new User(name,username,email,role,id);
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
        UserList userList = new UserList(UserDisplay.this,users);
        listView.setAdapter(userList);
    }
}