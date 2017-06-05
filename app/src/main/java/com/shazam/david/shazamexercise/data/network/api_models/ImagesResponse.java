package com.shazam.david.shazamexercise.data.network.api_models;

import com.google.gson.annotations.SerializedName;

public class ImagesResponse {

    @SerializedName("default")
    private String mDefault;

    public String getDefault() {
        return mDefault;
    }
}
