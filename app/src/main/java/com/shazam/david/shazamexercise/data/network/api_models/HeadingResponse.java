package com.shazam.david.shazamexercise.data.network.api_models;

import com.google.gson.annotations.SerializedName;

public class HeadingResponse {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("subtitle")
    private String mSubtitle;

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }
}
