package com.example.tourismapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourismHorizontalAdapter extends RecyclerView.Adapter<TourismHorizontalAdapter.ViewHolder> {
    private List<TourismHorizontal> tourism;
    private Context context;
    public TourismHorizontalAdapter(List<TourismHorizontal> tourism, Context context){
        this.tourism = tourism;
        this.context = context;
    }

    @NonNull
    @Override
    public TourismHorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.tourism_horizontal, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TourismHorizontalAdapter.ViewHolder holder, int position) {
        holder.imageViewHorizontal.setImageResource(tourism.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return tourism.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewHorizontal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHorizontal = itemView.findViewById(R.id.imageViewHorizontal);
        }
    }
}
