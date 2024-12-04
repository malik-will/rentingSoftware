package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter_Lessor extends RecyclerView.Adapter<Adapter_Lessor.MyViewHolder> {

    Context context;

    ArrayList<Item> itemList;

    public Adapter_Lessor(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.categoryName.setText(item.getCategoryName());
        holder.fee.setText(item.getFee());
        holder.description.setText(item.getDescription());
        holder.startDate.setText(item.getStartDate());
        holder.endDate.setText(item.getEndDate());



    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, categoryName, description, fee, startDate, endDate, actionButton;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            categoryName = itemView.findViewById(R.id.categoryName);
            description = itemView.findViewById(R.id.descript);
            fee = itemView.findViewById(R.id.fee);
            startDate = itemView.findViewById(R.id.startD);
            endDate = itemView.findViewById(R.id.endD);
            actionButton = itemView.findViewById(R.id.actionButton);
        }
    }
}
