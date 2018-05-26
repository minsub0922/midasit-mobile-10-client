package com.example.minseop.midasit.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.user.User;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserRecyclerViewHolder> {

    private final List<User> users;

    public UserRecyclerAdapter(@NonNull List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_user_management, parent, false);
        return new UserRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewHolder holder, int position) {
        final User user = users.get(position);

        holder.username.setText(user.getUsername());
        holder.employeeNumber.setText(user.getEmployeeNumber());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView employeeNumber;

        public UserRecyclerViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.card_user_management_username);
            employeeNumber = itemView.findViewById(R.id.card_user_management_employee_number);
        }
    }

}
