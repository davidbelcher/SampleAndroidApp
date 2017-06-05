package com.shazam.david.shazamexercise.data.network;

import android.os.AsyncTask;

import com.shazam.david.shazamexercise.data.ChartRepo;
import com.shazam.david.shazamexercise.data.app_models.Track;
import com.shazam.david.shazamexercise.data.utils.JsonDownloader;

import java.util.List;

public class NetworkChartRepo extends ChartRepo {

    private final String mChartUrl;

    public NetworkChartRepo(String chartUrl) {
        mChartUrl = chartUrl;
    }

    @Override
    public void getTracks(Callback<List<Track>> callback) {
        new GetTracksTask(callback).execute(mChartUrl);
    }

    private static class GetTracksTask extends AsyncTask<String, Void, List<Track>> {

        private final Callback<List<Track>> mCallback;

        private final JsonDownloader mJsonDownloader = new JsonDownloader();
        private final TracksParser mTracksParser = new TracksParser();

        GetTracksTask(Callback<List<Track>> callback) {
            mCallback = callback;
        }

        @Override
        protected List<Track> doInBackground(String... params) {
            try {
                String jsonUrl = params[0];
                String json = mJsonDownloader.downloadJson(jsonUrl);
                return mTracksParser.parseTracksFromJson(json);
            } catch (Exception exception) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Track> tracks) {
            super.onPostExecute(tracks);
            if (tracks != null) {
                mCallback.onSuccess(tracks);
            } else {
                mCallback.onError();
            }
        }
    }
}
