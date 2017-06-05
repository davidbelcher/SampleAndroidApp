package com.shazam.david.shazamexercise.data.network.api_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class JsonResponse {

    @SerializedName("chart")
    private List<TrackResponse> mChart;

    public List<TrackResponse> getChart() {
        return mChart;
    }

}
