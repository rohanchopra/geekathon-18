package com.choprarohan.litrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    @BindView(R.id.name)
    TextView nameText;
    @BindView(R.id.status)
    TextView statusText;
    @BindView(R.id.days)
    TextView daysText;

    public TextView getNameText() {
        return nameText;
    }

    public void setNameText(TextView nameText) {
        this.nameText = nameText;
    }

    public TextView getStatusText() {
        return statusText;
    }

    public void setStatusText(TextView statusText) {
        this.statusText = statusText;
    }

    public TextView getDaysText() {
        return daysText;
    }

    public void setDaysText(TextView daysText) {
        this.daysText = daysText;
    }
}
