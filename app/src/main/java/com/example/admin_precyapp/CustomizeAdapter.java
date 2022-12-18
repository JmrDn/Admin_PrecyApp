package com.example.admin_precyapp;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomizeAdapter extends RecyclerView.Adapter<CustomizeAdapter.MyViewHolder> {

    Context context;
    ArrayList<CustomizeModel> customizeModels;

    public CustomizeAdapter(Context context, ArrayList<CustomizeModel> customizeModels) {
        this.context = context;
        this.customizeModels = customizeModels;
    }


    @NonNull
    @Override
    public CustomizeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_customize, parent, false );
        return new CustomizeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomizeAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(customizeModels.get(position).getName()));

    }

    @Override
    public int getItemCount() {
        return customizeModels.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(View view){
            super(view);

            textView = view.findViewById(R.id.txtView);



        }

    }

}
