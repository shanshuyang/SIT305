package com.example.tourismapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourismVerticalAdapter extends RecyclerView.Adapter<TourismVerticalAdapter.ViewHolder> {
    private List<TourismVertical> tourism;
    private Context context;
    private OnPlaceClickListener listener;
    public TourismVerticalAdapter(List<TourismVertical> tourism, Context context, OnPlaceClickListener listener){
        this.tourism = tourism;
        this.context = context;
        this.listener = listener;
    }

    public interface OnPlaceClickListener{
        void onItemClick (int position);
    }

    @NonNull
    @Override
    public TourismVerticalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.tourism_vertical, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TourismVerticalAdapter.ViewHolder holder, int position) {
        holder.imageViewVertical.setImageResource(tourism.get(position).getImage());
        holder.placeNameTextView.setText(tourism.get(position).getName());
        holder.placeLocationTextView.setText(tourism.get(position).getLocation());
        holder.placeEvaluateTextView.setText(tourism.get(position).getEvaluate());
    }

    @Override
    public int getItemCount() {
        return tourism.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewVertical;
        TextView placeNameTextView, placeLocationTextView,placeEvaluateTextView;
        private OnPlaceClickListener onPlaceClickListener;
        public ViewHolder(@NonNull View itemView, OnPlaceClickListener listener) {
            super(itemView);
            imageViewVertical = itemView.findViewById(R.id.imageViewVertical);
            placeNameTextView = itemView.findViewById(R.id.placeNameTextView);
            placeLocationTextView = itemView.findViewById(R.id.placeLocationTextView);
            placeEvaluateTextView = itemView.findViewById(R.id.placeEvaluateTextView);
            this.onPlaceClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPlaceClickListener.onItemClick(getAdapterPosition());
        }
    }
}
