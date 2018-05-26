package com.example.minseop.midasit.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Account;
import com.example.minseop.midasit.ui.admin.DetailUserManagementActivity;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserRecyclerViewHolder> {

    private final List<Account> accounts;

    public UserRecyclerAdapter(@NonNull List<Account> accounts) {
        this.accounts = accounts;
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
        final Account account = accounts.get(position);

        holder.username.setText(account.getUsername());
        holder.employeeNumber.setText(account.getEmployeeNumber());
        holder.id = account.getId();
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    static class UserRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView employeeNumber;
        int id;

        public UserRecyclerViewHolder(final View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.card_user_management_username);
            employeeNumber = itemView.findViewById(R.id.card_user_management_employee_number);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailUserManagementActivity.class);
                    intent.putExtra("username",username.getText().toString());
                    intent.putExtra("employeenumber",employeeNumber.getText().toString());
                    intent.putExtra("id",id);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
