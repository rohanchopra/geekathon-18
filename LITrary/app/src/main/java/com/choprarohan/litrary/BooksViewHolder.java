package com.choprarohan.litrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rohan Chopra on 1/6/18.
 */

public class BooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    private List<Books> items;
    private Context context;

    public BooksViewHolder(View itemView, List<Books> items, Context context) {
        super(itemView);
        this.context = context;
        this.items = items;
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
    }

    public ImageView getBookImage() {
        return bookImage;
    }

    public void setBookImage(ImageView bookImage) {
        this.bookImage = bookImage;
    }

    @BindView(R.id.bookImage)
    ImageView bookImage;


    @Override
    public void onClick(View view) {

        context.startActivity(new Intent(context, DetailActivity.class));

    }
}
