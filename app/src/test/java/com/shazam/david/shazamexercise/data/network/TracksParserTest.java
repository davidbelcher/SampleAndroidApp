package com.shazam.david.shazamexercise.data.network;

import com.shazam.david.shazamexercise.data.app_models.Track;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;

public class TracksParserTest {

    @Test
    public void parsesCorrectNumberOfTracksFromJson() {
        List<Track> tracks = getTracks();
        assertThat(tracks.size(), is(20));
    }

    @Test
    public void parsesTrackDetails() {
        List<Track> tracks = getTracks();
        Track firstTrack = tracks.get(0);

        assertThat(firstTrack.getCoverArtUrl(), is("https://images.shazam.com/coverart/t353362412_s400.jpg"));
        assertThat(firstTrack.getArtistName(), is("Niall Horan"));
        assertThat(firstTrack.getTrackName(), is("Slow Hands"));
        assertThat(firstTrack.getUrl(), is("http://www.shazam.com/track/353362412/slow-hands"));
        assertThat(firstTrack.getNumShazams(), is(20));
    }

    private List<Track> getTracks() {
        String json = loadJSONFromAsset();
        TracksParser tracksParser = new TracksParser();
        return tracksParser.parseTracksFromJson(json);
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("future_hits.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}