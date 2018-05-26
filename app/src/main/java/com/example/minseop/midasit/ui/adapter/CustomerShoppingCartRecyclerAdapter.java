package com.example.minseop.midasit.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.ShoppingCartItem;

import java.util.List;

public class CustomerShoppingCartRecyclerAdapter extends RecyclerView.Adapter<CustomerShoppingCartRecyclerAdapter.CustomerShoppingCartViewHolder> {

    private final List<ShoppingCartItem> shoppingCart;

    public CustomerShoppingCartRecyclerAdapter(@NonNull List<ShoppingCartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @NonNull
    @Override
    public CustomerShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.card_customer_shopping_cart, parent, false);
        return new CustomerShoppingCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerShoppingCartViewHolder holder, int position) {
        final ShoppingCartItem shoppingCartItem = shoppingCart.get(position);
        holder.menuName.setText(shoppingCartItem.getName());
        holder.menuImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_launcher_foreground));
    }

    @Override
    public int getItemCount() {
        return shoppingCart.size();
    }

    static class CustomerShoppingCartViewHolder extends RecyclerView.ViewHolder {

        private ImageView menuImage;
        private TextView menuName;

        public CustomerShoppingCartViewHolder(View itemView) {
            super(itemView);

            menuImage = itemView.findViewById(R.id.card_customer_shopping_card_menu_image);
            menuName = itemView.findViewById(R.id.card_customer_shopping_card_menu_name);
        }
    }

}
