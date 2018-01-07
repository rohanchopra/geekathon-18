package com.choprarohan.litrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohan Chopra on 1/6/18.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksViewHolder> {

    Context context;
    private List<Books> items;
    private LayoutInflater mInflater;


    // data is passed into the constructor
    BooksAdapter(ArrayList<Books> items) {
        this.items = items;
    }


    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        BooksViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);

        View listingView = inflater.inflate(R.layout.card_book, parent, false);
        viewHolder = new BooksViewHolder(listingView,items,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {
        BooksViewHolder viewHolder = holder;
        configureBookViewHolder(holder, position);
    }


    private void configureBookViewHolder(final BooksViewHolder viewHolder, int position){

        try {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(viewHolder.getBookImage().getContext())
                    .load(items.get(position).getImgurl())
                    .apply(options)
                    .into(viewHolder.getBookImage());
            //viewHolder.getBookImage().setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
            //viewHolder.getBookImage().setAlpha(0.7f);


            //LightingColorFilter lcf = new LightingColorFilter( Color.GRAY, Color.GRAY );

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}

