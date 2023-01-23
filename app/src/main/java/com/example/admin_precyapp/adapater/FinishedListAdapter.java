package com.example.admin_precyapp.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.admin_precyapp.R;
import com.example.admin_precyapp.model.FinishedListModel;


import java.util.ArrayList;


public class FinishedListAdapter extends RecyclerView.Adapter<FinishedListAdapter.MyViewHolder> {

    Context context;
    ArrayList<FinishedListModel> list;


    public FinishedListAdapter(Context context, ArrayList<FinishedListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FinishedListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.finished_reservation_list, parent, false);

        return  new FinishedListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedListAdapter.MyViewHolder holder, int position) {

        FinishedListModel finishedListModel = list.get(position);
        holder.reservationDate.setText(finishedListModel.getReservationDate());
        holder.name.setText(finishedListModel.getName());
        holder.mobileno.setText(finishedListModel.getMobilenum());
        holder.reservationID.setText(finishedListModel.getReservationID());
        holder.event.setText(finishedListModel.getEvent());
        holder.numofPeople.setText(finishedListModel.getNumofPeople());
        holder.userID.setText(finishedListModel.getUserID());
        holder.timeOfReservation.setText(finishedListModel.getTimeOfReservation());
        holder.dateOfReservation.setText(finishedListModel.getDateOfReservation());
        holder.companyName.setText(finishedListModel.getCompanyName());
        holder.venue.setText(finishedListModel.getVenue());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView reservationID, name, mobileno, reservationDate, event, companyName, venue,
                numofPeople, userID, dateOfReservation, timeOfReservation;

        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
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



        }
    }
}
