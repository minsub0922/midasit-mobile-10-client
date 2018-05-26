package com.example.minseop.midasit.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.ReservationStatusModel;
import com.example.minseop.midasit.model.ShoppingCartItem;

import java.util.List;

public class CustomerProfileRecyclerAdpater extends RecyclerView.Adapter<CustomerProfileRecyclerAdpater.CustomerProfileViewHolder> {

    private final List<ReservationStatusModel> modelList;

    public CustomerProfileRecyclerAdpater(@NonNull List<ReservationStatusModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CustomerProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_customer_profile, parent, false);
        return new CustomerProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerProfileViewHolder holder, int position) {
        final ReservationStatusModel model = modelList.get(position);
        holder.menuName.setText(model.getName());
        holder.priceornum.setText(model.getPrice());
        holder.dateorstatus.setText(model.getDate());
        holder.menuImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_launcher_foreground));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class CustomerProfileViewHolder extends RecyclerView.ViewHolder {

        private ImageView menuImage;
        private TextView menuName;
        private TextView priceornum;
        private TextView dateorstatus;

        public CustomerProfileViewHolder(View itemView) {
            super(itemView);

            menuImage = itemView.findViewById(R.id.card_customer_profile_image);
            menuName = itemView.findViewById(R.id.card_customer_profile_title_txt);
            priceornum = itemView.findViewById(R.id.card_customer_profile_price_or_num_txt);
            dateorstatus = itemView.findViewById(R.id.card_customer_profile_date_or_state_txt);
        }
    }

}
