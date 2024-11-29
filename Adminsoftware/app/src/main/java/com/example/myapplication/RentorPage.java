package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

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

public class RentorPage extends AppCompatActivity {
    SearchView searchView;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rentor_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView = findViewById(R.id.searchView);

        listView=findViewById(R.id.searchCategories);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCategories(query); // Fetch results from Firebase
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCategories(newText); // Dynamically update results
                return false;
            }
        });


    }
    public void searchCategories(String query) {
        if (query.equals("")){
            return;
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");
        databaseReference.orderByChild("categoryName").startAt(query).endAt(query + "\uf8ff")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Category> categories = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            String name = data.child("categoryName").getValue(String.class);
                            String desc = data.child("description").getValue(String.class);
                            String id = data.child("id").getValue(String.class);
                            Category category = new Category(id,name,desc);
                            categories.add(category);
                        }
                        CategoryList categoryList = new CategoryList(RentorPage.this,categories);
                        listView.setAdapter(categoryList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }




}