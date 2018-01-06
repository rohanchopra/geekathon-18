package com.choprarohan.litrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rohan Chopra on 1/7/18.
 */

public class TransactionViewHolder extends RecyclerView.ViewHolder{

    private List<Transaction> items;
    private Context context;

    public TransactionViewHolder(View itemView, List<Transaction> items, Context context) {
        super(itemView);
        this.context = context;
        this.items = items;
        ButterKnife.bind(this,itemView);
    }

    public ImageView getBookImage() {
        return bookImage;
    }

    public void setBookImage(ImageView bookImage) {
        this.bookImage = bookImage;
    }

    @BindView(R.id.img)
    ImageView bookImage;

}
