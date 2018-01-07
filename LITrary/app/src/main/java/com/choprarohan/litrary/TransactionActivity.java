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
        b.setTitle("Angels and Demons");
        b.setDays("0");
        b.setFlag(1);
        b.setImgLink("http://images.amazon.com/images/P/074349346X.01.LZZZZZZZ.jpg");
        items.add(b);

        b = new Transaction();
        b.setTitle("The Da Vinci Code");
        b.setDays("14");
        b.setFlag(0);
        b.setImgLink("http://images.amazon.com/images/P/0385504217.01.LZZZZZZZ.jpg");
        items.add(b);




        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.txnList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(items);
        recyclerView.setAdapter(adapter);


    }
}
