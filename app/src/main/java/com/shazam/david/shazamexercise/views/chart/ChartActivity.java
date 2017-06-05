package com.shazam.david.shazamexercise.views.chart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ViewAnimator;

import com.shazam.david.shazamexercise.R;
import com.shazam.david.shazamexercise.views.detail.TrackDetailActivity;
import com.shazam.david.shazamexercise.data.network.NetworkChartRepo;
import com.shazam.david.shazamexercise.data.app_models.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity implements ChartPresenter.ChartView {

    private static final int INDEX_LOADING = 0;
    private static final int INDEX_ERROR = 1;
    private static final int INDEX_LIST = 2;

    private static final String FUTURE_HITS_JSON_URL = "https://amp.shazam.com/shazam/v2/en/US/android/-/tracks/web_chart_future_hits_us";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.view_animator)
    ViewAnimator mViewAnimator;

    @BindView(R.id.error_view)
    View mErrorView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ChartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_hits_chart);
        ButterKnife.bind(this);

        mPresenter = new ChartPresenter(new NetworkChartRepo(FUTURE_HITS_JSON_URL));

        setSupportActionBar(mToolbar);

        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onTapToRetryErrorClicked();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    @Override
    public void showLoading() {
        mViewAnimator.setDisplayedChild(INDEX_LOADING);
    }

    @Override
    public void showChart(List<Track> tracks) {
        mRecyclerView.setAdapter(new ChartAdapter(tracks, new ChartAdapter.TrackClickedListener() {
            @Override
            public void onTrackClicked(Track track) {
                mPresenter.onTrackSelected(track);
            }
        }));
        mViewAnimator.setDisplayedChild(INDEX_LIST);
    }

    @Override
    public void showTapToRetryError() {
        mViewAnimator.setDisplayedChild(INDEX_ERROR);
    }

    @Override
    public void showTrackDetail(Track track) {
        startActivity(TrackDetailActivity.newIntent(this, track));
    }
}
