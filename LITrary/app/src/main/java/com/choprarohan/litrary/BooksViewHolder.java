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

        int pos = getLayoutPosition();
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra("name",items.get(pos).getTitle());
        intent.putExtra("author",items.get(pos).getAuthor());
        intent.putExtra("credits",items.get(pos).getCredits());
        intent.putExtra("rating",items.get(pos).getRating());
        intent.putExtra("isbn",items.get(pos).getIsbn());
        intent.putExtra("url",items.get(pos).getImgurl());
        intent.putExtra("desc",items.get(pos).getDesc());

        context.startActivity(intent);
    }
}
