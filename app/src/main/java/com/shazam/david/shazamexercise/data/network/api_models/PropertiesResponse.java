package com.shazam.david.shazamexercise.data.network.api_models;

import com.google.gson.annotations.SerializedName;

public class PropertiesResponse {

    @SerializedName("numberOfShazams")
    private int mNumberOfShazams;

    public int getNumberOfShazams() {
        return mNumberOfShazams;
    }
}
