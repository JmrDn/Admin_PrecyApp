package com.example.admin_precyapp.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_precyapp.R;
import com.example.admin_precyapp.model.PendingListModel;

import java.util.ArrayList;

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

//        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView reservationID, name, mobileno, reservationDate, event, numofPeople;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            reservationID = itemView.findViewById(R.id.reservationID);
            name = itemView.findViewById(R.id.name);
            mobileno = itemView.findViewById(R.id.mobileNumber);
            reservationDate = itemView.findViewById(R.id.reservationDate);
            event = itemView.findViewById(R.id.event);
            numofPeople = itemView.findViewById(R.id.numofPeople);





        }
    }
}
