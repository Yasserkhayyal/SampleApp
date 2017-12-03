package com.example.android.sampleapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sampleapp.R;
import com.example.android.sampleapp.models.Item;
import com.example.android.sampleapp.views.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Yasser on 12/2/2017.
 */

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItemViewHolder> {
    List<Item> items;
    Context mContext;

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_layout
                ,parent,false);
        return new MenuItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        Picasso.with(mContext).load(items.get(position).getPhotoUrl()).into(holder.itemImage);
        holder.itemTitle.setText(items.get(position).getName());
        holder.itemDesc.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.item_iv)
        ImageView itemImage;

        @BindView(R.id.item_title)
        TextView itemTitle;

        @BindView(R.id.item_description)
        AppCompatTextView itemDesc;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {
            Intent startDetailsActivity = new Intent(mContext, DetailsActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext,
                            this.itemImage,
                            ViewCompat.getTransitionName(this.itemImage));
            startDetailsActivity.putExtra(mContext.getString(R.string.image_url),
                    items.get(this.getAdapterPosition()).getPhotoUrl());
            startDetailsActivity.putExtra(mContext.getString(R.string.title),
                    items.get(this.getAdapterPosition()).getName());
            startDetailsActivity.putExtra(mContext.getString(R.string.description),
                    items.get(this.getAdapterPosition())
                    .getDescription());
            mContext.startActivity(startDetailsActivity,options.toBundle());
        }
    }

    public void setItems(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }
}
