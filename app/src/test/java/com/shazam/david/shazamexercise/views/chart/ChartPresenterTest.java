package com.shazam.david.shazamexercise.views.chart;

import com.shazam.david.shazamexercise.data.ChartRepo;
import com.shazam.david.shazamexercise.data.app_models.Track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChartPresenterTest {

    private ChartPresenter mPresenter;

    private List<Track> mSongsInChart = Arrays.asList(
        new Track("", "", "", "", 0), new Track("", "", "", "", 0)
    );

    @Mock
    ChartPresenter.ChartView mView;

    @Test
    public void loadingViewDisplayedWhenViewFirstAttached() {
        mPresenter = new ChartPresenter(getSuccessChartRepo());
        mPresenter.bindView(mView);

        verify(mView, times(1)).showLoading();
    }

    @Test
    public void chartFetchedAndDisplayedWhenViewFirstAttached() {
        mPresenter = new ChartPresenter(getSuccessChartRepo());
        mPresenter.bindView(mView);

        verify(mView, times(1)).showChart(eq(mSongsInChart));
    }

    @Test
    public void tapToRetryErrorViewDisplayedWhenErrorLoadingChart() {
        mPresenter = new ChartPresenter(getErrorChartRepo());
        mPresenter.bindView(mView);

        verify(mView, times(1)).showTapToRetryError();
    }

    @Test
    public void chartFetchedAgainWhenTappingOnErrorView() {
        mPresenter = new ChartPresenter(getSingleErrorThenSuccessRepo());
        mPresenter.bindView(mView);

        mPresenter.onTapToRetryErrorClicked();

        InOrder inOrder = Mockito.inOrder(mView);
        inOrder.verify(mView).showLoading();
        inOrder.verify(mView).showTapToRetryError();
        inOrder.verify(mView).showLoading();
        inOrder.verify(mView).showChart(mSongsInChart);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void trackDetailDisplayedWhenTrackTapped() {
        mPresenter = new ChartPresenter(getSuccessChartRepo());
        mPresenter.bindView(mView);

        Track selectedTrack = mSongsInChart.get(1);
        mPresenter.onTrackSelected(selectedTrack);

        verify(mView).showTrackDetail(eq(selectedTrack));
    }

    private ChartRepo getSuccessChartRepo() {
        return new ChartRepo() {
            @Override
            public void getTracks(Callback<List<Track>> callback) {
                callback.onSuccess(mSongsInChart);
            }
        };
    }

    private ChartRepo getErrorChartRepo() {
        return new ChartRepo() {
            @Override
            public void getTracks(Callback<List<Track>> callback) {
                callback.onError();
            }
        };
    }

    private ChartRepo getSingleErrorThenSuccessRepo() {
        return new ChartRepo() {

            int mCount = 0;

            @Override
            public void getTracks(Callback<List<Track>> callback) {
                if (mCount > 0) {
                    callback.onSuccess(mSongsInChart);
                } else {
                    mCount++;
                    callback.onError();
                }
            }
        };
    }

}