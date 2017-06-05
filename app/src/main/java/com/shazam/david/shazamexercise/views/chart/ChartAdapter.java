package com.shazam.david.shazamexercise.views.chart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shazam.david.shazamexercise.R;
import com.shazam.david.shazamexercise.data.app_models.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    public interface TrackClickedListener {
        void onTrackClicked(Track track);
    }

    private final List<Track> mTracks;
    private final TrackClickedListener mTrackClickedListener;

    public ChartAdapter(List<Track> tracks, TrackClickedListener listener) {
        mTracks = tracks;
        mTrackClickedListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindWith(mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover_art)
        ImageView mCoverArtView;

        @BindView(R.id.track_title)
        TextView mTrackTitleView;

        @BindView(R.id.track_artist)
        TextView mTrackArtistView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Track track = mTracks.get(getAdapterPosition());
                    mTrackClickedListener.onTrackClicked(track);
                }
            });
        }

        public void bindWith(Track track) {
            Picasso.with(itemView.getContext())
                    .load(track.getCoverArtUrl())
                    .fit()
                    .centerCrop()
                    .into(mCoverArtView);

            mTrackArtistView.setText(track.getArtistName());
            mTrackTitleView.setText(track.getTrackName());
        }

    }
}
