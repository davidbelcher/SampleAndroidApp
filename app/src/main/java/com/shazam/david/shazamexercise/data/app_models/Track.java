package com.shazam.david.shazamexercise.data.app_models;

import android.os.Parcel;
import android.os.Parcelable;

public class Track implements Parcelable {

    private final String mCoverArtUrl;
    private final String mArtistName;
    private final String mTrackName;
    private final String mUrl;
    private final int mNumShazams;

    public Track(String coverArtUrl, String artistName, String trackName, String url, int numShazams) {
        mCoverArtUrl = coverArtUrl;
        mArtistName = artistName;
        mTrackName = trackName;
        mUrl = url;
        mNumShazams = numShazams;
    }

    public String getCoverArtUrl() {
        return mCoverArtUrl;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getNumShazams() {
        return mNumShazams;
    }

    private Track(Parcel in) {
        mCoverArtUrl = in.readString();
        mArtistName = in.readString();
        mTrackName = in.readString();
        mUrl = in.readString();
        mNumShazams = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCoverArtUrl);
        dest.writeString(mArtistName);
        dest.writeString(mTrackName);
        dest.writeString(mUrl);
        dest.writeInt(mNumShazams);
    }

    public static final Parcelable.Creator<Track> CREATOR
            = new Parcelable.Creator<Track>() {

        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

}
