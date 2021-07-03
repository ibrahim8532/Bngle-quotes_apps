package com.ib.quotes.categories;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ib.quotes.Model;
import com.ib.quotes.R;

import java.util.ArrayList;
import java.util.List;

public class Philosophy extends AppCompatActivity implements View.OnClickListener{
    TextView count_text, quote_text;
    CardView back_btn, copy_btn, share_btn, next_btn;

    List<String> quotes_list;
    DatabaseReference databaseReference;
    Model myQuote;
    int position =0;

    private AdView mAdView;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceStart) {
        super.onCreate(savedInstanceStart);
        setContentView(R.layout.custom_layout);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("দর্শন");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        count_text = findViewById(R.id.countTEXT);
        quote_text = findViewById(R.id.quotesTEXT);
        back_btn = findViewById(R.id.backBtn);
        copy_btn = findViewById(R.id.copyBtn);
        share_btn = findViewById(R.id.shareBtn);
        next_btn = findViewById(R.id.nextBtn);


        back_btn.setOnClickListener(this);
        copy_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);


        databaseReference = FirebaseDatabase.getInstance().getReference("Philosophy");
        myQuote = new Model();
        quotes_list = new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    myQuote = dataSnapshot1.getValue(Model.class);
                    if (myQuote !=null){
                        quotes_list.add(myQuote.getTitle());
                    }
                }

                quote_text.setText(quotes_list.get(position));
                count_text.setText(position+"/"+ quotes_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.backBtn:
                back();
                break;

            case R.id.copyBtn:
                copy();
                break;
            case R.id.shareBtn:
                share();
                break;
            case R.id.nextBtn:
                next();
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void back() {
        if (position > 0) {
            position = (position - 1) % quotes_list.size();
            quote_text.setText(quotes_list.get(position));
            count_text.setText(position + "/" + quotes_list.size());
        }
        Toast.makeText(getApplicationContext(), "Previous Quote", Toast.LENGTH_SHORT).show();
    }

    private void copy() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("TextView", quote_text.getText());

        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
        }
        Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, quote_text.getText());
        startActivity(Intent.createChooser(intent, "Share Via"));

        Toast.makeText(getApplicationContext(), "Share Quote", Toast.LENGTH_SHORT).show();
    }

    private void next() {
        position = (position + 1) % quotes_list.size();
        quote_text.setText(quotes_list.get(position));
        count_text.setText(position + "/" + quotes_list.size());

        Toast.makeText(getApplicationContext(), "Next Quote", Toast.LENGTH_SHORT).show();
    }
}