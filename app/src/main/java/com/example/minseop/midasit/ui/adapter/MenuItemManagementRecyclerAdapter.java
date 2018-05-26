package com.example.minseop.midasit.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.MenuModel;

import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuItemManagementRecyclerAdapter extends RecyclerView.Adapter<MenuItemManagementRecyclerAdapter.MenuItemViewHolder> {

    List<MenuModel> items;
    Context context;

    public MenuItemManagementRecyclerAdapter(Context context, List<MenuModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menuitem_management, parent, false);
        MenuItemViewHolder holder = new MenuItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        final MenuModel item = items.get(position);

//        Glide.with(context).load(item.getImgResource()).into(holder.image);
        holder.title.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, price;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_menuitem_child_management);
            title = (TextView) itemView.findViewById(R.id.txt_title_menuitem_child_management);
            price = itemView.findViewById(R.id.txt_price_menuitem_child_management);
        }
    }
}
