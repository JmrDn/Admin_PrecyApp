package com.example.admin_precyapp.adapater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.admin_precyapp.PendingReservationList;
import com.example.admin_precyapp.R;
import com.example.admin_precyapp.model.UserPendingListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;

public class UserPendingListAdapter extends RecyclerView.Adapter<UserPendingListAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserPendingListModel> list;


    public UserPendingListAdapter(Context context, ArrayList<UserPendingListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserPendingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_pending_reservation, parent, false);


//        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return  new UserPendingListAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserPendingListAdapter.MyViewHolder holder, int position) {

        UserPendingListModel userPendingListModel = list.get(position);
        holder.reservationDate.setText(userPendingListModel.getuser_ReservationDate());
        holder.name.setText(userPendingListModel.getuser_Name());
        holder.mobileno.setText(userPendingListModel.getuser_MobileNum());
        holder.reservationID.setText(userPendingListModel.getuserReservationID());
        holder.event.setText(userPendingListModel.getuser_Event());
        holder.numofPeople.setText(userPendingListModel.getuser_NumofPeople());
        holder.companyName.setText(userPendingListModel.getCompanyName());
        holder.venue.setText(userPendingListModel.getVenue());

        if(userPendingListModel.getuser_Status().equals(true)){
            holder.statusCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.approvedbg));
            holder.statusTxtView.setTextColor(ContextCompat.getColor(context, R.color.fontcolorapproved));
            holder.statusTxtView.setText("Approved");
        }

      if ( userPendingListModel.getuser_Status().equals(false)){
          holder.statusCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.pendingbg));
          holder.statusTxtView.setTextColor(ContextCompat.getColor(context, R.color.fontcolorpending));
          holder.statusTxtView.setText("Pending");
      }

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("PendingReservation"). document(holder.userReservationID)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot.exists()){
                            MyViewHolder.reservationID_Appr = (documentSnapshot.getString("Reservation ID"));
                            MyViewHolder.name_Appr = (documentSnapshot.getString("Name"));
                            MyViewHolder.mobileno_Appr = (documentSnapshot.getString("Phone Number"));
                            MyViewHolder.event_Appr = (documentSnapshot.getString("Event"));
                            MyViewHolder.reservationDate_Appr = (documentSnapshot.getString("ReservationDate"));
                            MyViewHolder.numOfPeople_Appr = (documentSnapshot.getString("Number of People"));
                            MyViewHolder.userUID_Appr = (documentSnapshot.getString("User ID"));
                            MyViewHolder.timeOfReservation_Appr = (documentSnapshot.getString("Time of Reservation"));
                            MyViewHolder.dateOfReservation_Appr = (documentSnapshot.getString("Date of Reservation"));
                            MyViewHolder.reservationVenue = (documentSnapshot.getString("Venue"));
                            MyViewHolder.clientCompanyName = (documentSnapshot.getString("CompanyName"));


                        } else{
                            Log.d("TAG", "no such document");
                        }
                    }
                });

        holder.statusCardView.setOnClickListener(v -> {

            Toast.makeText(context.getApplicationContext(), "Reservation Approved", Toast.LENGTH_LONG).show();
            HashMap<String, Object> ReservationDetails = new HashMap<>();
            ReservationDetails.put("Reservation ID", MyViewHolder.reservationID_Appr);
            ReservationDetails.put("Name", MyViewHolder.name_Appr);
            ReservationDetails.put("Phone Number", MyViewHolder.mobileno_Appr);
            ReservationDetails.put("ReservationDate", MyViewHolder.reservationDate_Appr);
            ReservationDetails.put("Event", MyViewHolder.event_Appr);
            ReservationDetails.put("Number of People", MyViewHolder.numOfPeople_Appr);
            ReservationDetails.put("User ID", MyViewHolder.userUID_Appr);
            ReservationDetails.put("Time of Reservation", MyViewHolder.timeOfReservation_Appr);
            ReservationDetails.put("Date of Reservation", MyViewHolder.dateOfReservation_Appr);
            ReservationDetails.put("CompanyName", MyViewHolder.clientCompanyName);
            ReservationDetails.put("Venue", MyViewHolder.reservationVenue);

            firestore.collection("ApprovedReservationListData").document(holder.userReservationID)
                    .set(ReservationDetails)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("TAG", "SUCCESS DATA UPLOAD");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "data sending" + e);
                        }
                    });

            firestore.collection("Users").document(holder.userUID)
                    .collection("My Reservation")
                    .document(holder.userReservationID)
                    .update("Status", true);

            firestore.collection("ClientReservationDetails").document(holder.userReservationID)
                    .update("Status", true);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView reservationID, name, mobileno, reservationDate, event, numofPeople, statusTxtView, companyName, venue;
        CardView statusCardView;

        String userUID, userReservationID;
       public   static String reservationID_Appr;
       public static String name_Appr;
       public static String mobileno_Appr;
       public static String reservationDate_Appr;
       public static String event_Appr;
       public static String numOfPeople_Appr;
       public boolean status_Appr;
       public static String userUID_Appr;
       public static String timeOfReservation_Appr;
       public static String dateOfReservation_Appr;
       public static String reservationVenue;
       public static String clientCompanyName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            reservationID = itemView.findViewById(R.id.user_ReservationID);
            name = itemView.findViewById(R.id.User_name);
            mobileno = itemView.findViewById(R.id.User_mobileNumber);
            reservationDate = itemView.findViewById(R.id.User_reservationDate);
            event = itemView.findViewById(R.id.User_event);
            numofPeople = itemView.findViewById(R.id.User_numofPeople);
            companyName = itemView.findViewById(R.id.companyName);
            venue = itemView.findViewById(R.id.venue);
            statusCardView = itemView.findViewById(R.id.userpending_cardView);
            statusTxtView = itemView.findViewById(R.id.User_status);
            userUID = PendingReservationList.userUID;
            userReservationID = PendingReservationList.userReservationID;

        }
    }
}
