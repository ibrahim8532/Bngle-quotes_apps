package com.ib.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
// Set its Properties
//grid view with 2 columns in each row
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
// Adapter
        mAdapter = new MyAdapter(this, getModels());
        mRecyclerView.setAdapter(mAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_menu);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawer_layout);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout , toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.more_app:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/developer?id=IB+Production")));
                       break;

                    case R.id.rate:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        }catch (ActivityNotFoundException e){
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                        break;
                    case R.id.share:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBody = "Bengali Quotes Apps \n"+ "https://play.google.com/store/apps/details?id=" +getPackageName();
                        String shareSub = "Your Suhject Here";
                        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(intent,"share using"));
                        break;
                    case R.id.privacy_policy:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent privacy = new Intent(Home.this, PrivacyPolicy.class);
                        startActivity(privacy);
                        break;
                    case R.id.terms:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent terms = new Intent(Home.this, TermsConditions.class);
                        startActivity(terms);
                        break;
                }
                return false;
            }
        });
    }

    private ArrayList<Model> getModels() {
        ArrayList<Model> models = new ArrayList<>();
        Model p;

        // Create an items for recycler view below
        p = new Model();
        p.setTitle("অনুপ্রেরণা");
        p.setImage(R.drawable.motivation);
        models.add(p);

        p = new Model();
        p.setTitle("প্রেম");
        p.setImage(R.drawable.love_vector);
        models.add(p);

        p = new Model();
        p.setTitle("জীবন");
        p.setImage(R.drawable.life);
        models.add(p);

        p = new Model();
        p.setTitle("উপদেশ");
        p.setImage(R.drawable.advice);
        models.add(p);

        p = new Model();
        p.setTitle("একাকিত্ব");
        p.setImage(R.drawable.lonekiness);
        models.add(p);

        p = new Model();
        p.setTitle("ভালোবাসা");
        p.setImage(R.drawable.valobase);
        models.add(p);

        p = new Model();
        p.setTitle("কষ্ট");
        p.setImage(R.drawable.tro);
        models.add(p);

        p = new Model();
        p.setTitle("মন");
        p.setImage(R.drawable.mind);
        models.add(p);

        p = new Model();
        p.setTitle("দুঃখ");
        p.setImage(R.drawable.sorrow);
        models.add(p);

        p = new Model();
        p.setTitle("শিক্ষা");
        p.setImage(R.drawable.education);
        models.add(p);

        p = new Model();
        p.setTitle("বন্ধুত্ব");
        p.setImage(R.drawable.friendship);
        models.add(p);

        p = new Model();
        p.setTitle("বন্ধু");
        p.setImage(R.drawable.friendjpg);
        models.add(p);
        p = new Model();
        p.setTitle("অনুভুতি");
        p.setImage(R.drawable.feelingjpeg);

        models.add(p);
        p = new Model();
        p.setTitle("দর্শন");
        p.setImage(R.drawable.download);
        models.add(p);

        p = new Model();
        p.setTitle("নারী");
        p.setImage(R.drawable.womanjpg);
        models.add(p);

        return models;
    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT");
        builder.setMessage("Are You Sure Want to Exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).show();
    }
}