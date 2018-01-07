package com.choprarohan.litrary;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rohan Chopra on 1/6/18.
 */

public class DetailActivity extends AppCompatActivity {

    String title, imgLink, descp, rating, author, credits, isbn;

    @BindView(R.id.title)
    TextView titleText;
    @BindView(R.id.author)
    TextView authorText;
    @BindView(R.id.rating)
    TextView ratingText;
    @BindView(R.id.credits)
    TextView creditsText;
    @BindView(R.id.desc)
    TextView descText;
    @BindView(R.id.cover)
    ImageView imgView;
    @BindView(R.id.borrow_button)
    AppCompatButton borrowButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                title = extras.getString("name");
                imgLink = extras.getString("url");
                descp = extras.getString("desc");
                rating = extras.getString("rating");
                author = extras.getString("author");
                credits = extras.getString("credits");
                isbn = extras.getString("isbn");

            }
        }

        titleText.setText(title);
        authorText.setText("by "+author);
        creditsText.setText(credits+" credits");
        descText.setText(descp);
        ratingText.setText(rating);

        try {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(DetailActivity.this)
                    .load(imgLink)
                    .apply(options)
                    .into(imgView);
            //viewHolder.getBookImage().setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
            //viewHolder.getBookImage().setAlpha(0.7f);


            //LightingColorFilter lcf = new LightingColorFilter( Color.GRAY, Color.GRAY );

        }catch (Exception e){
            e.printStackTrace();
        }


        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {

                    URL url = new URL("http://192.168.43.37:5000/txion");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");


                    String input = "{\"from\": \"1\", \"to\":\"2\", \"amount\": "+credits+"}";
                    OutputStream os = conn.getOutputStream();
                    os.write(input.getBytes());
                    os.flush();



                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    conn.disconnect();

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }



                Toast.makeText(DetailActivity.this, "Transaction Successful!",
                        Toast.LENGTH_SHORT).show();


            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }
}
