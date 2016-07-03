package com.piotr.weather;

import com.piotr.weather.view.MainActivity_;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertNotNull;
import static org.robolectric.Robolectric.buildActivity;

/**
 * @author piotr on 30.04.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    private ActivityController<MainActivity_> mController;
    private MainActivity_ sut;

    @Before
    public void setUp() throws Exception {

        // Create new activity
        this.mController = buildActivity(MainActivity_.class);
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
}
