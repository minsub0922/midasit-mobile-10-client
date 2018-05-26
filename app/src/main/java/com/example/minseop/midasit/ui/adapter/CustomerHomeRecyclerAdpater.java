package com.example.minseop.midasit.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.HomeAdModel;
import com.example.minseop.midasit.model.ReservationStatusModel;

import java.util.List;

public class CustomerHomeRecyclerAdpater extends RecyclerView.Adapter<CustomerHomeRecyclerAdpater.CustomerHomeViewHolder> {

    private final List<HomeAdModel> modelList;

    public CustomerHomeRecyclerAdpater(@NonNull List<HomeAdModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CustomerHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_customer_home, parent, false);
        return new CustomerHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeViewHolder holder, int position) {
        final HomeAdModel model = modelList.get(position);
        holder.text.setText(model.getText());
        holder.menuImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.promotion));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class CustomerHomeViewHolder extends RecyclerView.ViewHolder {

        private ImageView menuImage;
        private TextView text;

        public CustomerHomeViewHolder(View itemView) {
            super(itemView);

            menuImage = itemView.findViewById(R.id.card_customer_home_image);
            text = itemView.findViewById(R.id.card_customer_home_txt);

        }
    }

}
