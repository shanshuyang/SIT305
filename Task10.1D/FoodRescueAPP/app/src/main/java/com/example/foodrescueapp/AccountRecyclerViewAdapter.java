package com.example.foodrescueapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrescueapp.model.User;

import java.util.List;

public class AccountRecyclerViewAdapter extends RecyclerView.Adapter<AccountRecyclerViewAdapter.ViewHolder> {
    private List<User> userList;
    private Context context;

    public AccountRecyclerViewAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.account_details, parent, false);
        return new AccountRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.accountName.setText(userList.get(position).getUsername());
        holder.accountPassword.setText(userList.get(position).getPassword());
        holder.accountEmail.setText(userList.get(position).getEmail());
        holder.accountPhone.setText(userList.get(position).getPhone());
        holder.accountAddress.setText(userList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView accountName, accountPassword, accountEmail, accountPhone, accountAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.accountName);
            accountPassword = itemView.findViewById(R.id.accountPassword);
            accountEmail = itemView.findViewById(R.id.accountEmail);
            accountPhone = itemView.findViewById(R.id.accountPhone);
            accountAddress = itemView.findViewById(R.id.accountAddress);
        }
    }
}
