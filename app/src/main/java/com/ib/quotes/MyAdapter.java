package com.ib.quotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ib.quotes.categories.Advice;
import com.ib.quotes.categories.Education;
import com.ib.quotes.categories.Feeling;
import com.ib.quotes.categories.Friend;
import com.ib.quotes.categories.FriendShip;
import com.ib.quotes.categories.Life;
import com.ib.quotes.categories.Lonekiness;
import com.ib.quotes.categories.Love;
import com.ib.quotes.categories.Mind;
import com.ib.quotes.categories.Motivation;
import com.ib.quotes.categories.Philosophy;
import com.ib.quotes.categories.Sorrow;
import com.ib.quotes.categories.Troble;
import com.ib.quotes.categories.Valobasa;
import com.ib.quotes.categories.Woman;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private Context c;
    private ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull

    @Override
    public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        // convert XML to OBJ
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_item, null);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.img.setImageResource(models.get(position).getImage());
        holder.modelTitle.setText(models.get(position).getTitle());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                switch (models.get(pos).getTitle()){
                    case "অনুপ্রেরণা":{
                        Intent motivation = new Intent(c, Motivation.class);
                        c.startActivity(motivation);
                        break;
                    }
                    case "প্রেম":{
                        Intent love = new Intent(c, Love.class);
                        c.startActivity(love);
                        break;
                    }
                    case "জীবন":{
                        Intent life = new Intent(c, Life.class);
                        c.startActivity(life);
                        break;
                    }
                    case "উপদেশ":{
                        Intent advice = new Intent(c, Advice.class);
                        c.startActivity(advice);
                        break;
                    }
                    case "একাকিত্ব":{
                        Intent in = new Intent(c, Lonekiness.class);
                        c.startActivity(in);
                        break;
                    }
                    case "কষ্ট":{
                        Intent in = new Intent(c, Troble.class);
                        c.startActivity(in);
                        break;
                    }
                    case "মন":{
                        Intent in = new Intent(c, Mind.class);
                        c.startActivity(in);
                        break;
                    }
                    case "দুঃখ":{
                        Intent in = new Intent(c, Sorrow.class);
                        c.startActivity(in);
                        break;
                    }
                    case "শিক্ষা":{
                        Intent in = new Intent(c, Education.class);
                        c.startActivity(in);
                        break;
                    } case "বন্ধুত্ব":{
                        Intent in = new Intent(c, FriendShip.class);
                        c.startActivity(in);
                        break;
                    } case "বন্ধু":{
                        Intent in = new Intent(c, Friend.class);
                        c.startActivity(in);
                        break;
                    } case "অনুভুতি":{
                        Intent in = new Intent(c, Feeling.class);
                        c.startActivity(in);
                        break;
                    }
                    case "দর্শন":{
                        Intent in = new Intent(c, Philosophy.class);
                        c.startActivity(in);
                        break;
                    }
                    case "নারী":{
                        Intent in = new Intent(c, Woman.class);
                        c.startActivity(in);
                        break;
                    }
                    case "ভালোবাসা":{
                        Intent in = new Intent(c, Valobasa.class);
                        c.startActivity(in);
                        break;
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
