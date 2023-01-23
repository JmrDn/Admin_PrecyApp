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
import com.example.admin_precyapp.model.FoodPackageModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodPackageAdapter extends RecyclerView.Adapter<FoodPackageAdapter.MyViewHolder> {

    Context context;
    ArrayList<FoodPackageModel> list;

    public FoodPackageAdapter(Context context, ArrayList<FoodPackageModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public FoodPackageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.foodpackage_list, parent, false);

        return  new FoodPackageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodPackageAdapter.MyViewHolder holder, int position) {

        FoodPackageModel foodPackageModel = list.get(position);
        holder.packageName.setText(foodPackageModel.getPackageName());
        holder.price.setText(foodPackageModel.getPrice());
        holder.foodNo1.setText(foodPackageModel.getFoodNo1());
        holder.foodNo2.setText(foodPackageModel.getFoodNo2());
        holder.foodNo3.setText(foodPackageModel.getFoodNo3());
        holder.foodNo4.setText(foodPackageModel.getFoodNo4());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView packageName, foodNo1, foodNo2, foodNo3, foodNo4, price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            packageName = itemView.findViewById(R.id.packageName_TextView);
            price = itemView.findViewById(R.id.price_TextView);
            foodNo1 = itemView.findViewById(R.id.foodNo1_TextView);
            foodNo2 = itemView.findViewById(R.id.foodNo2_TextView);
            foodNo3 = itemView.findViewById(R.id.foodNo3_TextView);
            foodNo4 = itemView.findViewById(R.id.foodNo4_TextView);

        }
    }
}
