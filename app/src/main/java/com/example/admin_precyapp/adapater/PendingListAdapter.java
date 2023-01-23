package com.example.admin_precyapp.adapater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_precyapp.PendingReservationList;
import com.example.admin_precyapp.R;
import com.example.admin_precyapp.model.PendingListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.MyViewHolder> {

    Context context;
    ArrayList<PendingListModel> list;

    public PendingListAdapter(Context context, ArrayList<PendingListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PendingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        return  new PendingListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingListAdapter.MyViewHolder holder, int position) {

        PendingListModel pendingListModel = list.get(position);
        holder.reservationDate.setText(pendingListModel.getReservationDate());
        holder.name.setText(pendingListModel.getName());
        holder.mobileno.setText(pendingListModel.getMobilenum());
        holder.reservationID.setText(pendingListModel.getReservationID());
        holder.event.setText(pendingListModel.getEvent());
        holder.numofPeople.setText(pendingListModel.getNumofPeople());
        holder.userID.setText(pendingListModel.getUserID());
        holder.timeOfReservation.setText(pendingListModel.getTimeOfReservation());
        holder.dateOfReservation.setText(pendingListModel.getDateOfReservation());
        holder.companyName.setText(pendingListModel.getCompanyName());
        holder.venue.setText(pendingListModel.getVenue());
        holder.gCashNumber.setText(pendingListModel.getgCashNum());

        if(pendingListModel.getStatus().equals(true)){
            holder.statusCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.approvedbg));
            holder.statusTxtView.setTextColor(ContextCompat.getColor(context, R.color.fontcolorapproved));
            holder.statusTxtView.setText("Approved");
        }

        if(pendingListModel.getStatus().equals(false)){

            holder.statusCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.pendingbg));
            holder.statusTxtView.setTextColor(ContextCompat.getColor(context, R.color.fontcolorpending));
            holder.statusTxtView.setText("Pending");

        }

        holder.statusCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Enter User UID and Reservation ID to approve", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reservationID, name, mobileno, reservationDate, event, venue, companyName,
                numofPeople, statusTxtView, userID, dateOfReservation, timeOfReservation, gCashNumber;
        CardView statusCardView;
        Button seeReceiptBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            reservationID = itemView.findViewById(R.id.reservationID);
            name = itemView.findViewById(R.id.name);
            mobileno = itemView.findViewById(R.id.mobileNumber);
            reservationDate = itemView.findViewById(R.id.reservationDate);
            companyName = itemView.findViewById(R.id.companyName);
            venue = itemView.findViewById(R.id.venue);
            event = itemView.findViewById(R.id.event);
            numofPeople = itemView.findViewById(R.id.numofPeople);
            statusCardView = itemView.findViewById(R.id.pending_cardView);
            statusTxtView = itemView.findViewById(R.id.status);
            userID  = itemView.findViewById(R.id.userUID);
            dateOfReservation = itemView.findViewById(R.id.dateofReservation_TxtView);
            timeOfReservation = itemView.findViewById(R.id.timeofReservation_TxtView);
            seeReceiptBtn = itemView.findViewById(R.id.seeReceipt_Btn);
            gCashNumber = itemView.findViewById(R.id.gCashNumber_textView);
        }
    }
}
