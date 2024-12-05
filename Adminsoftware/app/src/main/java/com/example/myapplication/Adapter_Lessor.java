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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Adapter_Lessor extends RecyclerView.Adapter<Adapter_Lessor.MyViewHolder> {

    Context context;

    ArrayList<Request> requestsList;
    public int requestID;




    public Adapter_Lessor(Context context, ArrayList<Request> requestsList) {
        this.context = context;
        this.requestsList = requestsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Request request = requestsList.get(position);

    // Bind data to views
    holder.itemName.setText(request.getItemName());
    holder.description.setText(request.getDescription());
    holder.categoryName.setText(request.getCategoryName());
    holder.fee.setText(request.getFee());
    holder.startDate.setText(request.getStartDate());
    holder.endDate.setText(request.getEndDate());

    // Accept button logic



        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRequestStatus(request, "Accepted", "Request Accepted");
                requestID = holder.getAdapterPosition();
                deleteRequest(requestID);

            }
        });
    // Reject button logic
        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRequestStatus(request, "rejected", "Request Rejected");
                requestID = holder.getAdapterPosition();
                deleteRequest(requestID);
            }
        });

}




    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public void deleteRequest(int ID){
        requestsList.remove(ID);
        notifyItemRemoved(ID);

    }



    private void updateRequestStatus(Request request, String newStatus, String toastMessage) {
        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference("requests")
                .child(request.getOwnerID())
                .child(request.getItemName());

        requestRef.child("status").setValue(newStatus)
                .addOnSuccessListener(unused -> {
                    for (Request req : requestsList) {
                        if (req.getItemName().equals(request.getItemName())) {
                            req.setStatus(newStatus);
                        }
                    }
                    notifyDataSetChanged(); // Refresh UI
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to update request: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public View acceptButton = itemView.findViewById(R.id.acceptButton);
        public View rejectButton = itemView.findViewById(R.id.declineButton);

        TextView itemName, categoryName, description, fee, startDate, endDate, accButton, decButton;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            categoryName = itemView.findViewById(R.id.categoryName);
            description = itemView.findViewById(R.id.descript);
            fee = itemView.findViewById(R.id.fee);
            startDate = itemView.findViewById(R.id.startD);
            endDate = itemView.findViewById(R.id.endD);
            accButton = itemView.findViewById(R.id.acceptButton);
            decButton = itemView.findViewById(R.id.declineButton);


        }
    }
}
