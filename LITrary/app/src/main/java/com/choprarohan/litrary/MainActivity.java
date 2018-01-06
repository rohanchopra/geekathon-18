package com.choprarohan.litrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BooksAdapter adapter;
    ArrayList<Books> items = new ArrayList<>();
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_txn:
                    intent.setClass(getBaseContext(), TransactionActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_profile:
                    intent.setClass(getBaseContext(), ProfileActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Books b = new Books();
        b.setImgLink("https://the-digital-reader.com/wp-content/uploads/2017/11/35.png");
        items.add(b);
        b = new Books();
        b.setImgLink("https://upload.wikimedia.org/wikipedia/en/a/a7/The_Whistler_book_cover.png");
        items.add(b);
        b = new Books();
        b.setImgLink("http://78.media.tumblr.com/61b3cf732767b2ce259184ee829c7d96/tumblr_ob72x0hCBD1qfv89lo1_r2_500.png");
        items.add(b);
        b = new Books();
        b.setImgLink("http://78.media.tumblr.com/9c89cdedf6317aa67b3a3f04a3589a03/tumblr_inline_p03epxt2Ij1qcipmn_540.png");
        items.add(b);
        b = new Books();
        b.setImgLink("https://upload.wikimedia.org/wikipedia/en/2/20/Scarlet_%28Official_Book_Cover%29_by_Marissa_Meyer.png");
        items.add(b);




        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookList);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new BooksAdapter(items);
        recyclerView.setAdapter(adapter);


    }

}
