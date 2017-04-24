package com.example.administrator.testmaterialdesign;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Friend {
    private int imageId;
    private String friendName;

    public Friend(int imageId, String friendName) {
        this.imageId = imageId;
        this.friendName = friendName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
