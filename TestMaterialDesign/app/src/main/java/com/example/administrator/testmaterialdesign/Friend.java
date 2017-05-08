package com.example.administrator.testmaterialdesign;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Friend {
    private String imageURL;
    private String friendName;

    public Friend(String imageURL, String friendName) {
        this.imageURL = imageURL;
        this.friendName = friendName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
