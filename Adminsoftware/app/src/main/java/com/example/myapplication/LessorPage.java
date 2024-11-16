package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;

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
import java.util.Calendar;

public class LessorPage extends AppCompatActivity {

    private Button viewCategories;
    private Button backToMainPage;
    private Spinner spinner2;
    List<String> categoriesList = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = database.getReference("categories");
    Button buttonAddItem;
    EditText editTextName3;
    EditText editTextDescription;
    EditText editTextFee;
    EditText editStartDate;
    EditText editEndDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lessor_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        viewCategories = findViewById(R.id.viewCategories);
        backToMainPage = findViewById(R.id.backToMainPage);
        spinner2 = findViewById(R.id.spinner2);
        buttonAddItem = findViewById(R.id.buttonAddItem);
        editTextName3 = findViewById(R.id.editTextName3);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextFee = findViewById(R.id.editTextDescription2);
        editStartDate = findViewById(R.id.editTextDate1);
        editEndDate = findViewById(R.id.editTextDate2);


        viewCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessorPage.this, CategoryPage.class);
                startActivity(intent);
            }
        });

        backToMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LessorPage.this, WelcomePage.class);
                startActivity(intent);
            }
        });

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate(editStartDate);
            }
        });

        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate(editEndDate);
            }
        });
//        editEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                pickDate(editEndDate);
//            }
//        });
        loadCategory();
    }

    private void loadCategory() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoriesList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String categoryName = dataSnapshot.child("categoryName").getValue(String.class);
                    if (categoryName != null){
                    categoriesList.add(categoryName);}
                }
                ArrayList<String> adapter = new ArrayList<>(categoriesList);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(LessorPage.this, android.R.layout.simple_spinner_item, adapter);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
                Log.d("Firebase", "Categories: " + categoriesList);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to read categories", error.toException());
            }
        });

    }




    private void addItem() {
        String selectedCategory = spinner2.getSelectedItem().toString();
        String name = editTextName3.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String fee = editTextFee.getText().toString().trim();
        String startDate = editStartDate.getText().toString().trim();
        String endDate = editEndDate.getText().toString().trim();
        if (name.isEmpty() || description.isEmpty() || fee.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedCategory.isEmpty()) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseRef.push().getKey();
        Item item = new Item(id, name, description, fee, startDate, endDate, selectedCategory);
        databaseRef.child(id).setValue(item);
        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
        editTextName3.setText("");
        editTextDescription.setText("");
        editTextFee.setText("");
        editStartDate.setText("");
        editEndDate.setText("");
    }

    private void pickDate(EditText dateField) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            LessorPage.this, new DatePickerDialog.OnDateSetListener() {
                @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dateField.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, year, month, day);
        datePickerDialog.show();
    }

}