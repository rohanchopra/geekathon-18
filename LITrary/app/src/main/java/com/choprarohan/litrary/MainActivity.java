package com.choprarohan.litrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @BindView(R.id.button_add)
    AppCompatImageButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookList);

        items = new ArrayList<>();

        DatabaseReference dbBooks = FirebaseDatabase.getInstance().getReference("books");


        Query query = dbBooks.orderByChild("rating");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear();

                for(DataSnapshot bookSnapshot: dataSnapshot.getChildren()){
                    Books book = bookSnapshot.getValue(Books.class);



                    items.add(book);
                }


                // set up the RecyclerView
                int numberOfColumns = 3;
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, numberOfColumns));
                adapter = new BooksAdapter(items);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







    }

}
