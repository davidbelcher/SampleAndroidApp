package com.shazam.david.shazamexercise.views.chart;

import com.shazam.david.shazamexercise.data.ChartRepo;
import com.shazam.david.shazamexercise.data.app_models.Track;
import com.shazam.david.shazamexercise.views.utils.BasePresenter;

import java.util.List;

public class ChartPresenter extends BasePresenter<ChartPresenter.ChartView> {

    private final ChartRepo mChartRepo;

    public ChartPresenter(ChartRepo chartRepo) {
        mChartRepo = chartRepo;
    }

    @Override
    protected void updateView() {
        showLoading();

        mChartRepo.getTracks(new ChartRepo.Callback<List<Track>>() {
            @Override
            public void onSuccess(List<Track> results) {
                showChart(results);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    public void onTapToRetryErrorClicked() {
        updateView();
    }

    public void onTrackSelected(Track track) {
        showTrackDetail(track);
    }

    private void showLoading() {
        if (getView() != null) getView().showLoading();
    }

    private void showChart(List<Track> tracks) {
        if (getView() != null) getView().showChart(tracks);
    }

    private void showError() {
        if (getView() != null) getView().showTapToRetryError();
    }

    private void showTrackDetail(Track track) {
        if (getView() != null) getView().showTrackDetail(track);
    }

    public interface ChartView {

        void showLoading();
        void showChart(List<Track> tracks);
        void showTapToRetryError();
        void showTrackDetail(Track track);

    }

}
