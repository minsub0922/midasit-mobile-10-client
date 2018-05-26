package com.example.minseop.midasit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minseop.midasit.Item.MenuItem;
import com.example.minseop.midasit.R;

import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.PlaceViewHolder> {
    private Context mContext;
    List<com.example.minseop.midasit.Item.MenuItem> items;

    public RecyclerGridAdapter(Context mContext, List<com.example.minseop.midasit.Item.MenuItem> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menuitem_gridview,
                parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        final com.example.minseop.midasit.Item.MenuItem item =  items.get(position);
        Glide.with(mContext).load(item.getImgResource()).into(holder.img);
        holder.txt_title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_title;
        TextView txt_price;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_price = itemView.findViewById(R.id.txt_price);
        }
    }
}
