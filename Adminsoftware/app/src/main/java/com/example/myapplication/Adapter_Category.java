package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.MyViewHolder> {

    Context context;

    ArrayList<Category> categoryList;


    public Adapter_Category(Context context, ArrayList<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_item,parent, false);
        return new MyViewHolder(v);
    }

//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Category request = categoryList.get(position);
//        holder.categoryName.setText(request.getCategoryName());
//        holder.description.setText(request.getDescription());
//
//
//    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category request = categoryList.get(position);
        holder.categoryName.setText(request.getCategoryName());
        holder.description.setText(request.getDescription());

        // Use the context from the adapter
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the new activity
                Intent intent = new Intent(context, RentorCategoryView.class);

                // Optionally pass data to the new activity
                intent.putExtra("categoryName", request.getCategoryName());
                intent.putExtra("description", request.getDescription());

                // Start the new activity
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  categoryName, description, actionButton;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            description = itemView.findViewById(R.id.descript);
            actionButton = itemView.findViewById(R.id.actionButton);

        }
    }
}
