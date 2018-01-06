package com.choprarohan.litrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Rohan Chopra on 1/7/18.
 */

public class TransactionActivity extends AppCompatActivity {

    TransactionAdapter adapter;
    ArrayList<Transaction> items = new ArrayList<>();
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent.setClass(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_txn:

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
        navigation.setSelectedItemId(R.id.navigation_txn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigation.setSelectedItemId(R.id.navigation_txn);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Transaction b = new Transaction();
        b.setImgLink("https://the-digital-reader.com/wp-content/uploads/2017/11/35.png");
        items.add(b);
        b = new Transaction();
        b.setImgLink("https://upload.wikimedia.org/wikipedia/en/a/a7/The_Whistler_book_cover.png");
        items.add(b);
        b = new Transaction();
        b.setImgLink("http://78.media.tumblr.com/61b3cf732767b2ce259184ee829c7d96/tumblr_ob72x0hCBD1qfv89lo1_r2_500.png");
        items.add(b);
        b = new Transaction();
        b.setImgLink("http://78.media.tumblr.com/9c89cdedf6317aa67b3a3f04a3589a03/tumblr_inline_p03epxt2Ij1qcipmn_540.png");
        items.add(b);
        b = new Transaction();
        b.setImgLink("https://upload.wikimedia.org/wikipedia/en/2/20/Scarlet_%28Official_Book_Cover%29_by_Marissa_Meyer.png");
        items.add(b);




        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.txnList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(items);
        recyclerView.setAdapter(adapter);


    }
}
