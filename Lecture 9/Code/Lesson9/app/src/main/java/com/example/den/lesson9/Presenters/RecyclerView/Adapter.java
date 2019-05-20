package com.example.den.lesson9.Presenters.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson9.Interfaces.PhotoItem;
import com.example.den.lesson9.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderRecyclerView> {

    PhotoItem[] photoItems;

    public Adapter(PhotoItem[] photoItems) {
        this.photoItems = photoItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolderRecyclerView onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_item_img, parent, false);
        return new ViewHolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecyclerView holder, int position) {

        PhotoItem photoItem = this.photoItems[position];

        holder.textViewAuthor.setText(photoItem.getAuthorName());
        Picasso.get().load(photoItem.getImgUrl()).placeholder(R.drawable.placeholder).into(holder.imageViewPhoto);
    }

    @Override
    public int getItemCount() {
        return this.photoItems.length;
    }


    public static class ViewHolderRecyclerView extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        public ImageView imageViewPhoto;
        @BindView(R.id.textViewAuthor)
        public TextView textViewAuthor;
        @BindView(R.id.imageFavorite)
        public ImageView imageFavorite;

        ViewHolderRecyclerView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
