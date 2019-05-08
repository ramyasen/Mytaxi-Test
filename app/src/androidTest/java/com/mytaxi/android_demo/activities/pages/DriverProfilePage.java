package com.mytaxi.android_demo.activities.pages;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.util.Log;
import android.view.View;


import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.commons.TimeIdlingResource;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Page class for Driver profile page.
 *
 * @author ramya
 */
public class DriverProfilePage extends BasePage {

    private Matcher<View> matcherButtonCall = withId(R.id.fab);
    private Matcher<View> matcherTextLocation = withId(R.id.textViewDriverLocation);
    private Matcher<View> matcherImageAvatar = withId(R.id.imageViewDriverAvatar);
    private Matcher<View> matcherTextName = withId(R.id.textViewDriverName);
    private static final String PAGE_NAME = "Driver profile page";

    public DriverProfilePage() {
        Log.d(PAGE_NAME, "Instantiating driver page");
        IdlingResource idlingResource = TimeIdlingResource.timeout(4000);
        onView(matcherButtonCall).check(matches(isDisplayed()));
        //need to use replacement for deprecation
        unregisterIdlingResources(idlingResource);
    }

    /**
     * Makes call to driver.
     */
    public final void makeCall() {
        Log.d(PAGE_NAME, "Making call to driver");
        onView(matcherButtonCall).perform(click());
    }

    /**
     * Verifies driver name.
     *
     * @param name driver name
     */
    public final void verifyName(String name) {
        Log.d(PAGE_NAME, "Verifying driver name");
        ViewInteraction viewName = onView(matcherTextName);
        viewName.check(matches(isDisplayed()));
        viewName.check(matches(withText(name)));
    }

    /**
     * Verifies driver location.
     *
     * @param location driver location
     */
    public final void verifyLocation(String location) {
        Log.d(PAGE_NAME, "Verifying driver location");
        ViewInteraction viewLocation = onView(matcherTextLocation);
        viewLocation.check(matches(isDisplayed()));
        viewLocation.check(matches(withText(location)));
    }

    /**
     * Verifies the presence of avatar.
     */
    public final void verifyAvatar() {
        Log.d(PAGE_NAME, "verifying driver photo/avatar");
        onView(matcherImageAvatar).check(matches(isDisplayed()));
    }
}
