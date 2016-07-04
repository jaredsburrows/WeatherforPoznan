package com.piotr.weather;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.piotr.weather.view.MainActivity_;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

/**
 * @author Piotr on 16.10.2015.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    private static final int testPosition = 2;

    @Test
    public void refreshForecastDataUsingSwipeDown() {
        onView(withId(R.id.swipeRefresh)).check(matches(isEnabled())).perform(swipeDown());
    }

    @Ignore // Fails on multiple APIs
    @Test
    public void swipeToTheLastElementOfForecastAdapter() {
        onView(withId(R.id.mListView)).check(matches(isDisplayed()))
                .perform(swipeUp(), swipeUp(), swipeUp(), swipeUp());
    }

    @Test
    public void checkIfClickingOnItWillTakeMeToDetailsActivity() {
        onView(withId(R.id.mListView)).check(matches(isClickable())).perform(click());
    }

    @Test
    public void checkIfForecastItemIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItem)).check(matches(isDisplayed()));
    }

    @Ignore // Fails on API 23
    @Test
    public void checkIfForecastItemIconIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemIcon)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTableIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTable)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemDateIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemDate)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemDescriptionIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemDescription)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTemperatureIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTemperature)).check(matches(isDisplayed()));
    }

    @Ignore // Fails on API 19
    @Test
    public void checkIfForecastItemTemperatureMaxIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTemperatureMax)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfForecastItemTemperatureMinIsDisplayedOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTemperatureMin)).check(matches(isDisplayed()));
    }

    @Ignore // Fails on API 16
    @Test
    public void checkIfForecastItemIconHasADefaultValueOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemIcon))
                .check(matches(not(withId(R.mipmap.ic_launcher))));
    }

    @Test
    public void checkIfForecastItemDateHasADefaultValueOnForecastAdapter() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemDate))
                .check(matches(not(withText(R.string.date))));
    }

    @Test
    public void checkIfForecastItemDescriptionHasADefaultValue() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemDescription))
                .check(matches(not(withText(R.string.description))));
    }

    @Test
    public void checkIfForecastItemTemperatureMaxHasADefaultValue() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTemperatureMax))
                .check(matches(not(withText(R.string.temp))));
    }

    @Test
    public void checkIForecastItemTemperatureMinHasADefaultValue() {
        onData(anything()).inAdapterView(withId(R.id.mListView)).atPosition(testPosition).
                onChildView(withId(R.id.forecastItemTemperatureMin))
                .check(matches(not(withText(R.string.temp))));
    }
}
