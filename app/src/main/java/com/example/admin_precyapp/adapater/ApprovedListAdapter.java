package com.example.admin_precyapp.adapater;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_precyapp.PendingReservationList;
import com.example.admin_precyapp.R;
import com.example.admin_precyapp.model.ApprovedListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class ApprovedListAdapter extends RecyclerView.Adapter<ApprovedListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ApprovedListModel> list;
    private SelectListener listener;

    public ApprovedListAdapter(Context context, ArrayList<ApprovedListModel> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ApprovedListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.approved_reservation_list, parent, false);

        return  new ApprovedListAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedListAdapter.MyViewHolder holder, int position) {

        ApprovedListModel approvedListModel = list.get(position);
        holder.reservationDate.setText(approvedListModel.getReservationDate());
        holder.name.setText(approvedListModel.getName());
        holder.mobileno.setText(approvedListModel.getMobilenum());
        holder.reservationID.setText(approvedListModel.getReservationID());
        holder.event.setText(approvedListModel.getEvent());
        holder.numofPeople.setText(approvedListModel.getNumofPeople());
        holder.userID.setText(approvedListModel.getUserID());
        holder.timeOfReservation.setText(approvedListModel.getTimeOfReservation());
        holder.dateOfReservation.setText(approvedListModel.getDateOfReservation());
        holder.companyName.setText(approvedListModel.getCompanyName());
        holder.venue.setText(approvedListModel.getVenue());

        if(approvedListModel.getStatus().equals(true)){
            holder.cardView.setCardBackgroundColor(Color.RED);
        }else if(approvedListModel.getStatus().equals(false)){
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reservationID, name, mobileno, reservationDate, event, companyName, venue,
                numofPeople, userID, dateOfReservation, timeOfReservation;

        CardView cardView;


        public MyViewHolder(@NonNull View itemView, SelectListener selectListener) {
            super(itemView);

            reservationID = itemView.findViewById(R.id.reservationID);
            name = itemView.findViewById(R.id.name);
            mobileno = itemView.findViewById(R.id.mobileNumber);
            reservationDate = itemView.findViewById(R.id.reservationDate);
            event = itemView.findViewById(R.id.event);
            numofPeople = itemView.findViewById(R.id.numofPeople);
            userID  = itemView.findViewById(R.id.userUID);
            dateOfReservation = itemView.findViewById(R.id.dateofReservation_TxtView);
            timeOfReservation = itemView.findViewById(R.id.timeofReservation_TxtView);
            companyName = itemView.findViewById(R.id.companyName);
            venue = itemView.findViewById(R.id.venue);
            cardView = itemView.findViewById(R.id.approved_cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectListener != null ){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            selectListener.onItemClicked(pos);
                        }

                    }
                }
            });

        }
    }
}
