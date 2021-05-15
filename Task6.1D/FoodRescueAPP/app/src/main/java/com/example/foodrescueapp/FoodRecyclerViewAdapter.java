package com.example.foodrescueapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrescueapp.model.Food;

import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;

    public FoodRecyclerViewAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.food_details, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.foodImageView.setImageDrawable(transformToDrawable(foodList.get(position).getFoodImage()));
        holder.foodTitle.setText(foodList.get(position).getFoodTitle());
        holder.foodTime.setText(foodList.get(position).getFoodTime());
        holder.foodQuantity.setText(foodList.get(position).getFoodQuantity());
        holder.foodLocation.setText(foodList.get(position).getFoodLocation());
        holder.itemView.setTag(position);
        holder.shareImageButton.setTag(position);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView foodImageView;
        TextView foodTitle, foodTime, foodQuantity, foodLocation;
        ImageButton shareImageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodTime = itemView.findViewById(R.id.foodTime);
            foodQuantity = itemView.findViewById(R.id.foodQuantity);
            foodLocation = itemView.findViewById(R.id.foodLocation);
            shareImageButton = itemView.findViewById(R.id.shareImageButton);
            itemView.setOnClickListener(this);
            shareImageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (mOnItemClickListener != null) {
                switch (v.getId()){
                    case R.id.shareImageButton:
                        mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
                        break;
                    default:
                        mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                        break;
                }
            }
        }
    }

    public enum ViewName {
        ITEM,
        PRACTISE
    }

    public interface OnItemClickListener  {
        void onItemClick(View v, ViewName viewName, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }

    public Drawable transformToDrawable(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length, null);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        Drawable drawable = bitmapDrawable;
        return drawable;
    }
}
