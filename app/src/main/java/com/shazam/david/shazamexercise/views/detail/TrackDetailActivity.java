package com.shazam.david.shazamexercise.views.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.shazam.david.shazamexercise.R;
import com.shazam.david.shazamexercise.data.app_models.Track;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackDetailActivity extends AppCompatActivity {

    private static final String EXTRA_TRACK = "extra_track";

    public static Intent newIntent(Context context, Track track) {
        Intent intent = new Intent(context, TrackDetailActivity.class);
        intent.putExtra(EXTRA_TRACK, track);
        return intent;
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.cover_art)
    ImageView mCoverArt;

    @BindView(R.id.track_title)
    TextView mTrackTitle;

    @BindView(R.id.track_artist)
    TextView mTrackArtist;

    @BindView(R.id.num_shazams)
    TextView mNumShazams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        enableToolbarBackButton();

        Track track = getTrack();
        showTrackDetails(track);
    }

    private void enableToolbarBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private Track getTrack() {
        return getIntent().getParcelableExtra(EXTRA_TRACK);
    }

    private void showTrackDetails(Track track) {
        mToolbar.setTitle(track.getTrackName());

        Picasso.with(this)
                .load(track.getCoverArtUrl())
                .into(mCoverArt);

        mTrackTitle.setText(track.getTrackName());
        mTrackArtist.setText(track.getArtistName());
        mNumShazams.setText(String.valueOf(track.getNumShazams()));
    }

    @OnClick(R.id.go_to_shazam_btn)
    void onViewOnShazamClicked() {
        String url = getTrack().getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}
