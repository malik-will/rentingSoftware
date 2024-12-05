package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RentedItems extends AppCompatActivity {

    ListView userListView;
    DatabaseReference databaseRef;
    ArrayList<String> rentedItemsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rented_items_page);

        userListView = findViewById(R.id.userListView);
        rentedItemsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rentedItemsList);
        userListView.setAdapter(adapter);

        // Get the current user's ID
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Reference to the "requests" node in Firebase
        databaseRef = FirebaseDatabase.getInstance().getReference("requests");

        // Fetch rented items for the current user
        databaseRef.orderByChild("myID").equalTo(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rentedItemsList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    String itemName = data.child("itemName").getValue(String.class);
                    String status = data.child("status").getValue(String.class);

                    if (itemName != null && status != null) {
                        // Format: "Item Name - Status"
                        String displayText = itemName + " - " + status;
                        rentedItemsList.add(displayText);
                    }
                }
                adapter.notifyDataSetChanged(); // Update ListView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RentedItems.this, "Failed to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
