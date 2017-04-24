package com.example.administrator.testmaterialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private Context mContext;
    private List<Friend> mFriendList;

    public FriendAdapter(List<Friend> friendList) {
        this.mFriendList = friendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.friend_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
//                Log.d("----------------->", "onClick: position:" + position);
//                Log.d("----------------->", "onClick: size:" + mFriendList.size());
                Friend friend = mFriendList.get(position);
                AtyFriend.actionStart(mContext, friend.getFriendName(), friend.getImageId());
            }
        });
//        return new ViewHolder(view);
        return holder;
//        返回值改成holder!!
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Log.d("----------------->", "onClick: position:" + position);
        Friend friend = mFriendList.get(position);
        holder.tvFriendName.setText(friend.getFriendName());
        Glide.with(mContext).load(friend.getImageId()).into(holder.imageFriend);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageFriend;
        TextView tvFriendName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageFriend = (ImageView) itemView.findViewById(R.id.imageFriend);
            tvFriendName = (TextView) itemView.findViewById(R.id.tv_friendName);
        }
    }
}
