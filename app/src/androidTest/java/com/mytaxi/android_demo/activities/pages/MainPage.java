package com.mytaxi.android_demo.activities.pages;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.activities.commons.TimeIdlingResource;
import com.mytaxi.android_demo.activities.pages.BasePage;

import org.hamcrest.Matcher;
import org.hamcrest.core.Is;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Page class for Main page - menu and search
 *
 * @author ramya
 */
public class MainPage extends BasePage {

    private MainActivity activity;
    private Matcher<View> matcherTextFieldSearch = withId(R.id.textSearch);
    private Matcher<View> matcherSearchContainer = withId(R.id.searchContainer);
    private static final String PAGE_NAME = "Main page";

    public MainPage(MainActivity activity) {
        Log.d(PAGE_NAME, "Instantiating main page");
        this.activity = activity;
        IdlingResource idlingResource = TimeIdlingResource.timeout(4000);
        onView(matcherTextFieldSearch).check(matches(isDisplayed()));
        //need to do replacement for deprecation
        unregisterIdlingResources(idlingResource);
    }

    /**
     * Performs search for a given text.
     *
     * @param text text to be searhed
     */
    public final void search(String text) {
        Log.d(PAGE_NAME, "Making search for text: " + text);
        onView(matcherTextFieldSearch).perform(typeText(text));
        IdlingResource idlingResource = TimeIdlingResource.timeout(4000);
        onView(matcherSearchContainer).check(matches(isDisplayed()));
        //need to do replacement for deprecation
        unregisterIdlingResources(idlingResource);
    }

    /**
     * Selects a given name from search list.
     *
     * @param name driver name
     */
    public final void selectSearchItem(String name) {
        Log.d(PAGE_NAME, "Looking to click on driver name: " + name);
        onView(withId(R.id.searchContainer)).check(matches(isDisplayed()));
        ViewInteraction interaction = onView(withText(name))
                .inRoot(withDecorView(not(Is.is(activity.getWindow().getDecorView()))));
        interaction
                .check(matches(isDisplayed()));
        interaction.perform(click());
    }

    /**
     * Verifies given name in search list.
     *
     * @param name driver name
     */
    public final void verifyNameOccurrence(String name) {
        Log.d(PAGE_NAME, "Verifying driver name: " + name + " is present in the search result");
        ViewInteraction interaction = onView(withText(name))
                .inRoot(withDecorView(not(Is.is(activity.getWindow().getDecorView()))));
        interaction
                .check(matches(isDisplayed()));
    }

    /**
     * Verifies search text.
     *
     * @param text text which is searched
     */
    public final void verifySearchText(String text) {
        Log.d(PAGE_NAME, "Verifying search text: "+text);
        onView(matcherTextFieldSearch).check(matches(withText(text)));
    }

    /**
     * logout a user.
     */
    public final void logout() {
        Log.d(PAGE_NAME, "Logging out user");
        IdlingResource idlingResource = TimeIdlingResource.timeout(4000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Logout")).perform(click());
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
