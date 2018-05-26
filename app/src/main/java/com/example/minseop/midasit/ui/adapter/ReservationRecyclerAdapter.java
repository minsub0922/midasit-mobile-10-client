package com.example.minseop.midasit.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Order;

import java.util.List;

public class ReservationRecyclerAdapter extends RecyclerView.Adapter<ReservationRecyclerAdapter.ReservationRecyclerViewHolder> {

    private final List<Order> reservations;

    public ReservationRecyclerAdapter(@NonNull List<Order> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ReservationRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_order, parent, false);
        return new ReservationRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationRecyclerViewHolder holder, int position) {
        final Order order = reservations.get(position);

        holder.username.setText(String.format(holder.itemView.getResources().getString(R.string.fmt_order_username), order.getUsername()));
        holder.detail.setText(String.format(holder.itemView.getResources().getString(R.string.fmt_order_detail), order.getName(), order.getCount()));
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    static class ReservationRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView detail;

        public ReservationRecyclerViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.card_order_username);
            detail = itemView.findViewById(R.id.card_order_detail);
        }
    }

}
