package com.example.administrator.testmaterialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/4/12.
 */

public class AtyFriend extends AppCompatActivity {
    private String friendName;
    private int imageFriendId;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageFriendView;
    private TextView tvFriendContent;


    public static void actionStart(Context context, String friendName, int imageFriendId){
        Intent intent = new Intent(context, AtyFriend.class);
        intent.putExtra("friendName", friendName);
        intent.putExtra("imageFriendId", imageFriendId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_friend);
        Intent intent = getIntent();
        friendName = intent.getStringExtra("friendName");
        imageFriendId = intent.getIntExtra("imageFriendId", 0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToobar);
        imageFriendView = (ImageView) findViewById(R.id.imageFriendView);
        tvFriendContent = (TextView) findViewById(R.id.tvFriendContent);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(friendName);

        Glide.with(this).load(imageFriendId).into(imageFriendView);
        tvFriendContent.setText(getFriendContent());

    }

    private String getFriendContent(){
        StringBuilder friendContent = new StringBuilder();
        for (int i=0; i<500; i++){
            friendContent.append(friendName);
        }
        return friendContent.toString();
    }
}
