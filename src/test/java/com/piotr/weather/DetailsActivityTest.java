package com.piotr.weather;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.piotr.weather.utils.StringUtils;
import com.piotr.weather.view.DetailsActivity_;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.robolectric.Robolectric.buildActivity;

/**
 * @author piotr on 22.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class DetailsActivityTest {

    private ActivityController<DetailsActivity_> mController;
    private DetailsActivity_ sut;

    @Before
    public void setUp() throws Exception {

        // Create new activity
        this.mController = buildActivity(DetailsActivity_.class);
        this.sut = this.mController.create().postCreate(null).start().resume().visible().get();
    }

    @After
    public void tearDown() throws Exception {

        // Destroy activity
        this.mController.pause().stop().destroy();
        this.sut.finish();
    }

    @Test
    public void testOnCreateView() throws Exception {
        assertNotNull(sut);
    }

    @Test
    public void testOnDayChange() {
        String day = sut.getResources().getString(R.string.day);
        sut.dDay.setText(day);
        assertTrue(sut.dDay.getText().equals(day));
    }

    @Test
    public void testOnDateChange() throws Exception {
        String date = sut.getResources().getString(R.string.date);
        sut.dDate.setText(date);
        assertTrue(sut.dDate.getText().equals(date));
    }

    @Test
    public void testOnCityNameChange() throws Exception {
        String city_name = sut.getResources().getString(R.string.city_name);
        sut.dCity.setText(city_name);
        assertTrue(sut.dCity.getText().equals(city_name));
    }

    @Test
    public void testOnHighTemperatureChange() throws Exception {
        String high_temp = sut.getResources().getString(R.string.high_temp);
        sut.dHighTemp.setText(high_temp);
        assertTrue(sut.dHighTemp.getText().equals(high_temp));
    }

    @Test
    public void testOnLowTemperatureChange() throws Exception {
        String low_temp = sut.getResources().getString(R.string.low_temp);
        sut.dLowTemp.setText(low_temp);
        assertTrue(sut.dLowTemp.getText().equals(low_temp));
    }

    @Test
    public void testOnHumidityValueChange() throws Exception {
        String humidity_val = sut.getResources().getString(R.string.humidity_val);
        sut.dHumidityVal.setText(humidity_val);
        assertTrue(sut.dHumidityVal.getText().equals(humidity_val));
    }

    @Test
    public void testonPressureValueChange() throws Exception {
        String pressure_val = sut.getResources().getString(R.string.pressure_val);
        sut.dPressureVal.setText(pressure_val);
        assertTrue(sut.dPressureVal.getText().equals(pressure_val));
    }

    @Test
    public void testOnWindValueChange() throws Exception {
        String wind_val = sut.getResources().getString(R.string.wind_val);
        sut.dWindVal.setText(wind_val);
        assertTrue(sut.dWindVal.getText().equals(wind_val));
    }

    @Test
    public void testOnIconDrawableChange() throws Exception {
        final Drawable drawable = ContextCompat.getDrawable(sut, R.drawable.art_clear);
        sut.dIcon.setImageDrawable(drawable);
        assertTrue(sut.dIcon.getDrawable().equals(drawable));
    }

    @Test
    public void testOnDescriptionChange() throws Exception {
        String description = sut.getResources().getString(R.string.description);
        sut.dDescription.setText(StringUtils.capitalizeString(description));
        assertTrue(sut.dDescription.getText().equals(StringUtils.capitalizeString(description)));
    }

    @Test
    public void testOnClick() throws Exception {
        sut.findViewById(R.id.fab).performClick();
        assertTrue(sut.fab.isClickable());
        sut.fab.performClick();
    }
}
