package com.example.foodrescueapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrescueapp.model.Food;

import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;

    public CartRecyclerViewAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_details, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cartTitleTextView.setText(foodList.get(position).getFoodTitle());
        holder.cartIdTextView.setText((position + 1) + "");
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cartTitleTextView, cartIdTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartTitleTextView = itemView.findViewById(R.id.cartTitleTextView);
            cartIdTextView = itemView.findViewById(R.id.cartIdTextView);
        }
    }
}
