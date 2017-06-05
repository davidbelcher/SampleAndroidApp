package com.shazam.david.shazamexercise.data.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonDownloader {

    public String downloadJson(String jsonUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(jsonUrl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
