package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RentorCategoryView extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseRef;
    Adapter_Category myAdapter;
    ArrayList<Category> categoryList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_recyclerview);


        recyclerView = findViewById(R.id.itemlist);
        databaseRef = FirebaseDatabase.getInstance().getReference("categories");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()) {

                    String description = data.child("description").getValue(String.class);
                    String categoryName = data.child("categoryName").getValue(String.class);
                    String id = data.child("id").getValue(String.class);

                    Category category = new Category(id, categoryName, description);
                    categoryList.add(category);
                }
                myAdapter = new Adapter_Category(RentorCategoryView.this, categoryList);
                recyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
