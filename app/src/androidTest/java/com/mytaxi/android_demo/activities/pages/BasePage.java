package com.mytaxi.android_demo.activities.pages;

import android.util.Log;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

/**
 * Contains common functions across the pages.
 *
 * @author ramya
 */
public class BasePage {

    private static final String PAGE_NAME = "Base page";

    public void verifyPageTitle(String title) {
        Log.d(PAGE_NAME, "Verifying page title");
        onView(withText(title)).check(matches(isDisplayed()));
    }
}
