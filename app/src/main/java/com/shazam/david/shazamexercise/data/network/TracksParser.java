package com.shazam.david.shazamexercise.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shazam.david.shazamexercise.data.network.api_models.JsonResponse;
import com.shazam.david.shazamexercise.data.network.api_models.TrackResponse;
import com.shazam.david.shazamexercise.data.app_models.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksParser {

    List<Track> parseTracksFromJson(String json) {
        Gson gson = new GsonBuilder().create();
        JsonResponse jsonResponse = gson.fromJson(json, JsonResponse.class);
        List<TrackResponse> trackResponse = jsonResponse.getChart();
        return mapToTracks(trackResponse);
    }

    private List<Track> mapToTracks(List<TrackResponse> chart) {
        List<Track> tracks = new ArrayList<>();
        for (TrackResponse trackResponse : chart) {
            tracks.add(mapToTrack(trackResponse));
        }
        return tracks;
    }

    private Track mapToTrack(TrackResponse trackResponse) {
        String coverArtUrl = trackResponse.getImageResponse().getDefault();
        String artistName = trackResponse.getHeadingResponse().getSubtitle();
        String trackName = trackResponse.getHeadingResponse().getTitle();
        String url = trackResponse.getUrl();
        int numShazams = trackResponse.getProperties().getNumberOfShazams();
        return new Track(coverArtUrl, artistName, trackName, url, numShazams);
    }

}
