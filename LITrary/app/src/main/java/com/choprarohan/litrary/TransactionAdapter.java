package com.choprarohan.litrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohan Chopra on 1/7/18.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder>{

    Context context;
    private List<Transaction> items;
    private LayoutInflater mInflater;


    // data is passed into the constructor
    TransactionAdapter(ArrayList<Transaction> items) {
        this.items = items;
    }




    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        TransactionViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);

        View listingView = inflater.inflate(R.layout.card_txn, parent, false);
        viewHolder = new TransactionViewHolder(listingView,items,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        TransactionViewHolder viewHolder = holder;
        configureTransactionViewHolder(holder, position);
    }

    private void configureTransactionViewHolder(final TransactionViewHolder viewHolder, int position){

        try {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(viewHolder.getBookImage().getContext())
                    .load(items.get(position).getImgLink())
                    .apply(options)
                    .into(viewHolder.getBookImage());
            //viewHolder.getBookImage().setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
            //viewHolder.getBookImage().setAlpha(0.7f);


            //LightingColorFilter lcf = new LightingColorFilter( Color.GRAY, Color.GRAY );

        }catch (Exception e){
            e.printStackTrace();
        }

        viewHolder.getNameText().setText(items.get(position).getTitle());
        viewHolder.getDaysText().setText(items.get(position).getDays());
        if(items.get(position).getFlag() == 1){
            viewHolder.getStatusText().setText("Transaction Complete");
            viewHolder.getDaysText().setVisibility(View.GONE);
            viewHolder.getStatusText().setTextColor(context.getResources().getColor(R.color.green));
        }
        else {
            viewHolder.getDaysText().setVisibility(View.VISIBLE);
            viewHolder.getStatusText().setText("Transaction Pending");
            viewHolder.getStatusText().setTextColor(context.getResources().getColor(R.color.orange));
        }


    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
