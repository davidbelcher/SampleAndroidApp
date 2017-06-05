package com.shazam.david.shazamexercise.data;

import com.shazam.david.shazamexercise.data.app_models.Track;

import java.util.List;

public abstract class ChartRepo {

    public abstract void getTracks(Callback<List<Track>> callback);

    public interface Callback<T> {
        void onSuccess(T results);
        void onError();
    }

}
