package com.shazam.david.shazamexercise.data.network.api_models;

import com.google.gson.annotations.SerializedName;

public class TrackResponse {

    @SerializedName("heading")
    private HeadingResponse mHeadingResponse;

    @SerializedName("images")
    private ImagesResponse mImageResponse;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("properties")
    private PropertiesResponse mPropertiesResponse;

    public HeadingResponse getHeadingResponse() {
        return mHeadingResponse;
    }

    public ImagesResponse getImageResponse() {
        return mImageResponse;
    }

    public String getUrl() {
        return mUrl;
    }

    public PropertiesResponse getProperties() {
        return mPropertiesResponse;
    }
}
