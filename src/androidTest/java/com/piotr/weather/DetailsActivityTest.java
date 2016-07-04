package com.piotr.weather;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piotr.weather.view.MainActivity_;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * @author Piotr on 17.10.15.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<MainActivity_> mRule = new ActivityTestRule<>(MainActivity_.class);

    private final int[] detailsActivityTextViewsIdis = {
            R.id.dDay,
            R.id.dDate,
            R.id.dHighTemp,
            R.id.dLowTemp,
            R.id.dDescription,
            R.id.dHumidityVal,
            R.id.dPressureVal,
            R.id.dWindVal
    };

    private final int[] detailsActivityTextViewsDefaultValues = {
            R.string.day,
            R.string.date,
            R.string.temp,
            R.string.description,
            R.string.humidity_val,
            R.string.pressure_val,
            R.string.wind_val
    };

    @Before
    public void goToDetailsActivity() {
        onView(withId(R.id.mListView)).perform(click());
    }

    @Test
    public void checkIfDetailsActivityImageHasNoDefaultImage() {
        onView(withId(R.id.dIcon)).check(matches(not(withId(R.drawable.art_default))));
    }

    @Test
    public void checkIfDetailsActivityViewsHaveNoDefaultValue() {
        for (int viewId : detailsActivityTextViewsIdis)
            for (int valueId : detailsActivityTextViewsDefaultValues)
                onView(withId(viewId)).check(matches(not(withText(valueId))));
    }

    @Test
    public void checkIfSnackBarIsProperlyDisplayed() {
        onView(withId(R.id.fab)).perform(click());
        onView(withText(R.string.function_not_available)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnFloatingActionButtonToShowSnackBar() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.snackbar_text))
                .check(matches(withText(R.string.function_not_available)));

    }

    @Ignore // API 17 fail
    @Test
    public void swipeRightToDismissDisplayedCommunique() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.snackbar_text))
                .perform(swipeRight())
                .check(matches(not(isCompletelyDisplayed())));

    }
}
