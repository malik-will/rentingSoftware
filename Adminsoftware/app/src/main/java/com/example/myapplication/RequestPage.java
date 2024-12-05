package com.example.myapplication;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class RequestPage extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseRef;
    Adapter_Lessor myAdapter;
    ArrayList<Request> requestsList;
    public String loginIDFetched = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_recyclerview);


        recyclerView = findViewById(R.id.itemlist);
        databaseRef = FirebaseDatabase.getInstance().getReference("requests");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        databaseRef.orderByChild("ownerID").equalTo(loginIDFetched).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                requestsList = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    String ownerID = data.child("ownerID").getValue(String.class);
                    String itemname = data.child("itemName").getValue(String.class);
                    String description = data.child("description").getValue(String.class);
                    String startd = data.child("startDate").getValue(String.class);
                    String endd = data.child("endDate").getValue(String.class);
                    String categoryName = data.child("categoryName").getValue(String.class);
                    String fee = data.child("fee").getValue(String.class);
                    String myID = data.child("myID").getValue(String.class);



                    if(ownerID.equals(loginIDFetched)){
                        Request request = new Request(itemname, description, fee, startd, endd, categoryName, ownerID, myID,"null");
                        requestsList.add(request);}

                }
                myAdapter = new Adapter_Lessor(RequestPage.this, requestsList);
                recyclerView.setAdapter(myAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
