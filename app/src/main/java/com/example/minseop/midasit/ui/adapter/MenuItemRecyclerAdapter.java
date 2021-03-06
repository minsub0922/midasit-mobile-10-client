package com.example.minseop.midasit.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.MenuModel;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuItemRecyclerAdapter extends RecyclerView.Adapter<MenuItemRecyclerAdapter.MenuItemViewHolder> {

    List<MenuModel> items;
    Context context;

    public MenuItemRecyclerAdapter(Context context, List<MenuModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menuitem_cardview, parent, false);
        MenuItemViewHolder holder = new MenuItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        final MenuModel item = items.get(position);

        if (StringUtils.isNotEmpty(item.getImage())) {
            Glide.with(context).load(Base64.decode(item.getImage(), Base64.DEFAULT)).into(holder.image);
        }
        holder.title.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price;
        CardView cardview;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.menuitem_card_image);
            title = (TextView) itemView.findViewById(R.id.menuitem_card_name);
            price = itemView.findViewById(R.id.menuitem_card_price);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
