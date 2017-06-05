package com.shazam.david.shazamexercise;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shazam.david.shazamexercise.views.chart.ChartActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class AcceptanceTests {

    @Rule
    public ActivityTestRule<ChartActivity> mActivityTestRule = new ActivityTestRule<>(ChartActivity.class);

    @Test
    public void userCanViewFutureHitsChartAndSelectATrackToViewTrackDetails() throws Exception {
        // Select track from list
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        withParent(withId(R.id.view_animator)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(12, click()));

        // View track info on Shazam
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.go_to_shazam_btn), withText("View on shazam"), isDisplayed()));
        appCompatButton.perform(click());
    }

}
