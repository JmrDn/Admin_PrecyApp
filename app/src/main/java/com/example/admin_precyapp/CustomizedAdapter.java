package com.example.admin_precyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomizedAdapter extends RecyclerView.Adapter<CustomizedAdapter.MyViewHolder> {

    Context context;
    ArrayList<CustomizedModel> customizedModels;


    public CustomizedAdapter(Context context, ArrayList<CustomizedModel> customizedModels) {
        this.context = context;
        this.customizedModels = customizedModels;
    }


    @NonNull
    @Override
    public CustomizedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_customized, parent, false);
        return new CustomizedAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomizedAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(customizedModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return customizedModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);

        }
    }
}
